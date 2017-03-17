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
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;

/**
 * INF1069-17H
 * This class connects to MongoDB and updates documents into a collection.
 * By Steve Tshibangu <a>Steve.TshibanguMutshi@collegeboreal.ca</a>
 */
public class MongoDBUpdateDocument {
    public static void main(String[] args) {
        MongoClient mongoClient = null;
        MongoDatabase mongoDatabase = null;
        MongoCollection<Document> collection = null;
        FindIterable<Document> iterable = null;
        UpdateResult updateResult = null;
        Set<String> keySet = null;
        Iterator<String> iterator = null;
        Document catalog = null;
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
            collection = mongoDatabase.getCollection("catalogUpdate");

            // Create document 1
            catalog = new Document("catalogId", "catalog1")
                        .append("journal", "Oracle Magazine")
                        .append("publisher", "Oracle Publishing")
                        .append("edition", "November December 2013")
                        .append("title", "Engineering as a Service")
                        .append("author", "David A. Kelly");

            // Insert document 1
            collection.insertOne(catalog);

            // Create document 2
            catalog = new Document("catalogId", "catalog2")
                        .append("journal", "Oracle Magazine")
                        .append("publisher", "Oracle Publishing")
                        .append("edition", "November December 2013")
                        .append("title", "Quintessential and Collaborative")
                        .append("author", "Tom Haunert");

            // Insert document 2
            collection.insertOne(catalog);

            // Update one document
            collection.updateOne(
                    new Document("catalogId", "catalog1"),
                    new Document("$set", new Document("edition", "11-12 2013")
                                        .append("author", "Kelly, David A.")));

            // Update many documents
            collection.updateMany(new Document("journal", "Oracle Magazine"),
                    new Document("$set",
                            new Document("journal", "OracleMagazine")));

            updateResult = collection.replaceOne(
                            new Document("catalogId", "catalog3"),
                            new Document("catalogId", "catalog3")
                                    .append("journal", "Oracle Magazine")
                                    .append("publisher", "Oracle Publishing")
                                    .append("edition", "November December 2013")
                                    .append("title", "Engineering as a Service")
                                    .append("author", "David A. Kelly"),
                            new UpdateOptions().upsert(true));

            /*** Print results */
            System.out.println("Number of documents matched: "
                    + updateResult.getMatchedCount());

            System.out.println("Number of documents modified: "
                    + updateResult.getModifiedCount());

            System.out.println("Upserted Document Id: "
                    + updateResult.getUpsertedId().asObjectId().getValue());

            iterable = collection.find();

            /*** Print results */
            for (Document document : iterable) {
                keySet = document.keySet();
                iterator = keySet.iterator();

                while (iterator.hasNext()) {
                    documentKey = iterator.next();
                    System.out.println(
                            documentKey +
                            "\t" +
                            document.get(documentKey));
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
