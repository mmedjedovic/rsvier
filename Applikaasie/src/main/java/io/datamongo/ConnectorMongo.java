package io.datamongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class ConnectorMongo {
	
	private static ConnectorMongo connector = null;
	
	private ConnectorMongo() {}
	
	public static ConnectorMongo getInstance() {
		if(connector == null) {
			connector = new ConnectorMongo();
		}
		return connector;
	}
	
	public DB getDatabase() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		return mongoClient.getDB("applikaasie");
	}

}
