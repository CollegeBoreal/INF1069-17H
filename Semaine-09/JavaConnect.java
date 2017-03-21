package semaine09;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.WriteConcern;

/**
 * INF1069-17H
 * This class connects to MongoDB and prints the number of documents
 * from collection called word_stats.
 * By Steve Tshibangu <a>Steve.TshibanguMutshi@collegeboreal.ca</a>
 */
public class JavaConnect {
    public static void main(String[] args) {
        MongoClient mongoClient = null;
        MongoDatabase mongoDatabase = null;
        MongoCollection collection = null;

        try {
            // ***Change the server and port to match your database connection
            mongoClient = new MongoClient("10.0.2.2", 27018);
            mongoClient.setWriteConcern(WriteConcern.JOURNAL_SAFE);
            mongoDatabase = mongoClient.getDatabase("semaine09");
            collection = mongoDatabase.getCollection("word_stats");
            System.out.println("Number of Documents: " +
                    new Long(collection.count()).toString());
        } catch (Exception e) {
            // Print errors
            System.out.println(e);
        }
    }
}
