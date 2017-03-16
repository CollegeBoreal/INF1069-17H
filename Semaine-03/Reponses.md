# Les fonctions de recherche - Réponses

```
> db.titles.insert(
	{
		"TitleName":"Extreme Scoping",
		"PageCount":300,
		"PublicationYear":2013,
		"Author":"Larissa Moss",
		"AmazonReview":4.5
	})
> db.titles.insert(
	{
		"TitleName":"Data Engineering  ",
		"PageCount":100,
		"PublicationYear":2013,
		"Author":"Brian Shive",
		"AmazonReview":4.25
	})
> db.titles.insert(
	{
		"TitleName":"Business Intelligence",
		"PageCount":442,
		"PublicationYear":2013,
		"Author":"Barry Devlin, PhD",
		"AmazonReview":5
	})
> db.titles.insert(
	{
		"TitleName":"Data Modeling Made Simple",
		"PageCount":250,
		"PublicationYear":2009,
		"Author":"Steve Hoberman",
		"AmazonReview":4.35
	})
```

__Question 1__ 
```
> db.titles.find({$and:[{"PageCount": {$gt: 200}}, {PublicationYear: {$lt: 2010}}]}).pretty()
```

__Question 2__ 
```
> db.titles.find({"Author":{$nin:[/Shive/]}}, {"_id":0, "Author":1}).count()
> db.titles.find({"Author":{$not:{$in:[/Shive/]}}},{"_id":0, "Author":1}).count()
```

__Question 3__
```
> db.titles.findOne()
```

__Question 4__
```
> db.books.find({"categories":{$ne:"Java"}}).count()
> db.books.find({"categories":{$not:{$all:["Java"]}}}).count()
> db.books.find({"categories":{$not:{$in:["Java"]}}}).count()
```

__Question 5__
```
> var nbCount = db.books.find({}).count()
> db.books.find({}).skip(nbCount-3)
```

__Question 6__
```
> db.books.find({"publishedDate": {$gt: ISODate("2005-03-01")}}).pretty()
> db.books.find({publishedDate:{$gt: new ISODate("2005-03-01"), $exists:true}})
```

__Question 7__ 
```
> db.books.find({publishedDate:{$exists:true}},{"_id":0, "title":1, "isbn":1, "authors":1}).sort({"_id":1}).limit(5).pretty()
```

__Question 8__
```
> db.books.find({}).sort({"_id":1}).skip(20).limit(5).pretty()
```

__Question 9__
```
> db.getCollection('books').find({"status" : "PUBLISH"}).count()
> db.books.find({"status" : "PUBLISH"}).count()
```

__Question 10__
```
> db.books.find({$or:[{"categories": "Networking"}, {"status":{$ne:"PUBLISH"}}]}).pretty().count()

```
