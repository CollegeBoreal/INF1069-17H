# Les opérations CRUD - Exercices

Utiliser la base de données *semaine02*

1. Ajouter un utilisateur dans la collection *user* avec comme nom d'usager Henry.
2. Ajouter Canada comme pays d'origine pour Henry. Proposez plus qu'une solution.
3. Ajouter Marie qui est originaire du Peru. Elle a deux types de passe temps (*hobbies*), dont films (*movies*) et *sports*. Assignez-lui 4 films et 2 sports de votre choix.
4. Ajouter Paul qui est origine de la ville de Paris. Paul aime le soccer et la boxe. Paul a 20 ans.
5. On decide de ne plus avoir l'age de Paul.
6. Expliquer cette commande:
```
> db.user.update( {"username": "Tom"},
{$addToSet: {"favorites.movies": "The Maltese Falcon"} },
false,
false )
```
7. Expliquer cette commande: ```> db.user.update({username: "moe"}, {$inc: {age: -1})```
8. Organiser l'enregistrement d'Henry afin qu'il ressemble à celui de Marie avec un sport de plus et on veut son age, soit 24 ans.
9. Suprimmer tous les sports de James.
10. Vous avez fait un typo sur le sport préféré de Paul. Mettez-le à jour.
11. Ajouter les 4 usagers, Steve, Gary, Yves et Julien en une seule commande.
12. Mettez-les à jour en une seule commande avec comme pays d'origine Japon.
13. Ajouter le Volleyball (sans doublons) comme sport à tous les usagers dont l'origine est Peru
14. Faire un backup de votre collection
15. Supprimer tous les documents sans supprimer la collection
16. Importer vos données.
17. Importer les collections customer et product. Notez combien de documents sont été modifiés pour chacune des requêtes suivantes.
18. Ajouter les champs pays d'origine et description pour tous les produits
19. Incrémenter le prix unitaire de 5 pour tous les produits de categorie 2
20. Modifier les produits pour tous les fournisseurs de type 2, pour Canada et de type 1 pour USA
21. Discontinuer tous les produits de catégorie 4
22. Changer la region à Brussel-Capital pour le customer dont le _id est *588069902cff4eba9ab01b1a*
23. Notez tous les customers dont la valeur est NULL pour le champ field11
24. Supprimer le champ field11 et organiser les données pour ces customers
25. Supprimer tous les customers dont l'origine est UK
26. Mettre à jour tous les customers et les producits en ajoutant la date de dernère modification (lastModifiedDate), utiliser $currentDate et votre nom comme dernier à avoir modifier (lastModifiedBy)
