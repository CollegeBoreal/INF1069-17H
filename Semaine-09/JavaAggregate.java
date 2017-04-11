package semaine09;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class implements an example for aggregate function.
 * Author : Steve Tshibangu
 * Email: steve.tshibangu-mutshi.1@collegeboreal.ca
 * Course: INF1069
 * Date : 2017-02-02
 */
public class JavaAggregate {
    public static void main(String[] args) {
        MongoClient mongoClient = null;
        MongoDatabase mongoDatabase = null;
        MongoCollection collection = null;

        try {
            // ***Change the server and port to match your database connection
            mongoClient = new MongoClient("10.0.2.2", 27018);
            mongoDatabase = mongoClient.getDatabase("semaine09");
            collection = mongoDatabase.getCollection("word_stats");

            JavaAggregate.largeSmallVowels(collection);
            JavaAggregate.top5AverageWordFirst(collection);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void displayAggregate(AggregateIterable aggregateIterable){
        for (Iterator<Document> items = aggregateIterable.iterator();
            items.hasNext();){
            System.out.println(items.next());
        }
    }

    public static void largeSmallVowels(MongoCollection collection) {
        Document match = null;
        Document groupOps = null;
        Document group = null;
        Document sort = null;
        ArrayList<Document> documents = null;
        AggregateIterable aggregateIterable = null;
        ArrayList<String> vowels = null;

        vowels = new ArrayList<String>();
        vowels.add("a");
        vowels.add("e");
        vowels.add("i");
        vowels.add("o");
        vowels.add("u");

        match = new Document("$match",
                    new Document("first", new Document ("$in", vowels)));
        groupOps = new Document("_id", "$first");
        groupOps.append("largest", new Document("$max", "$size"));
        groupOps.append("smallest", new Document("$min", "$size"));
        groupOps.append("total", new Document("$sum", 1));
        group = new Document("$group", groupOps);
        sort = new Document("$sort", new Document("first", 1));
        documents = new ArrayList<Document>();
        documents.add(match);
        documents.add(group);
        documents.add(sort);
        aggregateIterable = collection.aggregate(documents);
        System.out.println("\nLargest and smallest word sizes for " +
                "words beginning with a vowel: ");
        JavaAggregate.displayAggregate(aggregateIterable);
    }

    public static void top5AverageWordFirst(MongoCollection collection){
        Document groupOps = null;
        Document group = null;
        Document sort = null;
        Document limit = null;
        AggregateIterable aggregateIterable = null;
        ArrayList<Document> documents = null;

        groupOps = new Document("_id", "$first");
        groupOps.append("average", new Document("$avg", "$size"));
        group = new Document("$group", groupOps);
        sort = new Document("$sort", new Document("average", -1));
        limit = new Document("$limit", 5);
        documents = new ArrayList<Document>();
        documents.add(group);
        documents.add(sort);
        documents.add(limit);
        aggregateIterable = collection.aggregate(documents);
        System.out.println("\nFirst letter of top 5 largest average " +
                "word size: ");
        JavaAggregate.displayAggregate(aggregateIterable);
    }
}
