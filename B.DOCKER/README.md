# Docker

## Installation Machine Hôte (Windows, Mac OS X)

. Installer Docker Toolbox

https://www.docker.com/products/docker-toolbox

. Installer INF1006 dans le dossier ~/Developer

```
$ git clone git@github.com:CollegeBoreal/INF1069-17H.git
```

. changer de repertoire

```
$ cd INF1006-16A
```

. Installer MongoDB

```
$ docker run -v "$(pwd)":/data --name mongo -d mongo mongod --smallfiles
```

. Executer la commande d'accer a MySQL

```
$ docker exec -it mongo bash
```
