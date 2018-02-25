package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logic.Bestelling;
import logic.Bestelling.Status;
import logic.Klant;

public class BestellingIO {
	
	
	//nieuwe bestelling in database opslaan in twee verschillende tabellen, bestelling_totaal en bestelling_details
	public static void maakNieuweBestelling(Bestelling bestelling) throws SQLException {
		Connection con = Connector.getInstance().getConnection();
		//hier wordt bestelling_totaal-tabel gemaak
		Integer bestellingId = maakBestellingTotaal(con, bestelling);
		//Hier wordt bestelling_details tabel gemaakt
		BestellingDetailsIO.maakBestellingDetails(con, bestelling, bestellingId);
	}
	
	//bestelling_totaal tabel wordt gemaakt
	private static Integer maakBestellingTotaal(Connection con, Bestelling bestelling) throws SQLException {
		String sql = "INSERT INTO bestelling_totaal(klant_id, totaal_prijs, bestelling_datum, status) VALUES(?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		Date bestellingDatum = new Date(bestelling.getBestellingDate().getTime());
		ps.setInt(1,  bestelling.getKlant().getKlantId());
		ps.setBigDecimal(2, bestelling.getTotaalPrijs());
		ps.setDate(3, bestellingDatum);
		ps.setString(4, bestelling.getStatus().name());
		ps.executeUpdate();
		ps.close();
		return getLastId(con);
	}

	//methode om overzicht van bestellingen te maken van specifiek klant, open of gesloten
	public static ArrayList<Bestelling> getBestellingenPerKlant(Integer klantId, Status status) throws SQLException {
		String sql = "SELECT * FROM bestelling_totaal WHERE  klant_id = ? AND status = ?";
		Connection con = Connector.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, klantId);
		ps.setString(2, status.name());
		ResultSet rs = ps.executeQuery();
		Klant klant = KlantIO.getKlant(klantId);
		return hulpGetBestellingen(rs, klant);
	}
	
	//Sluiten bestelling
	public static void changeStatusBestelling(Bestelling bestelling, Status status) throws SQLException {
		String sql = "UPDATE bestelling_totaal SET status = ? WHERE bestelling_id = ?";
		Connection con = Connector.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, Status.GESLOTEN.name());
		ps.setInt(2, bestelling.getBestellingId());
		ps.executeUpdate();
	}
	
	
	//hulp methode om bestelligen  uit ResultSet te crieeren
	private static ArrayList<Bestelling> hulpGetBestellingen(ResultSet rs, Klant klant) throws SQLException {
		ArrayList<Bestelling> bestellingLijst = new ArrayList<Bestelling>();
		while(rs.next()) {
			java.util.Date date = new Date(rs.getDate("bestelling_datum").getTime());
			Status status = Status.valueOf("OPEN");
			Bestelling bestelling = new Bestelling.BestellingBuilder(klant).
					totaalPrijs(rs.getBigDecimal("totaal_prijs")).bestellingDate(date).bestellingId(rs.getInt("bestelling_id")).status(status).build();
			bestellingLijst.add(bestelling);
		}
		return bestellingLijst;
	}
	
	//hulp methode om laatste bestelling_id te krijgen
	private static Integer getLastId(Connection con) throws SQLException {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery ("SELECT last_insert_id() as last_id from bestelling_totaal");
		rs.next();
		return rs.getInt("last_id");
	}
		
}
