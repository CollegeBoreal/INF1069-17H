# Les fonctions de recherche

## La fonction *find()*

Rechercher tous les documents.

Syntaxe
```
db.collection.find(query, <projection>)
```

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-03/Querying-data-in-MongoDB.PNG)

Importer les données suivantes pour manipuler les fonctions.
```
mongoimport -h localhost:27017 -d semaine03 -c states --drop --type json --file IndiaStates.json
mongoimport -h localhost:27017 -d semaine03 -c cities --drop --type csv --file IndiaCitiesPopulation.csv --headerline
```

Exemple: Rechercher tous les états.
```
> db.states.find().pretty()
```
Exemple pour parcourir et afficher les documents en la ligne de commande.
```
> var cursor = db.states.find()
> cursor.forEach(function printEach(doc) { print(doc.state)})
```

### Limiter les champs dans la selection
```
> db.cities.find({}, {city:1, state:1})
```
Par défaut le champ *_id* est toujours retourné.

On utilise 1 pour afficher, sinon 0.
```
> db.cities.find({}, {city:1, state:1, _id:0})
```

Accéder aux champs imbriqués (nested)
```
> db.states.find({"info.capital":"Mumbai"})
```
Les opérateurs *Equal* et *Not Equal ($ne)*

Exemple: Rechercher toutes les villes dont le nom est "Pune".
```
> db.cities.find({city:"Pune"}).pretty()
```
En mysql
``` 
select * from cities where city = 'Pune' 
```
Note: Il n'y a pas d'opérateur $eq

*Not Equal* ($ne), pour exclure.

Syntaxe
```{field:{$ne:value}}```

Exemple: Rechercher toutes les villes dont l'état est autre que "ANDHRA PRADESH"
```
> db.cities.find({state:{$ne:"ANDHRA PRADESH"}})
```

Opérateur *like*
```
> db.cities.find({city:/Mumbai/i})
> db.cities.find({city:{$regex:/Mumbai/i}})
```
En mysql
```
select * from cities where lower(city) like '%mumbai%' 
```
Note: Pour ignorer la casse majuscule/minuscule, on utilise *i*

### Paginer et trier

La fonction *sort()*: permet de faire le tri.

Le paramètre 1 pour l'ordre croissant, sinon -1 pour décroissant.
```
> db.cities.find().sort({city:-1})
> db.cities.find().sort({state:-1, city:1})
```

La fonction *limit()*
```
> db.cities.find({}, {state:1, city:1}).sort({state:1, city:1}).limit(20)
```

La fonction *skip()*
```
> db.cities.find({}, {state:1, city:1}).sort({state:1, city:1}).skip(20).limit(20)
```

### Les opérateurs de comparaison

Les opérateurs *less than ($lt)*, *less than or equal to ($lte)*, *greater than ($gt)*, et *greater than or equal to ($gte)*

Syntaxe
``` 
{field:{$lt:value} 
```
```
> db.cities.find({sexRatio:{$lt:800}})
> db.cities.find({population:{$gte:10000000}})
```

### Autres opérateurs de comparaison: *include ($in)*, *not include ($nin)*, et *all ($all)*
```
> db.cities.find({city:{$in:["Kolkata", "Chennai"]}}).pretty()
> db.cities.find({city:{$in:["Kolkata", "Chennai", /Mumbai/]}}).pretty()
> db.cities.find({state:{$nin:["MAHARASHTRA", "KARNATAKA"]}}).count()
> db.states.find({cities:{$all:["Pune", "Thane"]}})
```

Note: les commandes suivantes sont équivalentes. Dites pourquoi?
```
> db.cities.find({city:{$all:["Pune"]}})
> db.cities.find({city:"Pune"})
```

Opérateur *Exist* ($exists): recherche les documents dont le champ existe.
```
> db.states.find({info:{$exists:false}}).pretty()
```
Note: Il ne s'agit pas de chercher les valeurs qui sont NULL.

### Les opérateurs de logique *$and*, *$or*, *$not* et *$nor*

Syntaxe
```
{$and:[expression1, expression2 … expressionN]}
```
Pour utiliser *and* implicitement
```
> db.cities.find({city:"Pune", state:"MAHARASHTRA"})
```
Sinon
```
> db.cities.find({$and:[{city:"Pune", state:"MAHARASHTRA"}]})
```
Attention:

Ne pas utiliser le même champ pour plusieurs conditions pour le AND implicite.

Exemple: Rechercher les villes dont le champ *isMetro* existe et dont la valeur est vrai.
```
> db.cities.find({isMetro:{$exists:true}, isMetro:{$ne:"true"}})
```
Le résultat inclut ceux qui ne sont filtrés dans la 1ère condition, car seul la 2ième condition est considérée.

Dans ce cas, il faut utiliser $and

```
> db.cities.find({$and:[{isMetro:{$ne:"true"}}, {isMetro:{$exists:true}}]})
```

ou bien utiliser ceci:

```
> db.cities.find({isMetro:{$ne:"true", $exists:true}})
```

Opérateur $or, $not et $nor n'ont qu'une seule syntaxe.
```
> db.cities.find({$or:[{state:"ASSAM"}, {state:"NAGALAND"}]})
> db.cities.find({population:{$not:{$lte:1000000}}}).count()
> db.cities.find({nonExistent:{$not:{$lte:1000000}}}).count()
> db.cities.find({nonExistent:{$gt:1000000}}).count()
> db.cities.find({$nor:[{population:{$lt:1000000}},{state:"MAHARASHTRA"},{state:"UTTAR PRADESH"}]}).count()
```
### Autres opérateurs

Opérateur $mod: pour une valeur paire ou impaire
```
> db.media.find ( { Released : { $mod: [2,0] } }, {"Cast" : 0 } )
```

Opérateur $slice: pour une valeur dans un tableau étant donné l'index
```
> db.media.find({"Title" : "Matrix, The"}, {"Cast" : {$slice: 3}})
> db.media.find({"Title" : "Matrix, The"}, {"Cast" : {$slice: -3}})
> db.media.find({"Title" : "Matrix, The"}, {"Cast" : {$slice: [2,3] }})
```

Opérateur $size: pour une taille d'un tableau 

## La fonction *findOne()*

Similaire à *find()*, toutefois ne retourne qu'un seul document.

Syntaxe
```
db.collection.findOne(query, <projection>)
```

## La fonction *findAndModify*

Syntaxe
```
db.collection.findAndModify({
	query: <document>,
	sort: <document>,
	remove: <boolean>,
	update: <document>,
	new: <boolean>,
	fields: <document>,
	upsert: <boolean>
})
```
Les paramètres *upsert* et *remove* ne peuvent pas être combinés.
```
> db.catalog.findAndModify({
	query: {journal : "Oracle Magazine"},
	sort: {catalogId : 1},
	update: {$inc: {catalogId: 1}, $set: {edition: '11122013'}},
	upsert :true,
	new: true,
	fields: {catalogId: 1, edition: 1, title: 1, author: 1}
})
> db.catalog.findAndModify({
	query: {journal : "Oracle Magazine"},
	sort: {catalogId : 1},
	update: {$inc: {catalogId: 1}, $set: {edition: '11122013'}},
	remove: true,
	upsert :true,
	new: true,
	fields: {catalogId: 1, edition: 1, title: 1, author: 1}
})
```

### [Exercices](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-03/Exercices.md)

### [Réponses](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-03/Reponses.md)

## References

David Hows; Peter Membrey; Eelco Plugge; Tim Hawkins. The Definitive Guide to MongoDB: A complete guide to dealing with Big Data using MongoDB, Third Edition. Apress. 16-DEC-2015
