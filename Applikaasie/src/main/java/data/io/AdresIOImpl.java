package data.io;

import java.sql.*;

import interfaces.io.AdresIO;
import model.Adres;
import model.Klant;
import util.ExceptionIO;

public class AdresIOImpl implements AdresIO{
	
	private static AdresIOImpl instance = null;
	
	private AdresIOImpl() {}
	
	public static AdresIOImpl getInstance() {
		if(instance == null) {
			instance = new AdresIOImpl();
		}
		return instance;
	}
	
	//een adres maken
	@Override
	public void maakNieuweAdres(Adres adres) throws ExceptionIO {
		
		String sql = "INSERT INTO adres(klant_id, straatnaam, huisnummer, toevoegingsnummer, postcode, woonplaats) "
						+ "VALUES (?, ?, ?, ?, ?, ?)";
		try(Connection con = Connector.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, adres.getKlant().getKlantId());
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
	public Adres getAdres(Klant klant) throws ExceptionIO {
		String sql = "SELECT * FROM adres WHERE klant_id = ?";
		try(Connection con = Connector.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, klant.getKlantId());
			try(ResultSet rs = ps.executeQuery();) {
				return helpGetAdres(rs, klant);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt adres uit database halen");
		}
	}
	
	//hulp methode om adres uit database halen
	private Adres helpGetAdres(ResultSet rs, Klant klant) throws SQLException {
		Adres adres = null;
			if(rs.next()) {
				adres = new Adres.AdresBuilder(klant).straatNaam(rs.getString("straatnaam")).huisnummer(rs.getString("huisnummer")).
						toevoegingHuisnummer(rs.getString("toevoegingsnummer")).postcode(rs.getString("postcode")).woonplaats(rs.getString("woonplaats")).build();
		}
		return adres;
	}

}

