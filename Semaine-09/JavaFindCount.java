package semaine09;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * INF1069-17H
 * This class implements an example for count function.
 * By Steve Tshibangu <a>Steve.TshibanguMutshi@collegeboreal.ca</a>
 */
public class JavaFindCount {
    public static void main(String[] args) {
        MongoClient mongoClient = null;
        MongoDatabase mongoDatabase = null;
        MongoCollection collection = null;

        try {
            // ***Change the server and port to match your database connection
            mongoClient = new MongoClient("10.0.2.2", 27018);
            mongoDatabase = mongoClient.getDatabase("semaine09");
            collection = mongoDatabase.getCollection("word_stats");
            JavaFindCount.countWords(collection);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static void countWords(MongoCollection collection) {
        Long count = null;
        Document query = null;

        count = collection.count();
        System.out.println("Total words in the collection: " + count);
        query = new Document("first", "a");
        count = collection.count(query);
        System.out.println("Total words starting with A: " + count);
    }
}
