# Modélisation avec Mongo DB (suite)

## Data modeling

In MongoDB, it's always about  your application first.

More important, it's also about how your data is used.

### Conceptual model

This is a high level, abstract view, with the objective of identifying the fundamental concepts, very close to how users perceive the data. It is frequently used to describe universal domain concepts, such as person, store, product, instructor, student, and course.

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-07/Crows-foot-notation-example.PNG)

### Logical model 

It is derived from the conceptual model. It is common to describe business requirements. Details such as cardinality and nullability of relationship attributes with data types and constraints are mapped on this model too. The most widely graphical presentation used is the entity relationship (ER) model.

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-07/Entity-relationship-diagram-example.PNG)

### Physical model

In this model, we have detailed and less generic information about the data. We should know which technology should
be used. We can include tables, column names, keys, indexes, ..., and whatever detail you think is necessary.

## Normalizing Data with Document References

This is the process of organizing documents and collections to minimize redundancy and dependency. Typically, this is useful for objects that have a one-to-many or many-to-many relationship with subobjects.

**Advantage**

* The database size will be smaller because only a single copy of objects will exist in their own collection (no duplication).
* Additionally, if you modify the information in the subobject frequently, then you need to modify only a single instance.

**Major disadvantage**

* A separate lookup must occur to link the subobject. This can result in a significant performance hit if you are accessing the user data frequently.

Example:

An application that stores users who have a favorite store. Thousands of users might have the same favorite store. Therefore, storing the favorite store object data in each User object doesn't make sense because it would result in thousands of duplications.

## Denormalizing Data with Embedded Documents

This is the process of identifying subobjects of a main object that should be embedded directly into the document of the main object. Typically, this is done on objects that have mostly one-to-one relationships or that are relatively small and do not get updated frequently.

**Major advantage**

* You can get the full object back in a single lookup without needing to do additional lookups.

**Major disadvantage**

* For subobjects with a one-to-many relationship, you are storing a separate copy in each document - this slows insertion a bit and takes up additional disk space.

Example:

An example of when denormalizing data makes sense is a system that contains students' home contact information. How many people will likely have the same address?

## Design consideration

* Consistency
* Read/write ratio
* Types of queries / updates
* Analytics
* Data life-cycle and growth

## Application Design Tips

* Duplicate data for speed, reference data for integrity.
* Normalize if you need to future-proof data: you should be able to use normalized data for different applications that will query the data in different ways in the future.
* Try to fetch data in a single query.
* Embed dependent fields - if only one document cares about certain information, embed the information in that document.
* Embed "point-in-time" data - Example: You don't want a user's past orders to change if he updates his profile.
* Do not embed fields that have unbound growth - Example: comments.
* Pre-populate anything you can.
* Preallocate space, whenever possible.
* Store embedded information in arrays for anonymous access - Subdocuments should be used when you’ll always know exactly what you’ll be querying for. - Arrays should usually be used when you know some criteria about the element you’re querying for.

You can only ask for specific items: Is items.slingshot.damage greater than 20? 

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-07/Tip-9-subdocument.PNG)

Now you can use a simple query such as {"items.damage" : {"$gt" : 20}}

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-07/Tip-9-array.PNG)

## Hands on

### Practice 1

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-07/Practice-1-person.PNG)

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-07/Practice-1-car.PNG)

Solution

```

```
### Practice 2

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-07/Practice-2-sql.PNG)

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-07/Practice-2-nosql.PNG)

Solution

```

```

### Practice 3

* Every post is distinct with unique title, description and url.
* Every post has the name of its publisher and total number of likes.
* Every post can have one or more tags.
* Each post can have zero or more comments and the comments must contain user name, message, data-time and likes.

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-07/Practice-3.PNG)

Solution

```

```

### Practice 4

* A film could be acted by multiple actors and actresses. 
* A film can belong to multiple categories.
* An actor or actress could act multiple films.
* A category could include multiple films.

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-07/Practice-4-sql.PNG)

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-07/Practice-4-nosql.PNG)

Solution

```

```


## References
* Steve Hoberman. Data Modeling for MongoDB. Technics Publications. 09-JUN-2014.
* Brad Dayley. Sams Teach Yourself NoSQL with MongoDB in 24 Hours, Video Enhanced Edition. Sams, 08-SEP-2014.
* Kristina Chodorow. 50 Tips and Tricks for MongoDB Developers. O'Reilly Media, Inc., April 29, 2011.

