package semaine09;
/**
 * INF1069-17H
 * This class retrieves collections.
 */
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBGetCollectionNames {

	public static void main(String[] args) {
		MongoClient mongoClient = null;
		MongoDatabase mongoDatabase = null;
		String server = null;
		int port = 0;
		String database = null;
		
		try {
			// Server name
			server = "10.0.2.2";

			// Port number
			port = 27018;
			
			// Database
			database = "semaine05";
			
			// To connect to MongoDB server
			mongoClient = new MongoClient(server, port);
			
			// Now connect to your databases
			mongoDatabase = mongoClient.getDatabase(database);
			
			// Print message
			System.out.println("Connect to database successfully ");
			
			// Print collection names
			System.out.println("List of collections:");
			for (String collectionName : mongoDatabase.listCollectionNames()) {
				System.out.println(collectionName);
			}
			
			// close the connection
			mongoClient.close();
		} catch(Exception e) {
			// Print errors
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

}
