package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import logic.Klant;

public class KlantIO {
	
	public static void maakNieuweKlant(Klant klant) throws SQLException {
		Connection con = Connector.getInstance().getConnection();
		String sql = "INSERT INTO klant (voornaam, achternaam, tussenvoegsel, straatnaam, huisnummer, toevoegingshuisnummer, postcode, woonplaats) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, klant.getVoornaam());
		ps.setString(2, klant.getAchternaam());
		ps.setString(3, klant.getTussenvoegsel());
		ps.setString(4, klant.getStraatNaam());
		ps.setString(5, klant.getHuisnummer());
		ps.setString(6, klant.getToevoegingHuisnummer());
		ps.setString(7, klant.getPostcode());
		ps.setString(8, klant.getWoonplaats());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	public static ArrayList<Klant> getKlanten() throws SQLException {
		ArrayList<Klant> klantList = new ArrayList<Klant>();
		Connection con = Connector.getInstance().getConnection();
		String sql = "SELECT * FROM klant";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		con.close();
		st.close();
		return maakKlantenList(rs, klantList);
	}
	
	private static ArrayList<Klant> maakKlantenList(ResultSet rs, ArrayList<Klant> klantList) throws SQLException {
		while(rs.next()) {
			Klant klant = new Klant.KlantBuilder(rs.getString("voornaam"), rs.getString("achternaam")).
					straatNaam(rs.getString("straatnaam")).huisnummer(rs.getString("huisnummer")).toevoegingHuisnummer(rs.getString("toevoeginghuisnummer")).
								postcode(rs.getString("postcode")).woonplaats(rs.getString("woonplaats")).build();
			klantList.add(klant);
		}
		return klantList;
	}
}
