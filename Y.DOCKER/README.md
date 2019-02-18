# Mongo Docker

## Run mongo with authentication

```
$ docker run --name some-mongo --publish 27017:27017 --detach mongo --auth
```

## Add the Initial Admin User

* execute the admin command to get mongo's shell prompt

	. Connect to the container

```
$ docker exec --interactive --tty some-mongo bash
```

	. Launch mongo CLI
   
```
# mongo admin
```



* create the user admin

```
> db.createUser({ 
	user: 'etudiants', 
	pwd: 'etudiants_1', 
	roles: [ { 
		role: "userAdminAnyDatabase", db: "admin" 
	} ] 
});
```

## Access the mongo CLI

* execute the bash command to gt onto the container

```
$ docker exec -it some-mongo bash
```

* execute the mongo CLI command

```
# mongo
MongoDB shell version v3.6.1
connecting to: mongodb://127.0.0.1:27017
MongoDB server version: 3.6.1
> 
```

* retrieve all collections

```
> db.collections;
test.collections
```

# Load Data
