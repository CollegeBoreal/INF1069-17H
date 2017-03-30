# Gestion de la perfomance

## Analytics methods

* stats()

This helps you identify which collection is taking up the disk space or index space.
When looking at the database stats, it's also worth noting the difference between dataSize and storageSize. 
If storageSize exceeds dataSize by more than a factor of two, then performance may suffer because of ondisk fragmentation.

```
db.stats()
```

* validate()

This method checks the structure of the collection by scanning through the data and indexes.
If it detects problems, the output reports them.

```
db.collection.validate()
```

* setProfilingLevel()

The profiling results are stored in a special capped collection called system.profile 
which is located in the database where you executed the setProfilingLevel command.

```
db.setProfilingLevel(2)
```

* explain()

MongoDB provides the explain() function, which provides you a detailed procedure for the current operator.

```
db.collection.find().sort({fieldName: -1}).explain()
```

* currentOp()

The db.currentOp() function can be used to get this report from a database engine.

```
db.currentOp(true)
```

## Query optimization

Query optimization is the process of identifying slow queries, discovering why they're slow, and then taking steps to speed them up.

### Identifying slow queries

The causes of slow queries vary:

* Poor application design;
* Inappropriate data models; 
* Insufficient physical hardware, etc.

### Examining slow queries

Discovering why these queries are slow is trickier and may require some detective work.
In more difficult cases, you might have to rearrange indexes, restructure the data model, or upgrade hardware.
You should always look at the simplest case first.

### Query patterns

* Single-key indexes
* Exact matches. Example: db.values.find({close: 100})
* Sorting. Example: A sort on the indexed field: db.values.find({}).sort({_id: 1})
* Range queries. Example: db.values.find({close: {$gte: 100}}).sort({close: 1})
* Compound-key indexes

## Best practices

### Application Design Tips

#### Duplicate data for speed, reference data for integrity

* Decision factor #1

How often does the data you're thinking of referencing actually change?
The less it changes, the stronger the argument for denormalization.
It is almost never worth referencing seldom changing data such as names, birth dates, stock symbols, and addresses.

* Decision factor #2

How important is consistency?
If consistency is important, you should go with normalization.
For example, suppose multiple documents need to atomically see a change.
If we were designing a trading application where certain securities 
could only be traded at certain times, we'd want to instantly "lock" them all when they were untradable.

* Decision factor #3

Do reads need to be fast?
If reads need to be as fast as possible, you should denormalize.
In this application, they don't, so this isn't really a factor.
Realtime applications should usually denormalize as much as possible.

#### Try to fetch data in a single query

If we were designing a blog application, a request for a blog post might be one application unit.
When we display a post, we want the content, tags, some information about the author 
(although probably not her whole profile), and the post's comments.
Thus, we would embed all of this information in the post document and we could fetch everything needed for that view in one query.

#### Embed dependent fields

When considering whether to embed or reference a document, ask yourself if you'll be querying 
for the information in this field by itself, or only in the framework of the larger document. 
For example, you might want to query on a tag, but only to link back to the posts with that tag, not for the tag on its own. 
Similarly with comments, you might have a list of recent comments, but people are interested in going to the post that inspired the comment.

#### Embed "point in time" data

Any sort of information like this, where you want to snapshot the data at a particular time, should be embedded. 
You don't want a user's past orders to change if he updates his profile.

#### Do not embed fields that have unbound growth

Because of the way MongoDB stores data, it is fairly inefficient to constantly be appending information to the end of an array. 
You want arrays and objects to be fairly constant in size during normal usage. 
Example: Comments are often a weird edge case that varies on the application.

#### Preallocate space, whenever possible

This is an optimization for once you know that your documents usually grow to a certain size, 
but they start out at a smaller size.

#### Prepopulate anything you can

If you know that your document is going to need certain fields in the future, 
it is more efficient to populate them when you first insert it than to create the fields as you go.

#### Issue updates to only modify fields that have changed

#### Avoid negation in queries

#### Avoid unnecessarily long field names

#### Test every query in your application with explain()

### Optimization Tips

#### Minimize disk access

Accessing data from RAM is fast and accessing data from disk is slow. 
Therefore, most optimization techniques are basically fancy ways of minimizing the amount of disk accesses.

* Use SSDs

SSDs (solid state drives) are much faster than spinning hard disks for many things, 
but they are often smaller, more expensive, are difficult to securely erase.

* Add more RAM

Adding more RAM means you have to hit disk less. 
However, adding RAM will only get you so far—at some point, your data isn't going to fit in RAM anymore.

#### Allocate CPU hardware budget for faster CPUs

#### Dedicate each server to a single role in the system

#### Use the most recent drivers from MongoDB

#### Ensure uniform distribution of shard keys

#### Avoid large indexed arrays

#### Don’t always use an index

Every time a new record is added, removed, or updated, every index affected by the change must be updated.

#### Create indexes that cover your queries

If we only want certain fields returned and can include all of these fields in the index, 
MongoDB can do a covered index query, where it never has to follow the pointers to
documents and just returns the index’s data to the client.

#### Use compound indexes to make multiple queries fast

If possible, create a compound index that can be used by multiple queries. 
This isn't always possible, but if you do multiple queries with similar arguments, it may be.

#### Seek professional assistance

## References
* Kristina Chodorow; 50 Tips and Tricks for MongoDB Developers; O'Reilly Media, Inc.; 2011.