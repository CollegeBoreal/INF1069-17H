package semaine09;

import java.util.Iterator;
import java.util.Set;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

/**
 * This class implements exercices from week two.
 * Author : Steve Tshibangu
 * Email: steve.tshibangu-mutshi.1@collegeboreal.ca
 * Course: INF1069
 * Date : 2017-02-02
 */
public class ExerciceSemaine02 {
    static MongoClient mongoClient = null;
    static MongoDatabase mongoDatabase = null;
    static String server = null;
    static int port = 0;
    static String database = null;

    public static void main(String[] args) {
        // Connect to MongoDB Server
        connect();

        // Question 1
        //question1();

        // Question 2
        //question2();

        // Question 3
        question3();

        // Question 4
        //question4();
    }

    /**
     * This method connect to MongoDB server and initialize MongoClient.
     */
    public static void connect() {
        try {
            // Server name
            server = "10.0.2.2";

            // Port number
            port = 27018;

            // Database
            database = "semaine09";

            // To connect to MongDB server
            mongoClient = new MongoClient(server, port);

            // Now connect to your databases
            mongoDatabase = mongoClient.getDatabase(database);

            // Print message
            System.out.println("Connect to database successfully.");

            // close the connection
            //mongoClient.close();
        } catch(Exception e) {
            // Print errors
            System.err.println(e.toString());
        }
    }

    public static void question1() {
        Document document = null;
        MongoCollection collection = null;

        try {
            // Get the collection
            collection = mongoDatabase.getCollection("users");

            // Create the document
            document = new Document("username", "Henry");

            // Insert document
            collection.insertOne(document);
        } catch(Exception e) {
            // Print errors
            System.err.println(e.toString());
        }
    }

    public static void question2() {
        Document query = null;
        Document document = null;
        MongoCollection collection = null;
        UpdateResult updateResult = null;

        try {
            collection = mongoDatabase.getCollection("users");

            // Create query
            query = new Document("username", "Henry");

            // Create update document
            document = new Document();
            document.append("$set",  new Document("country", "Canada"));

            // Update
            updateResult = collection.updateOne(query, document);

            // Print results
            System.out.println("Update Result: \n" + updateResult.toString());
        } catch(Exception e) {
            // Print errors
            System.err.println(e.toString());
        }
    }

    public static void question3() {
        Document document = null;
        MongoCollection collection = null;

        try {
            // Get the collection
            collection = mongoDatabase.getCollection("users");

            // Create the document
            document = new Document("username", "Marie");
            document.append("country", "Peru");
            document.append("hobbies",
                    new Document("movies", "['Chicago', 'Star Wars', 'X-Men', 'Friday']")
                            .append("sports", "['Baseball', 'Tennis']"));

            // Insert document
            collection.insertOne(document);
        } catch(Exception e) {
            // Print errors
            System.err.println(e.toString());
        }
    }

    public static void question4() {
        Document document = null;
        MongoCollection collection = null;

        try {
            // Get the collection
            collection = mongoDatabase.getCollection("users");

            // Create the document
            document = new Document("username", "Paul");
            document.append("city", "Paris");
            document.append("hobbies", new Document("sports", "['Soccer', 'Boxing']"));
            document.append("age", 20);

            // Insert document
            collection.insertOne(document);
        } catch(Exception e) {
            // Print errors
            System.err.println(e.toString());
        }
    }

    public static void question5() {
        Document query = null;
        Document document = null;
        MongoCollection collection = null;
        UpdateResult updateResult = null;

        try {
            collection = mongoDatabase.getCollection("users");

            // Create query
            query = new Document("username", "Paul");

            // Create update document
            document = new Document();
            document.append("$unset",  new Document("age", 1));

            // Update
            updateResult = collection.updateOne(query, document);

            // Print results
            System.out.println("Update Result: \n" + updateResult.toString());
        } catch(Exception e) {
            // Print errors
            System.err.println(e.toString());
        }
    }
}
