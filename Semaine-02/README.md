# Les opérations CRUD
## Une opération *atomic*
Une opération *atomic* est une combinaison de plusieurs opérations 
qui sont exécutées comme une seule opération - cad, soit l'opération réussie ou elle échoue.

On parle d'opération *atomic*, si:
* Aucun processus n'est au courant du changement jusqu'à ce que le changement soit complété.
* Si une opération échoue, l'ensemble d'opérations est annulé (rollback).

## Règles sur la nomenclature d'une collection
* Pas plus que 128 caractères.
* Ne peut pas avoir un champ de caractère vide (" ") comme nom.
* Doit commencer par une lettre ou bien underscore _ Exemple: 6019_inf est invalide
* Ne peut pas utiliser le mot *system*, car c'est une collection reservée par MongoDB.
* Ne peut pas contenir le caractère NULL (dont le code ascii est "\0").

## Règles à considérer pour un document
* Le nom d'un champ ne doit pas commencer par le caractère $ Exemple: $tags
* Le nom d'un champ ne doit pas contenir le caractère [.] Exemple: ta.gs
* Le champ *_id* est réservé pour la clé primaire.
* Bien que ce n'est pas recommandé, le champ *_id* peut contenir une chaine de caractère ou un integer.

## La fonction insérer

Syntaxe
```
db.collection.insert(<document or array of documents>,
					{writeConcern: <document>,ordered:<boolean>})
```

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-02/Adding-data-in-MongoDB.PNG)

En utilisant une variable
```
> use semaine02
> db.catalog.drop()
> doc1 = {
				"catalogId" : 1, 
				"journal" : 'Oracle Magazine',
				"publisher" : 'Oracle Publishing',
				"edition" : 'November December 2013',
				"title" : 'Engineering as a Service',
				"author": 'David A. Kelly'
			}
> db.catalog.insert(doc1)
> db.catalog.find().pretty()
```
Note:

La collection est créée automatiquement si elle n'existe pas. On n'est pas obligé de la créer explicitement.
```
> document = ({ 
				"Type" : "Book",
				"Title" : "Definitive Guide to MongoDB 2nd ed., The",
				"ISBN" : "978-1-4302-5821-6",
				"Publisher" : "Apress",
				"Author": [
						"Hows, David",
						"Plugge, Eelco",
						"Membrey, Peter",
						"Hawkins, Tim" 
					] 
				} )
> db.media.insert(document)
> db.media.find().pretty()
```
Directement dans la ligne de commande sans définir de variable
```
> db.media.insert( { "Type" : "CD", "Artist" : "Nirvana", "Title" : "Nevermind" })
> db.media.find().pretty()
```
Directement dans la ligne de commande avec des sauts de lignes
```
> db.media.insert( { "Type" : "CD",
"Artist" : "Nirvana",
"Title" : "Nevermind",
"Tracklist" : 
	[
		{
			"Track" : 1,
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
> db.media.find().pretty()
```
En spécifiant implicitement une valeur pour le champ *_id*
```
> doc1 = {
				"_id": ObjectId("507f191e810c19729de860ea"),
				"catalogId" : 1,
				"journal": 'Oracle Magazine',
				"publisher" : 'Oracle Publishing',
				"edition" : 'November December 2013',
				"title" : 'Engineering as a Service',
				"author" : 'David A. Kelly'
			}
> db.catalog.insert(doc1)
> db.catalog.find().pretty()
```
La clé *_id* doit être unique
```
> doc2 = {
				"_id": ObjectId("507f191e810c19729de860ea"),
				"catalogId" : 2,
				"journal" : 'Oracle Magazine',
				"publisher" : 'Oracle Publishing',
				"edition" : 'November December 2013'
			}
> db.catalog.insert(doc2)
> doc3 = {"_id": ObjectId("507f191e810c19729de860ea"),"catalogId" : 3}
> db.catalog.insert(doc3)
> db.catalog.find().pretty()
```
Insérer plusieurs documents à la fois
```
> doc1 = {
				"_id": ObjectId("507f191e810c19729de860ea"),
				"catalogId" : 1,
				"journal" : 'Oracle Magazine',
				"publisher" : 'Oracle Publishing',
				"edition" : 'November December 2013',
				"title": 'Engineering as a Service',
				"author" : 'David A. Kelly'
			}
> doc2 = {
				"_id" : ObjectId("53fb4b08d17e68cd481295d5"),
				"catalogId" : 2,
				"journal" : 'Oracle Magazine',
				"publisher" : 'Oracle Publishing',
				"edition" : 'November December 2013',
				"title": 'Quintessential and Collaborative',
				"author" : 'Tom Haunert'
			}
> db.catalog.drop()
> show collections
> db.catalog.insert([doc1, doc2])
> db.catalog.find().pretty()
```
Autres exemples
```
> doc1 = {
				"_id": ObjectId("507f191e810c19729de860ea"),
				"catalogId" : 2,
				"journal" : 'Oracle Magazine',
				"publisher" : 'Oracle Publishing', 
				"edition" : 'November December 2013',
				"title" : 'Engineering as a Service',
				"author" : 'David A. Kelly'
			}
> doc2 = {
				"_id" : ObjectId("53fb4b08d17e68cd481295d5"), 
				"catalogId" : 1, 
				"journal" : 'Oracle Magazine', 
				"publisher" : 'Oracle Publishing', 
				"edition" : 'November December 2013',
				"title": 'Quintessential and Collaborative',
				"author" : 'Tom Haunert'
			}
> doc3 = {
				"_id" : ObjectId("53fb4b08d17e68cd481295d6"),
				"catalogId" : 3, 
				"journal" : 'Oracle Magazine', 
				"publisher" : 'Oracle Publishing', 
				"edition" : 'November December 2013'
			}
> db.catalog.drop()
> show collections
> db.catalog.insert([doc3, doc1, doc2], { writeConcern: { w: "majority", wtimeout: 5000 }, ordered:true })
```
## La fonction modifier
* La fonction prend 3 paramètres: *criteria*, *objNew* et *options*.
* Le paramètre *criteria* permet de spécifier le critère pour retrouver la donnée à modifier.
* Le paramètre *objNew* permet de spécifier la donnée mise à jour; 
* ou sinon utiliser un opérateur pour le faire. 
* Le paramètre *options* permet de spécifier les options et les values possibles sont: *upsert* et *multi*.
* upsert: mettre à jour ou créer.
* multi: tous les documents trouvés ou la 1er seulement.

