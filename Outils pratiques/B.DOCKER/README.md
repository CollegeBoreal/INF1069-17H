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
$ docker run -v "$(pwd)":/data --name mongo -d mongo mongod --smallfiles
```

. Executer la commande d'accer à MongoDB
```
$ docker exec -it mongo bash
```
```
$ mongo
```
. Ou bien
```
sudo docker exec -it mongo mongo
```
.Importer des données dans MongoDB
```
$ mongoimport -d INF1069 -c regions --drop --type csv --file regions.csv --headerline
$ mongoimport -d INF1069 -c customers --drop --type json --file customers.json
```
. Exporter des données depuis MongoDB
```
$ mongoexport --db INF1069 --type=json --collection customers --out customers.bak.json
```
. Lire les données
```
$ db.getCollection('customers').find({})
```
