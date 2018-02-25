package data.io;

import java.sql.*;

import util.ExceptionIO;

public class Connector {
	
	private static Connector connector = null;
	
	private Connector() {}
	
	public static Connector getInstance() {
		if (connector == null) {
			connector = new Connector();
		}
		return connector;
	}
	
	public Connection getConnection() throws ExceptionIO {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/applikaasie", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Connectie met database is niet gelukt!");
		}
	}

}

