package semaine09;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * INF1069-17H
 * This class connects to MongoDB and prints the list of collections from a database.
 * By Steve Tshibangu <a>Steve.TshibanguMutshi@collegeboreal.ca</a>
 */
public class MongoDBGetCollectionName {
    public static void main(String[] args) {
        MongoClient mongoClient = null;
        MongoDatabase mongoDatabase = null;
        String server = null;
        int port = 0;
        String database = null;

        try {
            // Server name
            server = "10.0.2.2";

            // Port number
            port = 27018;

            // Database name
            database = "semaine05";

            // Connect to MongoDB server
            mongoClient = new MongoClient(server, port);

            // Connect to your database
            mongoDatabase = mongoClient.getDatabase(database);

            // Print message
            System.out.println("Connect to database successfully ");

            /*** Print collection names */
            System.out.println("List of collections:");

            for (String collectionName : mongoDatabase.listCollectionNames()) {
                System.out.println(collectionName);
            }

            // close the connection
            mongoClient.close();
        } catch(Exception e) {
            // Print errors
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
