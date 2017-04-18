# Fonction MapReduce - Réponses

__Question 1__ 

```
db.mapreduceBooks.mapReduce(
	function() {
		for (var index = 0; index < this.authors.length; ++index) {
			var author = this.authors[index];
			emit(author.firstName + " " + author.lastName, 1);
		}
	},
	function(key, values){
		count = 0;
		for (var index = 0; index < values.length; ++index) {
			count += values[index];
		}		
		return count;
	},
	{
		query:  
		{
			$and: 
			[
				{"author.firstName": {"$exists": true}},
				{"author.lastName": {"$exists": true}},
				{"author.firstName": {$ne: ""}},
				{"author.lastName": {$ne: ""}}
			]
		},
		out: {replace: "question1"}
	}
)
```

__Question 2__ 

```
db.mapreduceBooks.mapReduce(
	function() {
        emit( this.publisher, { count: 1, price: this.price } );
    },
	function(key, values) {
        var value = { count: 0, price: 0 };

        for (var index = 0; index < values.length; ++index) {
            value.count += values[index].count;
            value.price += values[index].price;
        }
        return value;
    },
    {   
        scope: { currency: "US" },
        query:  
		{
			$and: 
			[
				{"publisher": {"$exists": true}},
				{"publisher": {$ne: ""}}
			]
		},
	out: { replace: "question2" },
        finalize: 
            function(key, value) {
                value.average = currency + ( value.price / value.count ).toFixed(2);
                return value;
            }
    }
)
```

__Question 3__ 

```
db.mapreduceBooks.mapReduce(
	function() {
        emit( this.publisher, { count: 1, price: this.price } );
    },
	function(key, values) {
        var value = { count: 0, price: 0 };

        for (var index = 0; index < values.length; ++index) {
            value.count += values[index].count;
            value.price += values[index].price;
        }
        return value;
    },
    {   
        scope: { currency: "US" },
	query:  
		{
			$and: 
			[
				{"publisher": {"$exists": true}},
				{"publisher": {$ne: ""}}
			]
		},
        out: { replace: "question3" },
        finalize: 
            function(key, value) {
				value.average = currency + ( value.price / value.count ).toFixed(2);
				return value;
			}
    }
)
```

__Question 4__

```
db.cityInspections.mapReduce(
	function() {
		var m = this.issue_date.getMonth()+1;
        emit( m, 1 );
    },
	function(key, values) {
		count = 0;
		for (var index = 0; index < values.length; ++index) {
			count += values[index];
		}
		return count;
    },
    {
		query:  
		{
			$and: 
			[
				{"result": {"$exists": true}},
				{"result": "Pass"},
				{"issue_date": {"$exists": true}}
			]
		},
		out: {replace: "question4"}
	}
)

db.question4.find({}).sort({"value":-1}).limit(1)
```

__Question 5__

```
db.cityInspections.mapReduce(
	function() {
	// Manipuler les chaines de caractères seulement si c'est nécessaire.
        emit( this.address.city.toLowerCase(), 1 );
    },
	function(publisher, values) {
		count = 0;
		for (var index = 0; index < values.length; ++index) {
			count += values[index];
		}
		return count;
    },
    {
		query:
		{
			$and: 
			[
				{"address.city": {"$exists": true}},
				{"address.city": {$ne: ""}}
			]
		},
		out: {replace: "question5"}
	}
)

db.question5.find({}).sort({"value":-1}).limit(1)
```

__Question 6__

```
db.cityInspections.mapReduce(
	function() {
        emit( this.result, { count: 1, average: 0, total: 0 });
    },
	function(key, values) {
		var value = { count: 0, average: 0, total: 0 };
		for (var index = 0; index < values.length; ++index) {
			value.count += values[index].count;
		}
		
		return value;
    },
    {
			scope: { total: db.cityInspections.find({}).count()},
			query: {$and: 
			[
				{"result": {"$exists": true}},
				{"result": "Fail"}
			]},
			out: {replace: "question6"},
			finalize: 
				function(key, value) {
					//value.average = ( value.count / total ).toFixed(2);
					value.average = ( value.count / total );
					value.total = total;
					return value;
				}
	}
)
```
