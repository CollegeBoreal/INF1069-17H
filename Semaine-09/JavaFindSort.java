package semaine09;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * This class implements an example for sort function.
 * Author : Steve Tshibangu
 * Email: steve.tshibangu-mutshi.1@collegeboreal.ca
 * Course: INF1069
 * Date : 2017-02-02
 */
public class JavaFindSort {
    public static void main(String[] args) {
        MongoClient mongoClient = null;
        MongoDatabase mongoDatabase = null;
        MongoCollection collection = null;

        try {
            // ***Change the server and port to match your database connection
            mongoClient = new MongoClient("10.0.2.2", 27018);
            mongoDatabase = mongoClient.getDatabase("semaine09");
            collection = mongoDatabase.getCollection("word_stats");
            JavaFindSort.sortWordsAscending(collection);
            JavaFindSort.sortWordsDesc(collection);
            JavaFindSort.sortWordsAscAndSize(collection);
        } catch (Exception e){
            System.err.println(e.toString());
        }
    }

    public static void displayCursor(MongoCursor mongoCursor){
        Document document = null;
        String words = "";

        while(mongoCursor.hasNext()){
            document = (Document) mongoCursor.next();
            words = words.concat(document.get("word").toString()).concat(",");
        }
        if(words.length() > 65){
            words = words.substring(0, 65) + "...";
        }
        System.out.println(words);
    }

    public static void sortWordsAscending(MongoCollection collection) {
        Document query = null;
        Document sorter = null;
        MongoCursor mongoCursor = null;

        query = new Document("first", "w");
        sorter = new Document("word", 1);
        mongoCursor = collection.find(query).sort(sorter).iterator();
        System.out.println("\nW words ordered ascending: ");
        JavaFindSort.displayCursor(mongoCursor);
    }

    public static void sortWordsDesc(MongoCollection collection) {
        Document query = null;
        Document sorter = null;
        MongoCursor mongoCursor = null;

        query = new Document("first", "w");
        sorter = new Document("word", -1);
        mongoCursor = collection.find(query).sort(sorter).iterator();
        System.out.println("\nW words ordered descending: ");
        JavaFindSort.displayCursor(mongoCursor);
    }

    public static void sortWordsAscAndSize(MongoCollection collection) {
        Document query = null;
        Document sorter = null;
        MongoCursor mongoCursor = null;

        query = new Document("first", "q");
        sorter = new Document("last", 1);
        sorter.append("size", -1);
        mongoCursor = collection.find(query).sort(sorter).iterator();
        System.out.println("\nQ words ordered first by last letter " +
                "and then by size: ");
        JavaFindSort.displayCursor(mongoCursor);
    }
}
