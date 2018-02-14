package model;

import java.sql.*;

import logic.BezorgAdres;

public class BezorgAdresIO {
	
	public static void maakNieuweBezorgAdres(BezorgAdres bezorgAdres) throws SQLException {
		Connection con = Connector.getInstance().getConnection();
		String sql = "INSERT INTO bezorgadres(klantid, voornaam, achternaam, straatnaam, huisnummer, toevoegingsnummer, postcode, woonplaats) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, bezorgAdres.getKlantId());
		ps.setString(2, bezorgAdres.getVoornaam());
		ps.setString(3, bezorgAdres.getAchternaam());
		ps.setString(4, bezorgAdres.getStraatNaam());
		ps.setString(5, bezorgAdres.getHuisnummer());
		ps.setString(6, bezorgAdres.getToevoegingHuisnummer());
		ps.setString(7, bezorgAdres.getPostcode());
		ps.setString(8, bezorgAdres.getWoonplaats());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	public static BezorgAdres getBezorgAdres(int klantId) throws SQLException {
		BezorgAdres bezorgAdres = null;
		Connection con = Connector.getInstance().getConnection();
		String sql = "SELECT * FROM bezorgadres WHERE klantid = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, klantId);
		ResultSet rs = ps.executeQuery();
		ps.close();
		con.close();
		return helpGetBezorgAdres(rs, bezorgAdres);
	}
	
	private static BezorgAdres helpGetBezorgAdres(ResultSet rs, BezorgAdres bezorgAdres) throws SQLException {
		while(rs.next()) {
		bezorgAdres = new BezorgAdres.BezorgAdresBuilder(rs.getInt("klantid"), rs.getString("voornaam"), rs.getString("achternaam")).
					straatNaam(rs.getString("straatnaam")).huisnummer(rs.getString("huisnumer")).toevoegingHuisnummer(rs.getString("toevoegingHuisnummer")).
																				postcode(rs.getString("postcode")).woonplaats(rs.getString("woonplaats")).build();
		}
		return bezorgAdres;
	}

}

