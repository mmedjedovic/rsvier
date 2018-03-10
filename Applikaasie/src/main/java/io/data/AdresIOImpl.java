package io.data;

import java.sql.*;
import java.util.ArrayList;

import io.interfaces.AdresIO;
import model.Adres;
import model.Klant;
import util.ExceptionIO;

public class AdresIOImpl implements AdresIO{
	
	private static AdresIOImpl instance = null;
	
	//een adres maken
	@Override
	public void maakNieuweAdres(Adres adres) throws ExceptionIO {
		
		String sql = "INSERT INTO adres(klant_id, straatnaam, huisnummer, toevoegingsnummer, postcode, woonplaats) "
						+ "VALUES (?, ?, ?, ?, ?, ?)";
		try(Connection con = Connector.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, adres.getKlantId());
			ps.setString(2, adres.getStraatNaam());
			ps.setString(3, adres.getHuisnummer());
			ps.setString(4, adres.getToevoegingHuisnummer());
			ps.setString(5, adres.getPostcode());
			ps.setString(6, adres.getWoonplaats());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt nieuwe adres in database aanmaken");
		}
	}
	
	//adres uit database halen
	@Override
	public Adres getAdres(Integer klantId) throws ExceptionIO {
		String sql = "SELECT * FROM adres WHERE klant_id = ?";
		try(Connection con = Connector.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, klantId);
			try(ResultSet rs = ps.executeQuery();) {
				return helpGetAdres(rs, klantId);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt adres uit database halen");
		}
	}
	
	//hulp methode om adres uit database halen
	private Adres helpGetAdres(ResultSet rs, Integer klantId) throws SQLException {
		Adres adres = null;
			if(rs.next()) {
				adres = new Adres.AdresBuilder(klantId).straatNaam(rs.getString("straatnaam")).huisnummer(rs.getString("huisnummer")).
						toevoegingHuisnummer(rs.getString("toevoegingsnummer")).postcode(rs.getString("postcode")).woonplaats(rs.getString("woonplaats")).build();
		}
		return adres;
	}

}

