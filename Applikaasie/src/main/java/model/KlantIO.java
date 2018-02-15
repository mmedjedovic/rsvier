package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.Date;

import logic.Klant;

public class KlantIO {
	
	public static void maakNieuweKlant(Klant klant) throws SQLException {
		Connection con = Connector.getInstance().getConnection();
		String sql = "INSERT INTO klant (voornaam, achternaam, geboortedatum) VALUES (?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		Date date = new Date(klant.getGeboorteDatum().getTime());
		ps.setString(1, klant.getVoornaam());
		ps.setString(2, klant.getAchternaam());
		ps.setDate(3, date);
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	public static ArrayList<Klant> getKlanten() throws SQLException {
		Connection con = Connector.getInstance().getConnection();
		String sql = "SELECT * FROM klant";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		con.close();
		st.close();
		return maakKlantenList(rs);
	}
	
	private static ArrayList<Klant> maakKlantenList(ResultSet rs) throws SQLException {
		ArrayList<Klant> klantList = new ArrayList<Klant>();
		while(rs.next()) {
			Klant klant = new Klant.KlantBuilder(rs.getString("voornaam"), rs.getString("achternaam")).
					idKlant(rs.getInt("klant_id")).geboorteDatum(rs.getDate("geboortedatum")).build();
			klantList.add(klant);
		}
		return klantList;
	}
}
