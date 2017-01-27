# Les opérations CRUD - Réponses

Question 1 
```
> db.user.insert( {username: "Henry"})
```
Question 2 
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
Question 3 
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
Question 4 
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
Question 5 
```
> db.user.update({"username": "Paul"}, {$unset: {"age": 1}})
```
Question 6. On ajoute à l'usager Tom le films "The Maltese Falcon". Tom n'existe pas, donc il n'y aura pas de changement.

Question 7. On réduit l'âge de l'usage moe de 1. moe n'existe pas dans la collection, donc il n'y aura pas de changement.

Question 8 
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
Question 9 
```
> db.user.update( {"username": "James"}, { $unset: { "hobbies":{"sports": 1}} })
> db.user.update( {"username": "James"}, { $unset: { "hobbies.sports": 1}})
```
Question 10
```
> db.user.update( {"username": "Paul"}, { $set: { "hobbies.sports": ["Soccer", "Boxe"]}})
```
Question 11
```
> db.user.insert(
	[
		{"username": "Steve"},
		{"username": "Gary"},
		{"username": "Yves"},
		{"username": "Julien"}
	])
```
Question 12
```
> db.user.update (
		{ $or:[{"username": "Steve"}, {"username": "Gary"}, {"username": "Yves"}, {"username": "Julien"}]},
		{$set: {"country": "Canada"}},
		{upsert: false, multi: true}
	)
```
Question 13
```
> db.user.find({"country": "Peru"}).pretty()
> db.user.update(
	{ "country": "Peru"}, 
	{ $addToSet: { "hobbies.sports": "Volleyball"}},
	{upsert: false, multi: true} 
)
```
Question 14
```
$ mongoexport --db semaine02 --type=json --collection user --out user.bak.json
```
Question 15
```
> db.user.remove({})
```
Question 16
```
$ mongoimport --port 27018 -d semaine02 -c user --drop --type json --file user.bak.json
```
Question 17
```
$ mongoimport --port 27018 -d semaine02 -c customer --drop --type csv --file customers.csv --headerline
$ mongoimport --port 27018 -d semaine02 -c product --drop --type csv --file products.csv --headerline
```
Question 18
```
> db.product.find().count()
> db.product.update(
    {},
    {$set: { "CountryFrom": "", "Description": ""}},
    {upsert: false, multi: true}
)
```
On a 77 documents

Question 19
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

Question 20
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

Question 21
```
> db.product.find({"CategoryID" : 4}).count()
> db.product.find({"CategoryID" : 4}).pretty()
> db.product.update(
    {"CategoryID" : 4},
    {$set: { Discontinued: 0 }},
    {upsert: false, multi: true}
)
```
