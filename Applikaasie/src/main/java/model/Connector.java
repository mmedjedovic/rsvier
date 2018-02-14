package model;

import java.sql.*;

public class Connector {
	
	private static Connector connector = null;
	
	public static Connector getInstance() {
		if (connector == null) {
			connector = new Connector();
		}
		return connector;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/applikaasie", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}

