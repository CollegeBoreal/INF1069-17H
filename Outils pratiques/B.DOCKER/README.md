# Docker

## Installation Machine Hôte (Windows, Mac OS X)

. Installer Docker Toolbox
https://www.docker.com/products/docker-toolbox
. Installer INF1069 dans le dossier ~/Developer
```
$ git clone git@github.com:CollegeBoreal/INF1069-17H.git
```
. changer de repertoire
```
$ cd INF1069-17H
```
. Installer MongoDB
```
$ docker run -p 27017:27017 -v "$(pwd)":/data --name mongo -d mongo mongod --smallfiles
```
. Executer la commande pour lancer Docker
```
$ sudo service docker start
```
. Lancer le container MongoDB
```
$ docker start mongo
```
. Executer la commande d'accer à MongoDB
```
$ docker exec -it mongo bash
$ mongo
```
. Ou bien
```
$ sudo docker exec -it mongo mongo
```
.Importer des données dans MongoDB
```
$ mongoimport -d INF1069 -c regions --drop --type csv --file regions.csv --headerline
$ mongoimport -d INF1069 -c customers --drop --type json --file customers.json
$ mongorestore -d INF1069 -c customers customers.bson
```
. Exporter des données depuis MongoDB
```
$ mongoexport --db INF1069 --type=json --collection customers --out customers.bak.json
$ mongorestore --drop -d INF1069 -c customers customers.bak.bson
```
. Lire les données
```
$ db.getCollection('customers').find({})
```
