# Gestion des index

## Introduction

To first start understand index, you need to use the following metaphore:

Imagine searching a chapter on a book (hard copy) without a table of contents.

An index is nothing more than a data structure that collects information about the values of specified fields in the documents of a
collection. This data structure is used by MongoDB’s query optimizer to quickly sort through and order the documents in a collection.
Remember that indexing ensures a quick lookup from data in your documents. Basically, you should view an index as a predefined query that was executed and had its results stored. As you can imagine, this enhances query performance dramatically.

Indexes significantly reduce the amount of work required to fetch documents. Without the proper indexes, the only way to satisfy a query is to scan all documents linearly until the query conditions are met.

Then, you might wonder why you would ever need to delete an index, rebuild your indexes, or even delete all indexes within a collection. The simple answer is that doing so lets you clean up some irregularities.

Finally, a single collection can have no more than 64 indexes.

Create an index

```
db.collection.createIndex( <key and index type specification>, <options> )
db.collection.ensureIndex(index, properties)
db.collection.getIndexes()
```
Example

```
> use semaine05
  spec = {ns: "semaine05.users", key: {'addresses.zip': 1}, name: 'zip'}
  db.system.indexes.insert(spec, true)
```

```
> db.users.getIndexes()
```

Delete index

```
db.collection.dropIndex(<name>)
```

## Index types

### Default _id

MongoDB creates a default index on _id field.

### Single Field

This is similar to the _id index, but on any field. It can be sorted by ascending or descending.
```
{name: 1}
```

### Compound

This type specifies an index on multiple fields. The index is sorted on first field and so on. You can mix order.
```
{name: 1, price: -1}
```

Only one singlekey index will be used to resolve a query, however for queries containing multiple keys, a compound index containing those keys will best resolve the query.

More generally, if you have a compound index on A‐B , then a second index on A alone will be redundant, but not one on B

The order of keys in a compound index matters.

As a general rule, a query where one term demands an exact match and another specifies a range requires a compound index where the range key comes second.

Example:

Imagine a cookbook where you store recipe and ingredien.

Another example is to store Price and Manufacturers with disk locations. 

### Multikey

This is for field that stores an array of items. 

A separated index for every element in the array is also created.

Each indexed document can have at most one indexed field whose value is an array.

### Geospatial index

This enables you to create a geospatial index based on 2d or 2 sphere coordinates.

The *type* parameter can be used to specify the document’s object type, which can be a Point, a LineString or a Polygon.

```
{"locs": "2d"}
```
Syntax:
```
> db.restaurants.insert({"name": "Kimono", "loc": { type: "Point", coordinates: [ 52.370451, 5.217497] } } )
> db.streets.insert( {"name": "Westblaak", "loc": { type: "LineString", coordinates: [ [52.36881, 4.890286],[52.368762, 4.890021] ] } } )
> db.stores.insert( 
  { 
    "name": "SuperMall", 
    "loc": 
    { 
      type: "Polygon", 
      coordinates: 
        [ 
          [
            [52.146917, 5.374337], 
            [52.146966, 5.375471], 
            [52.146722, 5.375085], 
            [52.146744, 5.37437], 
            [52.146917, 5.374337] 
          ] 
        ] 
    } 
  })
```
Example:
```
> db.restaurants.insert( { "name": "Kimono", "loc": { type: "Point", coordinates: [ 52.370451, 5.217497] } } )
> db.restaurants.insert( { "name": "Shabu Shabu", "loc": { type: "Point", coordinates: [51.915288, 4.472786] } } )
> db.restaurants.insert( { "name": "Tokyo Cafe", "loc": { type: "Point", coordinates: [52.368736, 4.890530] } } )

> db.restaurants.ensureIndex ( { "loc": "2dsphere" } )

> db.restaurants.find( { "loc" : [52,5] } )
> db.restaurants.find( { "loc" : { $geoNear : { $geometry : { type : "Point", coordinates: [52.338433, 5.513629] } } } } )
> db.restaurants.find( { "loc" : { $geoNear : { $geometry : { type : "Point", coordinates: [52.338433, 5.513629] }, $maxDistance : 40000 } } } )
> db.restaurants.find( { "loc": { $geoWithin : { $box : [ [52.368549, 4.890238], [52.368849, 4.89094] ] } } } )
> db.restaurants.find( 
  { 
    "loc" :
    { $geoWithin :
      { $geometry :
        { 
          type : "Polygon" ,
          coordinates : 
          [ [
            [52.368739, 4.890203], [52.368872, 4.890477], [52.368726, 4.890793], [52.368608, 4.89049], [52.368739, 4.890203]
          ] ]
        }
      }
    }
  })
> db.restaurants.find( { "loc": { $geoWithin : { $center : [ [52.370524, 5.217682], 10] } } } )
> db.restaurants.find( { "loc": { $geoWithin : { $centerSphere : [ [52.370524, 5.217682], 10] } } } )
> db.runCommand( { geoNear : "restaurants", near : { type : "Point", coordinates: [52.338433, 5.513629] }, spherical : true})
```

