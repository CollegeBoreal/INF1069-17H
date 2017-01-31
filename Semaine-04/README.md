# Les fonctions d’agrégation

MongoDB propose un framework pour manipuler les fonctions et les opérations d'agrégation. Ces opérations permettent de présenter l'information pour analyser les données stockées dans les collections. Il comprend des fonctions dédiées, le mapReduce et les fonctions de pipeline. Le mapReduce sera présenté lors d'une autre séance.

Importer les collections suivantes pour manipuler les exemples.

```
$ mongoimport -d semaine04 -c books --drop --type json --file catalog.books.json
$ mongorestore -d semaine04 -c aggregation aggregation.bson
```

## Fonctions dédiées

### count()

Permet de compter le nombre d'élements.

```
db.collection.count(query)
```

Exemple: Le nombre de livres publiés après le 1er avril 2009.
```
> db.books.count({"publishedDate": {$gt: ISODate("2009-04-01")}})
```

### distinct()

Permet d'éviter les doublons.

```
db.collection.distinct(field, query)
```

Exemple: Les catégories de livres dont le status est publié.
```
> db.books.distinct('categories', {"status" : "PUBLISH"}).pretty()
```

### group()

Permet de faire des transformations simples sur des groupes de données.

```
db.collection.group({ key, reduce, initial [, keyf] [, cond] [, finalize] })
```

Exemple: Compter le nombre de livres par status.
```
> db.books.group ({
    key: {status : true},
    initial: {total : 0},
    reduce :  function (items,prev) {
                prev.total += 1
              }
}).pretty()
```

## Pipeline

Le résultat de chaque opération est le *input* de la suivante.

````
db.collection.aggregate([ {$match: ...}, {$group: ...}, {$sort: ...} ] )
db.collection.aggregate({pipeline document})
```

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-04/aggregation_pipeline.PNG)

SQL versus aggregation

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-04/SQL_versus_aggregation.PNG)

### $group

```
{ $group : { _id : "$color" } }
```

* *$group* est l'opération dans le pipeline.
* *_id* est le nouveau champs utilisé pour la clé.
* *$color* est la reference au champ manipulé dans la collection.

Exemple: Grouper les couleurs dans la collection aggregation.
```
> db.aggregation.aggregate( { $group : { _id : "$color" } } ).pretty()
```

Fonctions pour manipuler l'opération $group

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-04/group_functions.PNG)

Exemple: On veut compter le nombre de chaque couleur.
```
> db.aggregation.aggregate({ $group : { _id : "$color", count : { $sum : 1 } } }).pretty()
```

Le résutlat de est stocké dans la variable *count*.

Exemple: On veut compter le nombre de chaque couleur et le transport.
```
> db.aggregation.aggregate({ $group : { _id : { color: "$color", transport: "$transport"} , count : { $sum : 1 } }}).pretty()
```

### $limit

Permet de limiter le nombre de résultat retourné.

```
db.collection.aggregate({$limit:<number to limit>})
```

Exemple: On veut limiter le résultat de notre exemple précédent de 5.
```
> db.aggregation.aggregate( 
  [ 
    { $group : { _id : { color: "$color", transport:"$transport"} , count : { $sum : 1 } } }, 
    { $limit : 5 } 
  ]).pretty()
```

### $match

Permet de définir un critère de recherche.
C'est une bonne pratique de l'utiliser en début de notre pipeline pour améliorer la performance.

```
db.collection.aggregate({$match:{<expression>}})
```

Exemple: On veut les documents dont la valeur du *num* est supérieur à 500.
```
> db.aggregation.aggregate(
  [
    { $match : { num : { $gt : 500 } } },
    { $group : { _id : { color: "$color", transport: "$transport"} , count : { $sum: 1 } } },
    { $limit : 5 }
  ]).pretty()
```

### $sort

Permet de trier. On spécifie 1 pour l'ordre croissant et -1 pour décroissant pour le champs pour lequel on trie. 

```
> db.aggregation.aggregate(
  [
    { $match : { num : { $gt : 500 } } },
    { $group : { _id : { color: "$color", transport: "$transport"} , count : { $sum: 1 } } },
    { $sort : { _id :1 } },
    { $limit : 10 }
  ]).pretty()
```

### $unwind

Permet de générer un nouveau document en mémoire pour chaque élément d'un tableau.

Exemple: Si on prend le tableau des légumes (vegetables)
```
> db.aggregation.aggregate({ $unwind : "$vegetables" }).pretty()
```

Ainsi, pour le document ayant 3 types de vegetables, on aura 3 documents avec chacun une vegetable.

Note: Faire attention de ne pas sur-utiliser la mémoire avec cette opération.

### $project

Permet de controler les champs retournés. On peut limiter et/ou renommer les champs désirés. Le champ _id est retourné par défaut. On spécifie *true* ou *false* pour le champ à retourné; sinon 0 ou 1.

Exemple: Ne retourner que les champs *fruits* et *vegetables* de l'exemple précédent.
```
> db.aggregation.aggregate(
  [
    { $unwind : "$vegetables" },
    { $project : { _id: 0, fruits:1, vegetables:1 } }
  ]).pretty()
```

### $skip

Permet de sauter les X documents.

```
db.collection.aggregate({$skip:<number to skip>})
```

```
> db.aggregation.aggregate(
  [
    { $unwind : "$vegetables" },
    { $project : { _id: 0, fruits:1, vegetables:1 } },
    { $skip : 2995 }
  ]).pretty()
```

Note: Les deux opérations, $skip et $limit sont utilisés pour faire de la pagination.

### Autres opérateurs

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-04/date_functions.PNG)

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-04/arithmetic_functions.PNG)

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-04/string_functions.PNG)

### [Exercices] (https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-04/Exercices.md)

### [Réponses] (https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-04/Reponses.md)

## References
* Le site de documentation de MongoDB. Aggregation https://docs.mongodb.com/manual/aggregation/ (page accédée le 29 janvier 2017)
* David Hows; Peter Membrey; Eelco Plugge; Tim Hawkins. The Definitive Guide to MongoDB: A complete guide to dealing with Big Data using MongoDB, Second Edition. Apress. 06-NOV-2013.
* Douglas Garrett Peter Bakkum Kyle Banker Shaun Verch and Tim Hawkins. MongoDB in Action, Second Edition: Covers MongoDB version 3.0. Manning Publications. 05-APR-2016 
