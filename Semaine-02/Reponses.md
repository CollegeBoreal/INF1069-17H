# Les opérations CRUD - Réponses

__Question 1__ 
```
> db.user.insert( {username: "Henry"})
```

__Question 2__ 
```
> db.user.update({username: "Henry"}, {$set: {country: "Canada"}})
> db.user.update({username: "Henry"}, {$unset: {country: "Canada"}})
> db.user.update({username: "Henry"}, {username: "Henry", country: "Canada"})
> db.user.update({username: "Henry"}, {$unset: {country: "Canada"}})
> db.user.save(
	{
        "_id" : ObjectId("5886ba6a62a16634044fc728"),
        "username" : "Henry",
        "country" : "Canada"
	})
```

__Question 3__
```
> db.user.insert( 
	{
		"username": "Marie",
		"country" : "Peru",
		"hobbies": {
					"movies": ["Chicago", "Star Wars", "X-Men", "Friday"],
					"sports": ["Baseball", "Tennis"]
				}
	})
```
ou bien en utilisant update avec upsert ou sinon save.

__Question 4__
```
> db.user.insert(
	{
		"username": "Paul",
		"city" : "Paris",
		"hobbies": {
					"sports": ["Soccer", "Boxe"]
				},
		"age": 20
	})
```

__Question 5__
```
> db.user.update({"username": "Paul"}, {$unset: {"age": 1}})
```

__Question 6__

On ajoute à l'usager Tom le films "The Maltese Falcon". Tom n'existe pas, donc il n'y aura pas de changement.

__Question 7__ 

On réduit l'âge de l'usage moe de 1. moe n'existe pas dans la collection, donc il n'y aura pas de changement.

__Question 8__
```
> db.user.update(
	{	"username": "Henry"},
	{
		"username": "Henry",
		"country" : "Canada",
		"hobbies": {
					"movies": ["Chicago", "Star Wars", "X-Men", "Friday"],
					"sports": ["Baseball", "Tennis", "Judo"]
				},
		"age": 24
	})
```

__Question 9__
```
> db.user.update( {"username": "James"}, { $unset: { "hobbies":{"sports": 1}} })
> db.user.update( {"username": "James"}, { $unset: { "hobbies.sports": 1}})
```

__Question 10__
```
> db.user.update( {"username": "Paul"}, { $set: { "hobbies.sports": ["Soccer", "Boxe"]}})
```

__Question 11__
```
> db.user.insert(
	[
		{"username": "Steve"},
		{"username": "Gary"},
		{"username": "Yves"},
		{"username": "Julien"}
	])
```

__Question 12__
```
> db.user.update (
		{ $or:[{"username": "Steve"}, {"username": "Gary"}, {"username": "Yves"}, {"username": "Julien"}]},
		{$set: {"country": "Canada"}},
		{upsert: false, multi: true}
	)
```

__Question 13__
```
> db.user.find({"country": "Peru"}).pretty()
> db.user.update(
	{ "country": "Peru"}, 
	{ $addToSet: { "hobbies.sports": "Volleyball"}},
	{upsert: false, multi: true} 
)
```

__Question 14__
```
$ mongoexport --db semaine02 --collection user --out user.bak.json
```

__Question 15__
```
> db.user.remove({})
```

__Question 16__
```
$ mongoimport --port 27018 -d semaine02 -c user --drop --type json --file user.bak.json
```

__Question 17__
```
$ mongoimport --port 27018 -d semaine02 -c customer --drop --type csv --file customers.csv --headerline
$ mongoimport --port 27018 -d semaine02 -c product --drop --type csv --file products.csv --headerline
```

__Question 18__
```
> db.product.find().count()
> db.product.update(
    {},
    {$set: { "CountryFrom": "", "Description": ""}},
    {upsert: false, multi: true}
)
```
On a 77 documents

__Question 19__
```
> db.product.find({"CategoryID" : 2}).count()
> db.product.find({"CategoryID" : 2}).pretty()
> db.product.update(
    {"CategoryID" : 2},
    {$inc: { UnitPrice: 5 }},
    {upsert: false, multi: true}
)
```
On a 12 documents

__Question 20__
```
> db.product.find({"SupplierID" : 2}).count()
> db.product.find({"SupplierID" : 2}).pretty()
> db.product.update(
    {"SupplierID" : 2},
    {$set: { CountryFrom: "Canada" }},
    {upsert: false, multi: true}
)

> db.product.find({"SupplierID" : 1}).count()
> db.product.find({"SupplierID" : 1}).pretty()
> db.product.update(
    {"SupplierID" : 1},
    {$set: { CountryFrom: "USA" }},
    {upsert: false, multi: true}
)
```
On en a 4 pour Canada et 3 pour USA

__Question 21__
```
> db.product.find({"CategoryID" : 4}).count()
> db.product.find({"CategoryID" : 4}).pretty()
> db.product.update(
    {"CategoryID" : 4},
    {$set: { Discontinued: 0 }},
    {upsert: false, multi: true}
)
```
