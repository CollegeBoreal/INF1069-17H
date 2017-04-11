package semaine09;

import com.mongodb.MongoClient;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;

/**
 * This class implements an example for distinct function.
 * Author : Steve Tshibangu
 * Email: steve.tshibangu-mutshi.1@collegeboreal.ca
 * Course: INF1069
 * Date : 2017-02-02
 */
public class JavaFindDistinct {
    public static void main(String[] args) {
        MongoClient mongoClient = null;
        MongoDatabase mongoDatabase = null;
        MongoCollection collection = null;

        try {
            // ***Change the server and port to match your database connection
            mongoClient = new MongoClient("10.0.2.2", 27018);
            mongoDatabase = mongoClient.getDatabase("semaine09");
            collection = mongoDatabase.getCollection("word_stats");
            JavaFindDistinct.sizesOfAllWords(collection);
            JavaFindDistinct.sizesOfQWords(collection);
            JavaFindDistinct.firstLetterOfLongWords(collection);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void sizesOfAllWords(MongoCollection collection) {
        DistinctIterable distinctIterable = null;
        MongoCursor<Object> mongoCursor = null;

        distinctIterable = collection.distinct(
                            "size",
                            Double.class);
        mongoCursor = distinctIterable.iterator();
        System.out.println("\nDistinct Sizes of words: ");
        System.out.println(mongoCursor.next());
    }

    public static void sizesOfQWords(MongoCollection collection) {
        Document query = null;
        DistinctIterable distinctIterable = null;
        MongoCursor<Object> mongoCursor = null;

        query = new Document("first", "q");
        distinctIterable = collection.distinct(
                            "size",
                            query,
                            Double.class);
        mongoCursor = distinctIterable.iterator();
        System.out.println("\nDistinct Sizes of words starting with Q: ");
        System.out.println(mongoCursor.next());
    }

    public static void firstLetterOfLongWords(MongoCollection collection) {
        Document query = null;
        DistinctIterable distinctIterable = null;
        MongoCursor<Object> mongoCursor = null;

        query = new Document("size", new Document("$gt", 12));
        distinctIterable = collection.distinct(
                            "first",
                            query,
                            String.class);
        mongoCursor = distinctIterable.iterator();
        System.out.println("\nDistinct first letters of words longer " +
                "than  12 characters: ");
        System.out.println(mongoCursor.tryNext());
    }
}
