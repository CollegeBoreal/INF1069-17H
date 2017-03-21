# Fonction MapReduce

Import these collections to manipulate given examples.

```
mongoimport -d semaine10 -c IndiaStates --drop --file IndiaStates.json
mongoimport -d semaine10 -c IndiaCitiesPopulation --drop --file IndiaCitiesPopulation.json
mongoimport -d semaine10 -c mapreduce --drop --file mapreduce.json
```

Map reduce is a programming model used on large data sets. It was first developed by Google.
It involves breaking down the problem in a key value pairs for processing. 
This means it suits best problems that can be broken down into key value pair.

## Understanding the mapReduce() Method

The Collection object provides the mapReduce() method to perform the operations on data before returning the results.

```
> db.<collection>.mapReduce (
	mapFunction,
	reduceFunction,
	options
)
```

The options are:

```
{
	out: <collection>,
	query: <document>,
	sort: <document>,
	limit: <number>,
	finalize: <function>,
	scope: <document>,
	jsMode: <boolean>,
	verbose: <boolean>
}
```

* out

This is the only required option. It specifies the location of the result of the map reduce operation.
You can use one the following parameters:

**Inline**: This performs the operation in memory and returns the resulted objects to the client. out: {inline:1}

**Collection**: This specifies a collection name to insert the results into. The collection gets created if it does not exist. out: outputCollection

**Collection with action**: This specifies a collection and database to output the data to with a replace, merge, or reduce action. out: {replace: outputCollection}

* query

This specifies the query operators to use to limit which documents are passed to the map function.

* sort

This specifies the sort field object to use to sort documents before passing them to the map function.

* limit

This specifies a maximum number of documents to return from the collection.

* finalize

This is executed after the reduce function. It contains user-define logic.

```
finalize: function(key, document) {
	<do some logic here>
	return document;
}
```

* scope

This specifies global variables that are accessible in the map, reduce, and finalize functions.

* jsMode

This is a Boolean with default value to FALSE.
When TRUE, the intermediate dataset objects are not converted to BSON in the map function during the emit execution.

* verbose

This is a Boolean with default to TRUE. It returns the timing data in the results.

The following code shows an example of using map reduce and specifying the out and query and sort options:

```
results = myCollection.mapReduce(
	function() { emit(this.key, this.value); },
	function(key, values){ return Array.sum(values); },
	{
		out: {inline: 1},
		query: {value: {$gt: 6}
	}
);
```

## map

This is a function that is applied on each object in the dataset and emits a key and value.

The value is added to an array based on the key name to be used during the reduce phase.

```
function(){
	<put some logic to manipulate key and value>
	emit(key, value);
}
```

## reduce

This is a function that is applied after the map function run on all objects with the reduce function.

The function must accept a key as the first parameter and the array of values that match the key as the second.

Finally, it uses the array values to calculate a single value for the key and return that result. 

```
function(key, values){
	<put some logic on values to calculate a single result>
	return result;
}
```

## Note

The map function can invoke the emit function as many times it wants.

Both map and reduce functions cannot access the database for any reason and should have no impact outside the map function additionally the reduce function should be idempotent

In nutshell, the Map reduce operation consists on the following four steps:

1. Read the input collection.

2. Execute the map operation.

3. Execute the reduce operation.

4. Write to output collection.

## Scenario

```
> function map() {
	for(i = 0; i < this.cities.length; i++) {
		emit(this.cities[i].substring(0, 1), 1);
	}
}

> function reduce(key, values) {
	return Array.sum(values);
}

> db.IndiaStates.mapReduce(map, reduce, {out: "census_mr"})

> db.census_mr.find().sort({value: -1}).limit(3)
```

## Hands-on

### Example

Execute the following script

```
mongo = new Mongo('localhost');
wordsDB = mongo.getDB('semaine10');
wordsColl = wordsDB.getCollection('word_stats');
results = wordsColl.mapReduce(
	function() { emit(this.first, this.stats.vowels); },
	function(key, values){ return Array.sum(values); },
	{ out: {inline: 1}}
);
print("Total vowel count in words beginning with " + "a certain letter: ");
for(i in results.results){
	print(JSON.stringify(results.results[i]));
}
results = wordsColl.mapReduce(
	function() { emit(this.first, 
						{ vowels: this.stats.vowels, 
						consonants: this.stats.consonants})
	}, 
	function(key, values){
		result = {count: values.length, 
				vowels: 0, consonants: 0};
		for(var i=0; i<values.length; i++){
			if (values[i].vowels)
				result.vowels += values[i].vowels;
			if(values[i].consonants)
				result.consonants += values[i].consonants;
		}
		return result;
	},
	{ out: {inline: 1},
		query: {last: {$in:['a','e','i','o','u']}},
		finalize: function (key, obj) {
			obj.characters = obj .vowels + obj.consonants;
			return obj;
		}
	}
);
print("Total words, vowels, consonants and characters in words " +
	"beginning with a certain letter that ends with a vowel: ");
for(i in results.results){
	print(JSON.stringify(resu lts.results[i]));
}
```

### Another example

First we use the map function

```
var map = function() {
	emit(this.color, this.num);
};
```

Then the reduce function

```
var reduce = function(color, numbers) {
	return Array.sum(numbers);
};
```

Now let's call the mapReduce function

```
> db.mapreduce.mapReduce(map,reduce, {out: "mrresult"});
```

Finally, we can manipulate the resulted collection

```
> db.mrresult.findOne();
```

### Advanced example

Let's analyze together these functions

```
var map = function() {
	var value = {
		num : this.num,
		count : 1
	};
	emit(this.color, value);
};

var reduce = function(color, val ) {
	reduceValue = { num : 0, count : 0};
	for (var i = 0; i < val.length; i++) {
		reduceValue.num += val[i].num;
		reduceValue.count += val[i].count;
	}
	return reduceValue;
};

db.mapreduce.mapReduce(map,reduce,{ out: "mrresult" });
```

See the resulted collection

```
db.mrresult.findOne();
```

What about the finalize function ?

```
var finalize = function (key, value) {
	value.avg = value.num/value.count;
	return value;
};
```

```
db.mapreduce.mapReduce(map,reduce,{ out: "mrresult", finalize : finalize });
```

```
db.mrresult.findOne();
```

## Debugging MapReduce

Similar to any programming design, when it comes to debugging, it never easy.

This is a simple example.

```
var emit = function(key, value) {
	print("emit results ‐ key: " + key + " value: " + tojson(value));
}
```

You can test one using map.apply()

```
map.apply(db.mapreduce.findOne());
```

Then reduce

```
reduce("blue",a);
```

### [Exercice](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-10/Exercice.md)

## References
* Instant MongoDB. By Amol Nayak. Packt Publishing. 26-JUL-2013
* The Definitive Guide to MongoDB: A complete guide to dealing with Big Data using MongoDB, Second Edition. By David Hows; Peter Membrey; Eelco Plugge; Tim Hawkins. Apress. 06-NOV-2013
