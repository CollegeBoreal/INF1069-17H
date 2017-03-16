# Les fonctions de recherche - Exercices

Utiliser la base de données *semaine03*

Insérer les données suivantes (titles)

```
Title Name                    Page Count 	  Publication Year 	          Author 				        Amazon Review
-----------------------------------------------------------------------------------------------------
Extreme Scoping               300 		      2013 				          Larissa Moss 		      4.5

Data Engineering              100 		      2013 				          Brian Shive 		      4.25

Business unIntelligence 	  442 		      2013 				          Barry Devlin, PhD 	  5

Data Modeling Made Simple     250 		      2009 				          Steve Hoberman 		  4.35
```
Importer les documents suivants:

```
$ mongoimport --db semaine03 --collection books --type json --drop  --file catalog.books.json
$ mongoimport --db semaine03 --collection companies --type json --drop  --file companies.json
```

__Question 1__

Quels sont les titres (titles) dont le nombre de pages est plus grand que 200 et l'année de publication est avant 2010.

__Question 2__

Quels sont les auteurs dont le nom ne contient pas "Shive" parmis les titres (titles).

__Question 3__

Quel est le title qui a été enregistré en premier.

__Question 4__

Combien y a-t-il de livres (books) qui ne sont pas de catégories Java.

__Question 5__

Quels sont les 3 derniers livres (books) enregistrés.

__Question 6__

Quels sont les livres (books) qui ont été publiés après le 1er mars 2005.

__Question 7__

Afficher le titre, le isbn et les auteurs des 5 premiers livres (books) triés en order croissant de _id qui ont une date de publication.

__Question 8__

Une application affiche tous les livres en order croissant selon le _id. Quels sont les livres à la page 4, si on affiche 5 par pages.

__Question 9__

Combien de livres (books) ont été publiés (status).

__Question 10__

Combien de livres (books) dont la catégorie est Networking ou qui n'ont pas été publiés.


### [Voir les réponses](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-03/Reponses.md)
