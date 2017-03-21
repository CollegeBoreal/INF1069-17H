package semaine09;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

/**
 * INF1069-17H
 * This class implements an example for find function.
 * By Steve Tshibangu <a>Steve.TshibanguMutshi@collegeboreal.ca</a>
 */
public class JavaFindSpecific {
    public static void main(String[] args) {
        MongoClient mongoClient = null;
        MongoDatabase mongoDatabase = null;
        MongoCollection collection = null;

        try {
            // ***Change the server and port to match your database connection
            mongoClient = new MongoClient("10.0.2.2", 27018);
            mongoDatabase = mongoClient.getDatabase("semaine09");
            collection = mongoDatabase.getCollection("word_stats");

            JavaFindSpecific.over12(collection);
            JavaFindSpecific.startingABC(collection);
            JavaFindSpecific.startEndVowels(collection);
            JavaFindSpecific.over6Vowels(collection);
            JavaFindSpecific.nonAlphaCharacters(collection);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void displayCursor(MongoCursor<Document> mongoCursor) {
        Document document = null;
        String words = "";

        while(mongoCursor.hasNext()){
            document = mongoCursor.next();
            words = words.concat(document.get("word").toString()).concat(",");
        }
        if(words.length() > 65){
            words = words.substring(0, 65) + "...";
        }
        System.out.println(words);
    }

    public static void over12(MongoCollection collection) {
        Document query = null;
        MongoCursor<Document> mongoCursor = null;

        query = new Document("size", new Document("$gt", 12));
        mongoCursor = collection.find(query).iterator();
        System.out.println("\nWords with more than 12 characters: ");
        JavaFindSpecific.displayCursor(mongoCursor);
    }

    public static void startingABC(MongoCollection collection) {
        Document query = null;
        MongoCursor<Document> mongoCursor = null;
        ArrayList<String> letters = null;

        letters = new ArrayList<String>();
        letters.add("a");
        letters.add("b");
        letters.add("c");
        query = new Document("first", new Document("$in", letters));
        mongoCursor = collection.find(query).iterator();
        System.out.println("\nWords starting with A, B or C: ");
        JavaFindSpecific.displayCursor(mongoCursor);
    }

    public static void startEndVowels(MongoCollection collection) {
        Document query = null;
        MongoCursor<Document> mongoCursor = null;
        ArrayList<String> vowels = null;
        ArrayList<Document> documents = null;

        vowels = new ArrayList<String>();
        vowels.add("a");
        vowels.add("e");
        vowels.add("i");
        vowels.add("o");
        vowels.add("u");

        documents = new ArrayList<Document>();
        documents.add(new Document("first", new Document("$in", vowels)));
        documents.add(new Document("last", new Document("$in", vowels)));

        query = new Document("$and", documents);
        query.append("size", new Document("$gt", 5));
        mongoCursor = collection.find(query).iterator();
        System.out.println("\nWords starting and ending with a vowel: ");
        JavaFindSpecific.displayCursor(mongoCursor);
    }

    public static void over6Vowels(MongoCollection collection) {
        Document query = null;
        MongoCursor<Document> mongoCursor = null;

        query = new Document("stats.vowels",
                        new Document("$gt", 5));
        mongoCursor = collection.find(query).iterator();
        System.out.println("\nWords with more than 5 vowels: ");
        JavaFindSpecific.displayCursor(mongoCursor);
    }

    public static void nonAlphaCharacters(MongoCollection collection) {
        Document query = null;
        MongoCursor<Document> mongoCursor = null;
        ArrayList<Document> documents = null;

        documents = new ArrayList<Document>();
        documents.add(new Document("type", "other"));
        documents.add(new Document("chars", new Document("$size", 1)));
        query = new Document("charsets",
                        new Document("$elemMatch",
                                new Document("$and", documents)));
        mongoCursor = collection.find(query).iterator();
        System.out.println("\nWords with 1 non-alphabet characters: ");
        JavaFindSpecific.displayCursor(mongoCursor);
    }
}
