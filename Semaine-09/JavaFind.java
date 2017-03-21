package semaine09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

/**
 * INF1069-17H
 * This class implements an example for find function.
 * By Steve Tshibangu <a>Steve.TshibanguMutshi@collegeboreal.ca</a>
 */
public class JavaFind {
    public static void main(String[] args) {
        MongoClient mongoClient = null;
        MongoDatabase mongoDatabase = null;
        MongoCollection collection = null;

        try {
            // ***Change the server and port to match your database connection
            mongoClient = new MongoClient("10.0.2.2", 27018);
            mongoDatabase = mongoClient.getDatabase("semaine09");
            collection = mongoDatabase.getCollection("word_stats");
            JavaFind.getOne(collection);
            JavaFind.getManyWhile(collection);
            JavaFind.getManyFor(collection);
            JavaFind.getManyToArray(collection);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getOne(MongoCollection collection) {
        Document document = (Document) collection.find().first();
        System.out.println("Single Document: ");
        System.out.println(document.toString());
    }

    public static void getManyWhile(MongoCollection collection) {
        FindIterable<Document> iterable = null;
        Iterator<Document> iterator = null;
        Document document = null;
        Double count = 0.0;

        iterable = collection.find();
        iterator = iterable.iterator();
        while(iterator.hasNext()) {
            document = iterator.next();
            count += Double.parseDouble(document.get("size").toString());
        }
        System.out.println("\nTotal characters using While loop: ");
        System.out.println(count);
    }

    public static void getManyFor(MongoCollection collection) {
        FindIterable<Document> iterable = null;
        Iterator<Document> iterator = null;
        Document document = null;

        System.out.println("\nFor loop iteration: ");
        iterable = collection.find();
        iterator = iterable.iterator();
        for(Integer i=0; i<5; i++){
            document = iterator.next();
            System.out.println(document.get("word"));
        }
    }

    public static void getManyToArray(MongoCollection collection) {
        FindIterable<Document> iterable = null;
        Iterator<Document> iterator = null;
        List<Document> documents = null;

        System.out.println("\nConverted to array iteration: ");
        iterable = collection.find();
        iterator = iterable.iterator();
        documents = new ArrayList<>();
        iterator.forEachRemaining(documents::add);
        for(final Document document : documents) {
            System.out.println(document.get("word"));
        }
    }
}
