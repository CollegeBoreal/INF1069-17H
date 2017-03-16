# Les fonctions d’agrégation - Exercices

Importer les documents suivants:

```
$ mongoimport --db semaine04 --collection IndiaStates --type json --drop  --file IndiaStates.json
$ mongoimport --db semaine04 --collection cityInspections --type json --drop  --file cityInspections.json
```

__Question 1__

Expliquez la commande suivante.

```
> db.IndiaStates.aggregate({
    $project:{state:1, _id:0, capital:"$info.capital"}
  })
```

__Question 2__

Expliquez la commande suivante.

```
> db.IndiaStates.aggregate(
    [
        {$match: { "info.capital": { $exists: true}}},
        {$project:{"state":1, "_id":0, capital:"$info.capital"}}
    ]
)
```

__Question 3__

Comment réécrire la commande suivante en utilisant les fonctions d'agrégation.
```
> db.IndiaStates.find({"state":/PRADESH/i})
```

__Question 4__

Dans votre collections d'états en Inde, vous avez une liste des villes.
Vous devez trouver le nombre de villes regroupées par la première lettre d'alphabet du nom et trouver les trois premiers alphabets de les plus courants des villes.

__Question 5__

Trouvez l'état le plus large.

__Question 6__

Trouvez combien il y a eu des inspéctions par mois en 2015. Présentez le résulat en ordre décroissant du mois.

__Question 7__

Trouvez le mois ayant le plus de réussite ("Pass").

__Question 8__

Trouvez la ville la plus inspéctée.

__Question 9__

Trouvez le pourcentage des Warning.

__Question 10__

Présentez pour chaque ville, la somme des inspéctions par secteur.

### [Voir les réponses](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-04/Reponses.md)
