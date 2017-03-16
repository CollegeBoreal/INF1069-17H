package semaine09;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * INF1069-17H
 * This class inserts document.
 */
public class MongoDBInsertDocument {

	public static void main(String[] args) {
		MongoClient mongoClient = null;
		MongoDatabase mongoDatabase = null;
		MongoCollection<Document> collection = null;
		Set<String> keySet = null;
		Iterator<String> iterator = null;
		Document document = null;
		Document documentRet = null;
		Object documentKey = null;
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
			
			// Create the document
			document = new Document("journal", "Oracle Magazine")
							.append("publisher", "Oracle Publishing")
							.append("edition", "November December 2013")
							.append("title", "Engineering as a Service")
							.append("author", "David A. Kelly");
			
			// Insert document
			collection.insertOne(document);
			
			// Get the first document
			documentRet = collection.find().first();
			
			// Print the retrieved document
			System.out.println(documentRet);
			
			// Loop thru the document
			keySet = document.keySet();			
			iterator = keySet.iterator();
			while (iterator.hasNext()) {
				documentKey = iterator.next();
				System.out.println(
						documentKey + 
						"\t" +
						documentRet.get(documentKey.toString()));
			}
			
			// close the connection
			mongoClient.close();
		} catch(Exception e) {
			// Print errors
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
}