Syntaxe
```
db.collection.update(query, update, options)
db.collection.update(
	<query>,
	<update>,
	{
		upsert: <boolean>,
		multi: <boolean>,
		writeConcern: <document>
	}
)
```

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-02/Updating-data-in-MongoDB.PNG)

Insérer dans notre catalogue en premier, puis modifier
```
> db.catalog.drop()
> show collections
> doc1 = {
			"catalogId" : 1, 
			"journal" : 'Oracle Magazine', 
			"publisher": 'Oracle Publishing', 
			"edition" : 'November December 2013'
		}
> db.catalog.insert(doc1)
> db.catalog.find().pretty()
```
Modifier le document
```
> db.catalog.update(
	{ 
		catalogId: 1
	},
	{
		"catalogId" :  1,
		"journal" : 'Oracle Magazine', 
		"publisher" : 'Oracle Publishing', 
		"edition" : '11122013',
		"title" : 'Engineering as a Service',
		"author": 'Kelly, David A.'
	}
)
> db.catalog.find().pretty()
```
Pas besoin d'insérer, on utilise *upsert: true*
```
> db.media.update( 
	{ "Title" : "Matrix, The"}, 
	{
		"Type" : "DVD", 
		"Title" : "Matrix, The", 
		"Released" : 1999, 
		"Genre" : "Action"
	}, 
	{ upsert: true} 
)
> db.media.find({"Title" : "Matrix, The"}).pretty()
```
Utiliser *upsert* et *multi* quand on utilise l'opérateur *$set*
```
> db.media.update( 
	{ "Title" : "Matrix, The"}, 
	{	
		$set: {
				"Type" : "Blue-R", 
				"Title" :"Matrix, The", 
				"Released" : 1999, 
				"Genre" : "Action",
				"Published": 2017
			} 
	}, 
	{upsert: true, multi: true} 
)
> db.media.find().pretty()
> db.catalog.update(
	{ journal: 'Oracle Magazine'},
	{
		$set: { edition: '11129999'},
		$inc: { catalogId: 2 }
	},
	{
		multi: true,
		writeConcern: { w: 1, wtimeout: 5000 }
	}
)
> db.catalog.find().pretty()
```
Modifier plusieurs documents à la fois.

