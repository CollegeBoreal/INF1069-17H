# Mongo Docker

## Run mongo with authentication

```
$ docker run --name some-mongo -d mongo --auth
```

## Add the Initial Admin User

* execute the admin command to get mongo's shell prompt

```
$ docker exec -it some-mongo mongo admin
```


```
> db.createUser({ 
	user: 'etudiants', 
	pwd: 'etudiants_1', 
	roles: [ { 
		role: "userAdminAnyDatabase", db: "admin" 
	} ] 
});
```
