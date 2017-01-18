# Les opérations CRUD

## Opérations atomic

Une opération Atomic est une combinaison de plusieurs opérations 
qui sont exécutées comme une seul - cad, soit l'opération réussie ou échoue.
On parle d'opération atomic si:

* Aucun processus n'est au courant du changement jusqu'à ce que le changement soit complété.
* Si une opération échoue, l'ensemble d'opérations est annulé (rollback).

## Règles sur la nomenclature d'une collection

* Pas plus que 128 caractères.
* Ne peut pas avoir un champ de caractère vide (" ") comme nom.
* Doit commencer par une lettre ou bien underscore _ 
** Exemple: 6019_inf est invalide
* Ne peut pas utiliser le mot system pour nom, car c'est une collection reservée par MongoDB.
* Ne peut pas contenir le caractère NULL (code ascii est "\0").
