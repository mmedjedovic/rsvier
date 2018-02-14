package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logic.Bestelling;

public class BestellingIO {
	//Er wordt gewerkt
	/**
	//Nieuwe bestelling in database opslaan
	public static void maakNieuweBestelling(Bestelling bestelling) throws SQLException {
		Connection con = Connector.getInstance().getConnection();
		int bestellingNummer = createBestellingNummer(con);
		int klantId = bestelling.getKlantId();
		String status = "open";
		String sql = "INSERT INTO bestelling(bestellnummer, kaas, kg, klantid, status) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		HashMap<String, Double> besteldeKazen = bestelling.getBesteldeKazenList(); 
		for(Map.Entry<String, Double> entry: besteldeKazen.entrySet()) {
			String kaas = entry.getKey();
			double kg = entry.getValue();
			ps.setInt(1, bestellingNummer);
			ps.setString(2, kaas);
			ps.setDouble(3, kg);
			ps.setInt(4, klantId);
			ps.setString(5, status);
			ps.executeUpdate();
		}
		ps.close();
		con.close();
	}
	
	//Mehode om overzicht van open bestellingen te maken
	public static ArrayList<Bestelling> getOpenBestellingenPerKlant(int klantId) throws SQLException {
		Connection con = Connector.getInstance().getConnection();
		String sql = "SELECT * FROM bestelling WHERE  klantid = ? AND status = open";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, klantId);
		ResultSet rs = ps.executeQuery();
	}
	
	
	//hulp methode om bestelligen van uit sql-query te maken
	private static ArrayList<Bestelling> getBestellingen(ResultSet rs) throws SQLException {
		ArrayList<Bestelling> bestellingLijst = new ArrayList<Bestelling>();
		while(rs.next()) {
			
		}
	}
	
	
	//hulp methode om nieuwe bestelnummer te maken
	private static int createBestellingNummer(Connection con) throws SQLException {
		int maxBestellingNummer = -1;
		String sql = "SELECT MIN(bestellingnummer) FROM bestelling";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()) {
			maxBestellingNummer = rs.getInt("bestellingnummer") + 1;
		}
		return maxBestellingNummer;
	}*/
}
