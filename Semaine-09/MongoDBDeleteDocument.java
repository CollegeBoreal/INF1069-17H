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
import com.mongodb.client.result.DeleteResult;

/**
 * INF1069-17H
 * This class deletes documents.
 */
public class MongoDBDeleteDocument {

	public static void main(String[] args) {
		MongoClient mongoClient = null;
		MongoDatabase mongoDatabase = null;
		MongoCollection<Document> collection = null;
		FindIterable<Document> iterable = null;
		DeleteResult result = null;
		Set<String> keySet = null;
		Iterator<String> iterator = null;
		Document catalog = null;
		Document documentDeleted = null;
		String documentKey = null;
		String server = null;
		int port = 0;
		String database = null;
		
		try {
			// Server name
			server = "10.0.2.2";
			
			// Port number
			port = 27018;
			
			// Database
			database = "semaine09";
			
			// Connect to server
			mongoClient = new MongoClient(
							Arrays.asList(new ServerAddress(server, port)));
			// Get the database
			mongoDatabase = mongoClient.getDatabase(database);
			// Get the collection
			collection = mongoDatabase.getCollection("catalog");
			
			// Create document 1
			catalog = new Document("catalogId", "catalog1")
					.append("journal", "Oracle Magazine")
					.append("publisher", "Oracle Publishing")
					.append("edition", "November December 2013")
					.append("title", "Engineering as a Service")
					.append("author", "David A. Kelly");
			collection.insertOne(catalog);

			// Create document 2
			catalog = new Document("catalogId", "catalog2")
					.append("journal", "Oracle Magazine")
					.append("publisher", "Oracle Publishing")
					.append("edition", "November December 2013")
					.append("title", "Quintessential and Collaborative")
					.append("author", "Tom Haunert");
			collection.insertOne(catalog);

			// Create document 3
			catalog = new Document("catalogId", "catalog3")
					.append("journal", "Oracle Magazine")
					.append("publisher", "Oracle Publishing")
					.append("edition", "November December 2013");
			collection.insertOne(catalog);

			// Create document 4
			catalog = new Document("catalogId", "catalog4")
					.append("journal", "Oracle Magazine")
					.append("publisher", "Oracle Publishing")
					.append("edition", "November December 2013");
			collection.insertOne(catalog);
			
			// Delete one document
			result = collection.deleteOne(
						new Document("catalogId", "catalog1"));
			
			// Print results
			System.out.println("Number of documents deleted: "
					+ result.getDeletedCount());
			
			// Find and delete
			documentDeleted = collection.findOneAndDelete(
								new Document("catalogId", "catalog2"));
			
			// Print results
			System.out.println("Document deleted: " + documentDeleted);

			// Delete many documents
			result = collection.deleteMany(new Document());

			// Print results
			System.out.println("Number of documents deleted: "
					+ result.getDeletedCount());

			// Find all documents
			iterable = collection.find();

			// Print results
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
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
}