Insérer tout d'abord
```
> use semaine02
> db.catalog.drop()
> show collections
> doc1 = {
			"catalogId" : 1, 
			"journal" : 'Oracle Magazine', 
			"publisher" : 'Oracle Publishing', 
			"edition" : 'November December 2013',
			"title" : 'Engineering as a Service',
			"author" : 'David A. Kelly'
		}
> db.catalog.insert(doc1)
> doc2 = {
			"catalogId" : 2, 
			"journal" : 'Oracle Magazine', 
			"publisher" : 'Oracle Publishing', 
			"edition" : 'November December 2013',
			"title" : 'Quintessential and Collaborative',
			"author" : 'Tom Haunert'
		}
> db.catalog.insert(doc2)
> db.catalog.find().pretty()
```
Ensuite, modifier
```
> db.catalog.update(
   { journal: 'Oracle Magazine'},
   {
      $set: { edition: '11­12­2013' },
      $inc: { catalogId: 2 }
   },
   {
     multi: true,
     writeConcern: { w: 1, wtimeout: 5000 }
   }
)
> db.catalog.find({ journal: 'Oracle Magazine'}).pretty()
```
### La fonction *save()*

En utilisant *save*, il faut spécifier le *_id*, sinon il fait un *insert*.

La commande *save* vous permet de simplifier la syntaxe. Le résultat est le même.

Syntaxe
```
db.collection.save(<document>,{writeConcern: <document>})
```
Exemple: On insert avant.
```
> use semaine02
> db.catalog.drop()
> doc1 = {
			"_id": ObjectId("507f191e810c19729de860ea"), 
			"catalogId" : 1,
			"journal" : 'Oracle Magazine', 
			"publisher" : 'Oracle Publishing',
			"edition": 'November December 2013',
			"title" : 'Engineering as a Service',
			"author" : 'David A. Kelly'
		}
> db.catalog.insert(doc1)
> db.catalog.find().pretty()
```
Construire un document avec le même *_id*
```
> doc1 = {
			"_id": ObjectId("507f191e810c19729de860ea"), 
			"catalogId" : 1, 
			"journal" : 'Oracle Magazine', 
			"publisher" : 'Oracle Publishing', 
			"edition" : '11122013',
			"title" : 'Engineering as a Service',
			"author" : 'Kelly, David A.'
		}
> db.catalog.save(doc1,{ writeConcern: { w: "majority", wtimeout: 5000 } })
> db.catalog.find().pretty()
```
Modifier la structure d'un document existant
```
doc2 = {
			"_id": ObjectId("507f191e810c19729de860ea"), 
			"catalogId" : 1,
			"journal" : 'Oracle Magazine', 
			"publisher" : 'Oracle Publishing', 
			"edition" : 'November December 2013'
		}
> db.catalog.drop()
> db.catalog.insert(doc2)
> db.catalog.find().pretty()
```
Ajout des champs title et author
```
> doc2 = {
			"_id": ObjectId("507f191e810c19729de860ea"),
			"catalogId" : 2,
			"journal" : 'Oracle Magazine', 
			"publisher" : 'Oracle Publishing', 
			"edition" : '11122013',
			"title" : 'Quintessential and Collaborative',
			"author" : 'Tom Haunert'
		}
> db.catalog.save(doc2)
> db.catalog.find().pretty()
```
Modifier ou ajouter si le document n'existe pas
```
> db.catalog.drop()
> doc2 = {
			"_id": ObjectId("507f191e810c19729de860ea"),
			"catalogId" : 2,
			"journal" : 'Oracle Magazine', 
			"publisher" : 'Oracle Publishing', 
			"edition": 'November December 2013'
		}
> db.catalog.insert(doc2)
> db.catalog.find().pretty()
> doc2 = {
			"_id": ObjectId("507f191e810c19729de860eb"),
			"catalogId" : 3,
			"journal" : 'Oracle Magazine', 
			"publisher" : 'Oracle Publishing', 
			"edition" : '11122013',
			"title" : 'Quintessential and Collaborative',
			"author" : 'Tom Haunert'
		}
> db.catalog.save(doc2)
> db.catalog.find().pretty()
```
Sans préciser le *_id*
```
> db.catalog.drop()
> doc2 = {
			"catalogId" : 2, 
			"journal" : 'Oracle Magazine', 
			"publisher": 'Oracle Publishing', 
			"edition" : 'November December 2013'
		}
> db.catalog.insert(doc2)
> db.catalog.find().pretty()
```
Ajout des champs title et author
```
> doc2 = {
			"catalogId" : 3, 
			"journal" : 'Oracle Magazine', 
			"publisher": 'Oracle Publishing', 
			"edition" : '11122013',
			"title" : 'Quintessential and Collaborative',
			"author" : 'Tom Haunert'
		}
> db.catalog.save(doc2)
> db.catalog.find().pretty()
```
Quand utiliser *update* ou bien *save*?
```
> db.media.update( 
	{ 
		"Title" : "Matrix, The"
	}, 
	{
		"Type" : "DVD", 
		"Title" : "Matrix, The", 
		"Released" : "1999", 
		"Genre" : "Action"
	}, 
	{ upsert: true} 
)
> db.media.find().pretty()
```
```
> db.media.save( 
	{ 
		"Title" : "Matrix, The"
	}, 
	{
		"Type" : "DVD", 
		"Title" : "Matrix, The", 
		"Released" : "1999", 
		"Genre" : "Drama"
	}
)
> db.media.find({"Title" : "Matrix, The"}).pretty()
```
Opérateur *$inc*: incrémente une valeur
* Performe une opération *atomic*.
* Si la champ n'existe pas, il sera créé.
```
> manga = ( 
	{ 
		"Type" : "Manga", 
		"Title" : "One Piece", 
		"Volumes" : 612,
		"Read" : 520 
	} 
)
> db.media.insert(manga)
> db.media.find({"Type" : "Manga"}).pretty()

> db.media.update ( { "Title" : "One Piece"}, {$inc: {"Read" : 4} } )
> db.media.find ( { "Title" : "One Piece" } ).pretty()
```
Opérateur *$set*: change la valeur, sinon crée la champ avec la valeur
```
> db.media.update ( 
	{ 
		"Title" : "Matrix, The" 
	},
	{
		$set : { Genre :"Sci-Fi" } 
	} 
)
> db.media.find ( { "Title" : "Matrix, The" } ).pretty()
```
Opérateur *$unset*: supprime le champ
```
> db.media.update ( {"Title": "Matrix, The"}, {$unset : { "Genre" : 1 } } )
> db.media.find ( { "Title" : "Matrix, The" } ).pretty()
```

