# Docker

## Installation Machine Hôte (Windows, Mac OS X)

. Installer Docker Toolbox

https://www.docker.com/products/docker-toolbox

. Installer INF1006 dans le dossier ~/Developer

```
$ git clone git@github.com:CollegeBoreal/INF1006-16A.git
```

. changer de repertoire

```
$ cd INF1006-16A
```

. Installer MySQL

```
$ docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=password -p 3306:3306 -d mysql:latest 
```

. Executer la commande d'accer a MySQL

```
$ docker exec -it some-mysql bash
```
