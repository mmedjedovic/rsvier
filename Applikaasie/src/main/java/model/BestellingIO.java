package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logic.Bestelling;
import logic.Klant;

public class BestellingIO {
	
	
	//Nieuwe bestelling in database opslaan
	public static void maakNieuweBestelling(Bestelling bestelling) throws SQLException {
		Connection con = Connector.getInstance().getConnection();
		//id voor bestelling wordt manual gecrieerd, want wordt zo gebruikt voor andere tabel
		Integer bestellingId = createBestellingId(con);
		maakBestellingTotaal(con, bestelling, bestellingId);
	}
	
	
	private static void maakBestellingTotaal(Connection con, Bestelling bestelling, Integer bestellingId) throws SQLException {
		String sql = "INSERT INTO bestelling_totaal(bestelling_id, klant_id, totaal_bedrag, bestelling_datum, status) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		Date bestellingDatum = new Date(bestelling.getBestellingDate().getTime());
		ps.setInt(1, bestellingId);
		ps.setInt(2,  bestelling.getKlant().getKlantId());
		ps.setBigDecimal(3, bestelling.getTotaalPrijs());
		ps.setDate(4, bestellingDatum);
		ps.setString(5, bestelling.getStatus().name());
		ps.executeUpdate();
		ps.close();
	}
	
	//hulp methode om nieuwe bestelnummer te maken
		private static int createBestellingId(Connection con) throws SQLException {
			int maxBestellingNummer = 1;
			String sql = "SELECT MAX(bestellingnummer) FROM bestelling";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				maxBestellingNummer = rs.getInt("bestellingnummer") + 1;
			}
			return maxBestellingNummer;
		}
	
	
		
	/**
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
	}*/
	
}
