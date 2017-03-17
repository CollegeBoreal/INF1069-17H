package semaine09;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

/**
 * INF1069-17H
 * This class connects to MongoDB and deletes a document.
 * By Steve Tshibangu <a>Steve.TshibanguMutshi@collegeboreal.ca</a>
 */
public class MongoDBDeleteDocument {
    public static void main(String[] args) {
        MongoClient mongoClient = null;
        MongoDatabase mongoDatabase = null;
        MongoCollection<Document> collection = null;
        FindIterable<Document> iterable = null;
        DeleteResult deleteResult = null;
        Set<String> keySet = null;
        Iterator<String> iterator = null;
        Document document = null;
        Document documentDeleted = null;
        String documentKey = null;
        String server = null;
        int port = 0;
        String database = null;

        try {
            // Server name
            server = "10.0.2.2";

            // Port number
            port = 27018;

            // Database name
            database = "semaine09";

            // Connect to server
            mongoClient = new MongoClient(
                            Arrays.asList(new ServerAddress(server, port)));

            // Get the database
            mongoDatabase = mongoClient.getDatabase(database);

            // Get the collection
            collection = mongoDatabase.getCollection("catalogDelete");

            // Create document 1
            document = new Document("catalogId", "catalog1")
                        .append("journal", "Oracle Magazine")
                        .append("publisher", "Oracle Publishing")
                        .append("edition", "November December 2013")
                        .append("title", "Engineering as a Service")
                        .append("author", "David A. Kelly");

            // Insert a document 1
            collection.insertOne(document);

            // Create document 2
            document = new Document("catalogId", "catalog2")
                        .append("journal", "Oracle Magazine")
                        .append("publisher", "Oracle Publishing")
                        .append("edition", "November December 2013")
                        .append("title", "Quintessential and Collaborative")
                        .append("author", "Tom Haunert");

            // Insert document 1
            collection.insertOne(document);

            // Create document 3
            document = new Document("catalogId", "catalog3")
                        .append("journal", "Oracle Magazine")
                        .append("publisher", "Oracle Publishing")
                        .append("edition", "November December 2013");

            // Insert document 3
            collection.insertOne(document);

            // Create document 4
            document = new Document("catalogId", "catalog4")
                        .append("journal", "Oracle Magazine")
                        .append("publisher", "Oracle Publishing")
                        .append("edition", "November December 2013");

            // Insert document 4
            collection.insertOne(document);

            // Delete one document
            deleteResult = collection.deleteOne(
                            new Document("catalogId", "catalog1"));

            // Print results
            System.out.println("Number of documents deleted: "
                    + deleteResult.getDeletedCount());

            // Find and delete
            documentDeleted = collection.findOneAndDelete(
                                new Document("catalogId", "catalog2"));

            // Print results
            System.out.println("Document deleted: " + documentDeleted);

            // Delete many documents
            deleteResult = collection.deleteMany(new Document());

            // Print results
            System.out.println("Number of documents deleted: "
                    + deleteResult.getDeletedCount());

            // Find all documents
            iterable = collection.find();

            // Print results
            for (Document doc : iterable) {
                keySet = doc.keySet();
                iterator = keySet.iterator();

                while (iterator.hasNext()) {
                    documentKey = iterator.next();
                    System.out.println(
                            documentKey +
                            "\t" +
                            doc.get(documentKey));
                }
            }

            // close the connection
            mongoClient.close();
        } catch(Exception e) {
            // Print errors
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
