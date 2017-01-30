# Les opérations CRUD - Exercices

Utiliser la base de données *semaine02*

__Question 1__

Ajouter un utilisateur dans la collection *user* avec comme nom d'usager Henry.

__Question 2__

Ajouter Canada comme pays d'origine pour Henry. Proposez plus qu'une solution.

__Question 3__

Ajouter Marie qui est originaire du Peru. Elle a deux types de passe temps (*hobbies*), dont films (*movies*) et *sports*. Assignez-lui 4 films et 2 sports de votre choix.

__Question 4__

Ajouter Paul qui est originaire de la ville de Paris. Paul aime le soccer et la boxe. Paul a 20 ans.

__Question 5__

On decide de ne plus avoir l'age de Paul.

__Question 6__

Expliquer cette commande: 
```
> db.user.update( {"username": "Tom"}, {$addToSet: {"favorites.movies": "The Maltese Falcon"} }, false, false)
```

__Question 7__

Expliquer cette commande:
```
> db.user.update({username: "moe"}, {$inc: {age: -1})
```

__Question 8__

Organiser l'enregistrement d'Henry afin qu'il ressemble à celui de Marie avec un sport de plus et on veut son age, soit 24 ans.

__Question 9__

Suprimmer tous les sports de James.

__Question 10__

Vous avez fait un typo sur les sports préférés de Paul. Mettez-les à jour.

__Question 11__

Ajouter les 4 usagers, Steve, Gary, Yves et Julien en une seule commande.

__Question 12__

Mettez-les à jour en une seule commande avec comme pays d'origine Japon.

__Question 13__

Ajouter le volleyball (sans doublons) comme sport à tous les usagers dont l'origine est Peru.

__Question 14__

Faire un backup de votre collection.

__Question 15__

Supprimer tous les documents sans supprimer la collection.

__Question 16__

Importer la collection user.

__Question 17__

Importer les collections customer et product. Notez combien de documents sont été modifiés pour chacune des requêtes suivantes.

__Question 18__

Ajouter les champs pays d'origine et description pour tous les produits.

__Question 19__

Incrémenter le prix unitaire de 5 pour tous les produits de categorie 2.

__Question 20__

Modifier le pays d'origine pour les produits pour tous les fournisseurs de type 2, pour Canada et de type 1 pour USA.

__Question 21__

Discontinuer tous les produits de catégorie 4.

__Question 22__

Changer la region à Brussel-Capital pour le customer dont le _id est *588069902cff4eba9ab01b1a*

__Question 23__

Notez tous les customers dont la valeur est NULL pour le champ field11.

__Question 24__

Supprimer le champ field11 et organiser les données pour ces customers.

__Question 25__

Supprimer tous les customers dont l'origine est UK.

__Question 26__

Mettre à jour tous les customers et les producits en ajoutant la date de dernère modification (lastModifiedDate), utiliser $currentDate et votre nom comme dernier à avoir modifier (lastModifiedBy)

### [Voir les réponses] (https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-02/Reponses.md)
