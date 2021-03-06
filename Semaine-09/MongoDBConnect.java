package semaine09;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * This class connects to MongoDB and prints the list of databases.
 * Author : Steve Tshibangu
 * Email: steve.tshibangu-mutshi.1@collegeboreal.ca
 * Course: INF1069
 * Date : 2017-02-02
 */
public class MongoDBConnect {
    public static void main(String[] args) {
        MongoClient mongoClient = null;
        @SuppressWarnings("unused")
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
            database = "semaine04";

            // Connect to MongoDB server
            mongoClient = new MongoClient(server, port);

            // Connect to your database
            mongoDatabase = mongoClient.getDatabase(database);

            // Print message
            System.out.println("Connect to database successfully.");

            /*** Print list of databases from the server. */
            System.out.println("List of databases:");

            for (String databaseName : mongoClient.listDatabaseNames()) {
                System.out.println(databaseName);
            }

            // close the connection
            mongoClient.close();
        } catch(Exception e) {
            // Print errors
            System.err.println(e.toString());
        }
    }
}
