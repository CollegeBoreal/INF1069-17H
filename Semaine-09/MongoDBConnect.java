package semaine09;
/**
 * INF1069-17H
 * This class connects to database.
 */
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnect {
	
	public static void main(String[] args) {
		MongoClient mongoClient = null;
		@SuppressWarnings("unused")
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
			database = "semaine09";
			
			// To connect to MongDB server
			mongoClient = new MongoClient(server, port);
			
			// Now connect to your databases
			mongoDatabase = mongoClient.getDatabase(database);
			
			// Print message
			System.out.println("Connect to database successfully.");
			
			// Print list of databases from the server.
			System.out.println("List of databases:");
			for (String databaseName : mongoClient.listDatabaseNames()) {
				System.out.println(databaseName);
			}
			
			// close the connection
			mongoClient.close();
		} catch(Exception e) {
			// Print errors
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
}
