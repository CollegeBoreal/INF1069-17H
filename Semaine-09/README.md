# Développement en Java avec MongoDB

## Setting Up the Environment

Download Java SE Development Kit 8 Downloads

http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

Get Eclipse

https://www.eclipse.org/downloads/eclipse-packages/

## Creating a Maven Project

1. Select File New Other. In the New window, select the Maven Maven Project wizard and click on Next.

2. In New Maven Project wizard select the check boxes "Create a simple project" and "Use default Workspace location".

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-09/New-maven-project.PNG)

3. Next, configure the project, specifying the following values and then clicking on Finish

```
Group Id: mongodb.java
Artifact Id: MongoDBJava
Version: 1.0.0
Packaging: jar
Name: MongoDBJava
```

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-09/New-maven-project-artifactID.PNG)

4. Next, configure the Maven project by adding the required dependencies to the pom.xml configuration file. Add the Java MongoDB Driver dependency to the pom.xml. The pom.xml is
listed below. Click on File Save All to save the pom.xml.

See MongoDB driver version in Maven repository, https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver

```
<project xmlns="http://maven.apache.org/POM/4.0.0" 
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>mongodb.java</groupId>
	<artifactId>MongoDBJava</artifactId>
	<version>1.0.0</version>
	<name>MongoDBJava</name>
	<dependencies>
		<dependency>
			<groupId>org.mongodb</groupId>
			<artifactId>mongo-java-driver</artifactId>
			<version>3.4.0</version>
		</dependency>
	</dependencies>
</project>
```

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-09/Maven-project-mongodb-dependency.PNG)

## Connect to MongoDB

1. Create Java Package and give it the name semaine09.

Right-click on project name MongoDBJava, New > Package

2. Create new Java class

Enter Name: MongoDBConnect

Check box "public void static void main(String [] args)"

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-09/New-Java-Class.PNG)

3. Copy the contents from the class
![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-09/MongoDBConnect.java)

4. Run the Java Application

In the Project Explorer, right-click the MongoConnect.java class Run as > Java Application

5. You should see the printed message: "Connect to database successfully "

## Display all collections in the database

1. Create new Java class named MongoDBGetCollectionNames

2. Copy the contents from this class

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-09/MongoDBGetCollectionNames.java)

## Insert document

1. Create new Java class, MongoDBInsertDocument

2. Copy the contents from this class:

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-09/MongoDBInsertDocument.java)

## Update document

1. Create new Java class named MongoDBUpdateDocument

2. Copy the contents from this class

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-09/MongoDBUpdateDocument.java)

## Delete document

1. Create new Java class named MongoDBDeleteDocument

2. Copy the contents from the class

![alt tag](https://github.com/CollegeBoreal/INF1069-17H/blob/master/Semaine-09/MongoDBDeleteDocument.java)

## References
* Deepak Vohra. Pro MongoDB™ Development. Apress. 26-SEP-2015

