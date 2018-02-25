package dataio;

import java.sql.*;

import util.ExceptionIOImpl;

public class Connector {
	
	private static Connector connector = null;
	
	private Connector() {}
	
	public static Connector getInstance() {
		if (connector == null) {
			connector = new Connector();
		}
		return connector;
	}
	
	public Connection getConnection() throws ExceptionIOImpl {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/applikaasie", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ExceptionIOImpl("Connectie met database is niet gelukt!");
		}
	}

}

