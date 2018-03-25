package io.datamongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import util.ExceptionIOMongo;

public class ConnectorMongo {
	
	private static ConnectorMongo connector = null;
	
	private ConnectorMongo() {}
	
	public static ConnectorMongo getInstance() {
		if(connector == null) {
			connector = new ConnectorMongo();
		}
		return connector;
	}
	
	public DB getDatabase() throws ExceptionIOMongo {
		try {
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			return mongoClient.getDB("applikaasie");
		} catch (UnknownHostException e) {
			throw new ExceptionIOMongo("Niet gelukt connectie maken met database");
		}
	}
}