### Text

This index supports faster lookup of string elements by words that are contained inside a text. It does not store words such as *the*, *a*, and *is*.
```
{"comment": "text"}
```

Enabling Text Search
```
db.adminCommand({ setParameter: 1, textSearchEnabled : true }
```

Import and test some data
```
$ mongoimport -d semaine05 -c texttest --drop --type json --file text.json
```

```
> db.texttest.ensureIndex( { body : "text" } );
> db.texttest.getIndexes()
> db.texttest.runCommand( "text", { search :"fish" } )
> db.texttest.runCommand( "text", { search : "fish", filter : { about : "food" } })
> db.texttest.runCommand( "text", { search : "cook" })
> db.texttest.runCommand( "text", { search : "cook ‐lunch" })
> db.texttest.runCommand( "text", { search : "mongodb text search" })
> db.texttest.runCommand( "text", { search : "\"mongodb text search\"" })
```

Additional Options
```
> db.texttest.runCommand( "text", { search :"fish", lagnuage : "french" } )
```

Read more about Text Indexes, https://docs.mongodb.com/manual/core/index-text/

### Hashed

This enables you to use a hashed index that indexes only hashed values that match those stored in the particular server.
```
{key: "hashed"}
```

## Property

### Background

True or False

### Unique

Forces the index to only include a single instance of each field value.

If you need a unique index on a collection, it’s usually best to create the index before inserting any data.

However, you may have to insert data beforehand in special cases such as data migration.

### Sparse

This ensures that the index will contain entries only for documents that have the indexed field.

Example:

1. You have product sku field and when you do data entry, you may not have a sku number at the time.

   You can later come and add that value. Instead of storing NULL, you do not include that field on the document.
   
2. Another example is an application that allow anonymous reviews. The field userID would not be included.
  ```
  > db.reviews.createIndex({user_id: 1}, {sparse: true, unique: false})
  ```

### TTL (Time To Live)

This can be used for log entries or event data that should be cleaned up after a certain amount of time.

### Name 

You can specify a name for the index.

## Hands on

Ensure ascending index
```
> db.media.ensureIndex( { Title :1 } )
```

```
> db.media.insert( 
  { "Type" : "CD", 
    "Artist" : "Nirvana",
    "Title" : "Nevermind",
    "Tracklist" : 
      [ 
        { 
          "Track" : "1", 
          "Title" : "Smells Like Teen Spirit", 
          "Length" : "5:02" 
        }, 
        {
          "Track" : "2",
          "Title" : "In Bloom",
          "Length" : "4:15" 
        } 
      ] 
  })
  
```

Embedded field
```
> db.media.ensureIndex( { "Tracklist.Title" : 1 } )
```

Index each element of an array
```
> db.media.ensureIndex( { "Tracklist" : 1 } )
```

Index specific field within an array

```
> db.media.ensureIndex({"Tracklist.Title": 1, "Tracklist.Length": ‐1})
```

Forcing a specified index to query data
```
> db.media.find( { ISBN: " 978‐1‐4302‐5821‐6"} ) . hint ( { ISBN: ‐1 } )
> db.media.ensureIndex({ISBN: 1}, {background: true});
> db.media.find( { ISBN: " 978‐1‐4302‐5821‐6"} ) . hint ( { ISBN: 1 } )
> db.media.find( { ISBN: " 978‐1‐4302‐5821‐6"} ) . hint ( { ISBN: 1 } ).explain()
```

## Putting all together

Generate the following data.

```
> use semaine05
for(i=0; i<200000; i++) {
    db.numbers.save({"num": i, "createdDateTime":new Date()});
}
```

Run the following queries

```
> db.numbers.count()
> db.numbers.find({num: 500})
> db.numbers.find( {num: {"$gt": 199995 }} )
> db.numbers.find( {num: {"$gt": 20, "$lt": 25 }} )
```

Now let's see some statistics
```
> db.numbers.find( {num: {"$gt": 199995 }} ).explain()
```

Let's add an index on num
```
> db.numbers.ensureIndex({num: 1})
```

Now, what happened?
```
> db.numbers.find( {num: {"$gt": 199995 }} ).explain()
```

## Exercice

This is NASDAQ stock exchange’s symbols. There are documents for a 30+ years period beginning in 1983.

Manipulate the following collection and determine how to improve the performance.

Use function *explain()* (with queryPlanner, or executionStats, or allPlansExecution) and *setProfilingLevel()* to validate speed increase.

```
$ mongorestore -d semaine05 dump/stocks
```


### [Travail pratique] (https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-05/Travail-pratique.md)


## References

* Le site de documentation de MongoDB. Indexes https://docs.mongodb.com/manual/indexes/ (page accédée le 02 février 2017)
