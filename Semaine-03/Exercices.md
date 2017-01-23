# Les fonctions de recherche - Exercices

Utiliser la base de données *semaine03*

Insérer les données suivantes

Title Name 					Page Count 	Publication Year 	Author 				Amazon Review

Extreme Scoping 			300 		2013 				Larissa Moss 		4.5

Data Engineering 			100 		2013 				Brian Shive 		4.25

Business unIntelligence 	442 		2013 				Barry Devlin, PhD 	5

Data Modeling Made Simple 	250 		2009 				Steve Hoberman 		4.35

```
$ mongoimport --db catalog --collection books --type json --drop  --file catalog.books.json
```

Questions

1. Les titres dont le nombre de pages est plus grand que 200 et l'année de publication est avant 2010.
2. Les auteurs dont le nom ne contient pas "Shive"