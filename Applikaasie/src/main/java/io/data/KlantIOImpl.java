package io.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import io.interfaces.KlantIO;
import model.Klant;
import util.ExceptionIO;

import java.sql.Date;

public class KlantIOImpl implements KlantIO{
	
	private static KlantIOImpl instance = null;
	
	private KlantIOImpl() {};
	
	public static KlantIOImpl getInstance() {
		if(instance == null) {
			instance = new KlantIOImpl();
		}
		return instance;
	}
	
	
	//een nieuwe klant in databse maken
	@Override
	public Integer maakNieuweKlant(Klant klant) throws ExceptionIO {
		String sql = "INSERT INTO klant (voornaam, achternaam) VALUES (?, ?)";
		Integer klantId = null;
		try(Connection con = Connector.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, klant.getVoornaam());
			ps.setString(2, klant.getAchternaam());
			ps.executeUpdate();
			return getLastId(con);
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt nieuwe klant in database te maken");
		}
	}
	
	//een speciefiek klant uit databse te halen
	@Override
	public Klant getKlant(Integer klantId) throws ExceptionIO {
		String sql = "SELECT * FROM klant WHERE klant_id = ?";
		try(Connection con = Connector.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, klantId);
			try(ResultSet rs = ps.executeQuery();) {
				return maakKlantenList(rs).get(0);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt gevraagde klant uit database te halen");
		}
	}
	
	//alle klanten uit database te halen
	@Override
	public ArrayList<Klant> getKlanten() throws ExceptionIO {
		String sql = "SELECT * FROM klant";
		try(Connection con = Connector.getInstance().getConnection(); 
										Statement st = con.createStatement();
													ResultSet rs = st.executeQuery(sql);) {
			return maakKlantenList(rs);
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt om lijst van klanten uit databse te halen");
		}
	}
	
	@Override
	public void deleteKlant(Integer klantId) throws ExceptionIO {
		String sql = "DELETE from klant WHERE klant_id =?";
		try(Connection con = Connector.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, klantId);
			ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt beterffende klant uit database te werwijderen");
		}
	}
	
	//hulp methode om lijst van klanten te maken
	private ArrayList<Klant> maakKlantenList(ResultSet rs) throws SQLException {
		ArrayList<Klant> klantList = new ArrayList<Klant>();
		while(rs.next()) {
			Klant klant = new Klant.KlantBuilder(rs.getString("voornaam"), rs.getString("achternaam")).
					idKlant(rs.getInt("klant_id")).build();
			klantList.add(klant);
		}
		return klantList;
	}
	
	//hulp methode om laast gemaakt klant_id te krijgen
	private Integer getLastId(Connection con) throws SQLException {
		try(Statement st = con.createStatement(); 
			ResultSet rs = st.executeQuery ("SELECT last_insert_id() AS last_id FROM klant");) {
			rs.next();
			return rs.getInt("last_id");
		}
	}
}
