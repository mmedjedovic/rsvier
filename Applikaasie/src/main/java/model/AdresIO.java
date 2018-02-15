package model;

import java.sql.*;

import logic.Adres;
import logic.Klant;

public class AdresIO {
	
	public static void maakNieuweAdres(Adres adres) throws SQLException {
		Connection con = Connector.getInstance().getConnection();
		String sql = "INSERT INTO bezorgadres(klantid, straatnaam, huisnummer, toevoegingsnummer, postcode, woonplaats) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, adres.getKlant().getKlantId());
		ps.setString(2, adres.getStraatNaam());
		ps.setString(3, adres.getHuisnummer());
		ps.setString(4, adres.getToevoegingHuisnummer());
		ps.setString(5, adres.getPostcode());
		ps.setString(6, adres.getWoonplaats());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	public static Adres getAdres(Klant klant) throws SQLException {
		Adres adres = null;
		Connection con = Connector.getInstance().getConnection();
		String sql = "SELECT * FROM bezorgadres WHERE klantid = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, klant.getKlantId());
		ResultSet rs = ps.executeQuery();
		ps.close();
		con.close();
		return helpGetAdres(rs, klant);
	}
	
	private static Adres helpGetAdres(ResultSet rs, Klant klant) throws SQLException {
		Adres adres = null;
			if(rs.next()) {
				adres = new Adres.AdresBuilder(klant).straatNaam(rs.getString("straatnaam")).huisnummer(rs.getString("huisnumer")).
						toevoegingHuisnummer(rs.getString("toevoeginghuisnummer")).postcode(rs.getString("postcode")).woonplaats(rs.getString("woonplaats")).build();
		}
		return adres;
	}

}