Opérateur *$push*: permet d'ajouter à la fin, *append*
```
> db.media.update ( {"ISBN" : "978-1-4302-5821-6"}, {$push: { Author : "Griffin, Stewie"} } )
> db.media.find ( { "ISBN" : "978-1-4302-5821-6" } ).pretty()
```
Note: Ne s'applique qu'à un tableau
```
> db.media.update ( {"ISBN" : "978-1-4302-5821-6"}, {$push: { Title : "This isn't an array"} } )
Cannot apply $push/$pushAll modifier to non-array
```
Spécifier plusieurs valeurs pour un tableau
```
> db.media.update( 
	{ 
		"ISBN" : "978-1-4302-5821-6" 
	}, 
	{ 
		$push: 
		{ 
			Author : 
			{ 
				$each: ["Griffin, Peter", "Griffin, Brian"] 
			} 
		} 
	} 
)
> db.media.find ( { "ISBN" : "978-1-4302-5821-6" } ).pretty()
```
Utiliser *$slice* pour limiter la taille du tableau lors du *push* (c'est optionnel)

Prend une valeur négative ou 0
```
> db.media.update( 
	{ 
		"ISBN" : "978-1-4302-5821-6" 
	}, 
	{ 
		$push: 
		{ 
			Author : 
			{ 
				$each: ["Griffin, Meg", "Griffin, Louis"], 
				$slice: -2 
			} 
		} 
	} 
)
> db.media.find ( { "ISBN" : "978-1-4302-5821-6" } ).pretty()
```
Opérateur *$addToSet*: ajoute des données dans un tableau

La différence avec *$push* c'est qu'il n'ajoute qu'une valeur qui n'est pas présente (pas de doublons)
```
> db.media.update( 
	{ 
		"ISBN" : "1-4302-3051-7" 
	}, 
	{
		$addToSet : { Author : "Griffin, Brian" } 
	} ,
	{ upsert: true}
)
> db.media.find ( { "ISBN" : "1-4302-3051-7" } ).pretty()
> db.media.update( 
	{ 
		"ISBN" : "1-4302-3051-7" 
	}, 
	{
		$addToSet : 
		{ 
			Author : { $each : ["Griffin, Brian","Griffin, Meg"] } 
		} 
	} 
)
> db.media.find ( { "ISBN" : "1-4302-3051-7" } ).pretty()
```
Supprimer des éléments dans un tableau

Opérateur *$pop*: supprime un seul élément
```
> db.media.update( { "ISBN" : "1-4302-3051-7" }, {$pop : {Author : 1 } } )
> db.media.find ( { "ISBN" : "1-4302-3051-7" } ).pretty()
> db.media.update( { "ISBN" : "1-4302-3051-7" }, {$pop : {Author : -1 } } )
> db.media.find ( { "ISBN" : "1-4302-3051-7" } ).pretty()
```
Opérateur *$pull*: supprime chaque occurence
```
> db.media.update ( 
	{
		"ISBN" : "1-4302-3051-7"
	}, 
	{
		$pull: { Author : "Griffin, James" } 
	} 
)
> db.media.find ( { "ISBN" : "1-4302-3051-7" } ).pretty()
> db.media.update ( 
	{
		"ISBN" : "1-4302-3051-7"
	}, 
	{
		$pull : { Author : "Griffin, Stewie" } 
	} 
)
> db.media.find ( { "ISBN" : "1-4302-3051-7" } ).pretty()
```
Opérateur *$pullAll*: supprime tous les éléments
```
> db.media.update( 
	{ 
		"ISBN" : "1-4302-3051-7"
	}, 
	{
		$pullAll : 
		{ 
			Author : ["Griffin, Louis","Griffin, Peter","Griffin, Brian"] 
		} 
	} 
)
> db.media.find ( { "ISBN" : "1-4302-3051-7" } ).pretty()
```
Préciser une position dans le tableau
```
> db.media.update( 
	{ 
		"Artist" : "Nirvana" 
	}, 
	{
		$addToSet : 
		{ 
			Tracklist : {"Track" : 2,"Title": "Been a Son", "Length":"2:23"} 
		} 
	} 
)
> db.media.find ( { "Artist" : "Nirvana" } ).pretty()
```
### La fonction *findAndModify*
* Prend 3 paramètres query, sort, operations
* Utilise tous les opérateurs de modification sauf $set

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
Modifier, trier, upsert
```
> db.catalog.findAndModify({
	query: {journal : "Oracle Magazine"},
	sort: {catalogId : 1},
	update: {$inc: {catalogId: 3}, $set: {edition: '11122013'}},
	upsert :true,
	new: true,
	fields: {catalogId: 1, edition: 1, title: 1, author: 1}
})
> db.getCollection('catalog').find({}).pretty()
```
Exception, ne pas combiner *upsert* avec *remove*
```
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
## La fonction supprimer

Bonne pratique:

C'est recommandé de faire un find() pour retrouver la donnée en premier avant de supprimer.

Syntaxe
```
db.collection.remove(
	<query>,
	{
		justOne: <boolean>,
		writeConcern: <document>
	}
)
```

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-02/Deleting-data-in-MongoDB.PNG)

Supprime toutes les documents ayant ce Title.
```
> db.catalog.drop()
> doc1 = {
			"_id" : ObjectId("53fb4b08d17e68cd481295d5"),
			"catalogId" : 1, 
			"journal" : 'Oracle Magazine',
			"publisher" : 'Oracle Publishing', 
			"edition" : 'November December 2013',
			"title": 'Engineering as a Service',
			"author" : 'David A. Kelly'
		}
> db.catalog.insert(doc1)
> db.catalog.find().pretty()
> doc2 = {
			"_id" : ObjectId("53fb4b08d17e68cd481295d6"), 
			"catalogId" : 2, 
			"journal" : 'Oracle Magazine',
			"publisher" : 'Oracle Publishing', 
			"edition" : 'November December 2013',
			"title": 'Quintessential and Collaborative',
			"author" : 'Tom Haunert'
		}
> db.catalog.insert(doc2)
> db.catalog.find().pretty()

> doc3 = {
			"_id" : ObjectId("53fb4b08d17e68cd481295d7"), 
			"catalogId" : 3, 
			"journal" : 'Oracle Magazine',
			"publisher" : 'Oracle Publishing', 
			"edition" : 'November December 2013'
		}
> db.catalog.insert(doc3)
> db.catalog.remove({ catalogId: 2 })
> db.catalog.find().pretty()
```
Autres exemples avec le paramètre *justOne*
```
> db.catalog.remove(
	{ catalogId: { $gt: 1 } },
	{ justOne:true, writeConcern: { w: 1, wtimeout: 5000 } }
)
> db.catalog.find().pretty()
```
Supprime tous les documents dans une collection
```
> db.media.remove({})
> db.catalog.remove({})
```
Supprimer la collection et les documents
```
> db.media.drop()
true
> db.catalog.drop()
true
```
Particularité d'une collection fixe, *capped*
* On ne peut pas supprimer des documents.
* Il faut faire *drop* et recréer.
* Il supprime la plus ancienne valeur et insère de façon circulaire.
```
> db.createCollection("catalog", {capped: true, size: 64 * 1024, max: 1000} )
```
Supprimer la base de données courante
```
> db.dropDatabase()
{ "dropped" : "library", "ok" : 1 }
```

### [Exercices] (https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-02/Exercices.md)

### [Réponses] (https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-02/Reponses.md)

## References
* David Hows; Peter Membrey; Eelco Plugge; Tim Hawkins. The Definitive Guide to MongoDB: A complete guide to dealing with Big Data using MongoDB, Third Edition. Apress. 16-DEC-2015
