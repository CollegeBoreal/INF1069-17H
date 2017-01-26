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

Questions

1. Quels sont les titres (titles) dont le nombre de pages est plus grand que 200 et l'année de publication est avant 2010.

2. Quels sont les auteurs dont le nom ne contient pas "Shive" parmis les titres (titles).

3. Quel est le title qui a été enregistré en premier.

4. Combien y a-t-il de livres (books) qui ne sont pas de catégories Java.

5. Quels sont les 3 derniers livres (books) enregistrés.

6. Quels sont les livres (books) qui ont été publiés après le 1er mars 2005.

7. Afficher le titre, le isbn et les auteurs des 5 premiers livres (books) triés en order croissant de _id qui ont une date de publication.

8. Une application affiche tous les livres en order croissant selon le _id. Quels sont les livres à la page 4, si on affiche 5 par pages.

9. Combien de livres (books) ont été publiés (status).

10. Combien de livres (books) dont la catégorie est Networking ou qui n'ont pas été publiés.
