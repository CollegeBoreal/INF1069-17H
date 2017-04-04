# Les fonctions d’agrégation - Réponses

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
	{out: {replace: "question1"}}
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
            query: {$and: 
			[
                {"result": {"$exists": true}},
                {"result": "Pass"}
			]},
            out: {replace: "question4"}
	}
)

db.question4.find({}).sort({"value":-1}).limit(1)
```

__Question 5__

```
db.cityInspections.mapReduce(
	function() {
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
            query: {$and: 
			[
                {"address.city": {"$exists": true}},
				{"address.city": {$ne: ""}}
			]},
            out: {replace: "question5"}
	}
)

db.question5.find({}).sort({"value":-1}).limit(1)
```

__Question 6__

```
```
