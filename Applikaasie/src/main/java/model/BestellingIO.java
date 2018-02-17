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
		//id voor bestelling wordt manual gecrieerd
		Integer bestellingId = createBestellingId(con);
		//hier wordt bestelling_totaal-tabel gemaak
		maakBestellingTotaal(con, bestelling, bestellingId);
		//Hier wordt bestelling_details tabel gemaakt
		BestellingDetailsIO.maakBestellingDetails(con, bestelling, bestellingId);
	}
	
	//bestelling_totaal tabel wordt gemaakt
	private static void maakBestellingTotaal(Connection con, Bestelling bestelling, Integer bestellingId) throws SQLException {
		String sql = "INSERT INTO bestelling_totaal(bestelling_id, klant_id, totaal_prijs, bestelling_datum, status) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		Date bestellingDatum = new Date(bestelling.getBestellingDate().getTime());
		ps.setInt(1, bestellingId);
		ps.setInt(2,  bestelling.getKlant().getKlantId());
		ps.setBigDecimal(3, bestelling.getTotaalPrijs());
		ps.setDate(4, bestellingDatum);
		ps.setString(5, bestelling.getStatus().name());
		ps.executeUpdate();
		
	}

	//methode om overzicht van bestellingen te maken van specifiek klant, open of gesloten
	public static ArrayList<Bestelling> getBestellingenPerKlant(int klantId, Status status) throws SQLException {
		String sql = "SELECT * FROM bestelling WHERE  klantid = ? AND status = ?";
		Connection con = Connector.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, klantId);
		ps.setString(2, status.name());
		ResultSet rs = ps.executeQuery();
		Klant klant = KlantIO.getKlant(klantId).get(0);
		return hulpGetBestellingen(rs, klant, Status.OPEN);
	}
	
	
	//hulp methode om bestelligen  uit ResultSet te crieeren
	private static ArrayList<Bestelling> hulpGetBestellingen(ResultSet rs, Klant klant, Status status) throws SQLException {
		ArrayList<Bestelling> bestellingLijst = new ArrayList<Bestelling>();
		while(rs.next()) {
			java.util.Date date = new Date(rs.getDate("bestelling_datum").getTime());
			Bestelling bestelling = new Bestelling.BestellingBuilder(klant).
					totaalPrijs(rs.getBigDecimal("totaal_prijs")).bestellingDate(date).status(status).build();
			bestellingLijst.add(bestelling);
		}
		return bestellingLijst;
	}
	
	//hulp methode om nieuwe bestelnummer te maken
	private static int createBestellingId(Connection con) throws SQLException {
		int maxBestellingNummer = 1;
		String sql = "SELECT MAX(bestellingnummer) FROM bestelling_totaal";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()) {
			maxBestellingNummer = rs.getInt("bestelling_id") + 1;
		}
		return maxBestellingNummer;
	}
		
}
