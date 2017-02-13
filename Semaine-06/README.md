# Modélisation avec Mongo DB

Ce tableau compare les termes utilisés pour chaque objet à travers le modèle de données, RDBMS et MongoDB.

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-06/Objects-across-the-data-model-RDBMS-and-MongoDB.PNG)

## Entities

It represents a collection of information about something that the business deems important and worthy of capture.

Entities can exist at conceptual, logical, and physical levels of detail. At a universal level, there are certain concepts common to all such as Customer, Product, and Employee.

Making the scope slightly narrower, a given industry may have certain unique concepts. For example, Phone Number, may be a valid concept for a telecommunications company but perhaps not for other industries such as manufacturing.

Six categories: who, what, when, where, why, or how

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-06/Entities-who-what-when-where-why-or-how.PNG)

## Attributes

An attribute is an elementary piece of information of importance to the business that identifies, describes, or measures instances of an entity. The attribute Student Last Name describes the last name of each student.

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-06/Attributes-order.PNG)

### Reminder about naming rules for attributes

* Avoid special characters: dollar sign, or null (\0).
* MongoDB is case sensitive. **FirstName** and **firstName** are distinct fields.
* Duplicate names are not allowed.

## Domains

A domain is the complete set of all possible values that an attribute contains. **Gender**, for example, can be limited to the domain of (female, male).

Main types of domains in relational databases.

* Format. A format domain restricts the length and type of the data element. 
  Example: Character(15) format domain limiting the possible values at most 15 characters.
* List. A list domain limits the values to a specified defined set.
  Example: female or male.
* Range. A range domain restricts the data element values to any value between two other values. 
  Example: A start and end dates.
 
![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-06/Main-datatype-domains.PNG)

## Relationships

A relationship is displayed as a line connecting two entities that captures the rule or navigation path between them. For example, Employee and Department, the relationship can capture the rules "Each Employee must work for one Department" and "Each Department may contain one or many Employees."

In a relationship between two entities, cardinality captures how many instances from one entity participate in the relationship with instances of the other entity. It is through cardinality that the data rules are specified and enforced.

Example:  The business rules in this example are:

Each Author may write one or many Titles.

Each Title must be written by one Author.

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-06/BR-author-title-1.PNG)

Let's change the cardinality and now allow a Title to be written by more than one Author:

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-06/BR-author-title-2.PNG)

### One-to-one

Most of the time, we will map this relationship with embedded documents, especially if it is a "contains" relationship.

Referenced data:

```
customer
{
  "_id": 5478329cb9617c750245893b
  "username" : "John Clay",
  "email": "johnclay@crgv.com",
  "password": "bf383e8469e98b44895d61b821748ae1"
}
customerDetails
{
  **"customer_id": "5478329cb9617c750245893b"**,
  "firstName": "John",
  "lastName": "Clay",
  "gender": "male",
  "age": 25
}
```
With embedded data:

```
customer
{
  _id: 1
  "username" : "John Clay",
  "email": "johnclay@crgv.com",
  "password": "bf383e8469e98b44895d61b821748ae1"
  **"details": 
  {
    "firstName": "John",
    "lastName": "Clay",
    "gender": "male",
    "age": 25
  }**
}
```

### One-to-many

In order to decide when to embed or to make references, we must consider the "many" side of the relationship. If the many side should be displayed with its parents, then we should choose to embed the data; otherwise, we can use references on the parents.

```
customer
{
  _id: 1,
  "username" : "John Clay",
  "email": "johnclay@crgv.com",
  "password": "bf383e8469e98b44895d61b821748ae1"
  "details": {
    "firstName": "John",
    "lastName": "Clay",
    "gender": "male",
    "age": 25
    }
  "billingAddress": [{
    "street": "Address 1, 111",
    "city": "City One",
    "state": "State One",
    "type": "billing",
    }],
  "shippingAddress": [{
    "street": "Address 2, 222",
    "city": "City Two",
    "state": "State Two",
    "type": "shipping"
    },
    {
    "street": "Address 3, 333",
    "city": "City Three",
    "state": "State Three",
    "type": "shipping"
    }]
}
```

### Many-to-many

In the relational world, this kind of relationship is often represented as a join table while, in the nonrelational one, it can be represented in many different ways.

### Recursion

A recursive relationship is a relationship that starts and ends on the same entity. A one-to-many recursive relationship describes a hierarchy: an entity instance has, at most, one parent. A many-to-many relationship describes a network: an entity instance can have more than one parent.

Example: A one-to-many recursive relationship.

Each Employee may manage one or many Employees.

Each Employee may be managed by one Employee.

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-06/One-to-many-recursive-example-employee.PNG)

Example: Many-to-many relationship.

Each Employee may manage one or many Employees.

Each Employee may be managed by one or many Employees.

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-06/Many-to-many-recursive-example-employee.PNG)

## Keys

A key is one or more attributes whose purposes include enforcing rules, efficiently retrieving data and allowing navigation from one entity to another.

### Candidate key (primary and alternate keys)  

A candidate key is one or more data elements that uniquely identify an entity instance.

* Unique. There cannot be duplicate values in the data in a candidate key, and it cannot be empty (also known as nullable).
* Stable. A candidate key value on an entity instance should never change.
* Minimal. A candidate key should contain only those attributes that are needed to uniquely identify an entity instance.

### Surrogate key

A surrogate key is a unique identifier for a row in a table, often a counter, that is always system-generated without intelligence, meaning a surrogate key contains values whose meanings are unrelated to the entities they identify.

Example: Globally Unique Identifiers, or GUIDs; MongoDB ObjectId, etc.

### Foreign key

The entity on the "one" side of the relationship is called the parent entity, and the entity on the "many" side of the relationship is called the child entity.

MongoDB Reference = RDBMS Foreign Key

In MongoDB, the reference is simply a way to navigate to another structure (without the referential integrity)

### Secondary key

A secondary key is one or more data elements (if there is more than one data element, it is called a composite secondary key) that are accessed frequently and need to be retrieved quickly.

MongoDB Non-Unique Index = RDBMS Secondary Key

### Subtype

Subtyping groups the common attributes and relationships of entities, while retaining what is special within each entity.

Example: An Author may write a PrintVersion or eBook. They both are subtype of Title.

## Embedding vs. Referencing Information in Documents

You can choose either to embed information into a document or reference that information from another document. Embedding information simply means that you place a certain type of data (for example, an array containing more data) into the document itself. Referencing information means that you create a reference to another document that contains that specific data.

## Reminder for data types

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-06/Types-of-data-representation-in-BSON.PNG)

## References
* Steve Hoberman. Data Modeling for MongoDB. Technics Publications. 09-JUN-2014.
