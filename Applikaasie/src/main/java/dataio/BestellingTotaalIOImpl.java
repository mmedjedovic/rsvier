package dataio;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfacesIO.BestellingDetailsIO;
import interfacesIO.BestellingTotaalIO;
import model.Bestelling;
import model.Klant;
import util.ExceptionIO;
import model.Bestelling.Status;

public class BestellingTotaalIOImpl implements BestellingTotaalIO{
	
	private static BestellingTotaalIOImpl instance = null;
	
	private BestellingTotaalIOImpl() {}
	
	public static BestellingTotaalIOImpl getInstance() {
		if(instance == null) {
			instance = new BestellingTotaalIOImpl();
		}
		return instance;
	}
	
	//bestelling_totaal tabel wordt gemaakt
	@Override
	public void maakBestellingTotaal(Bestelling bestelling) throws ExceptionIO{
		String sql = "INSERT INTO bestelling_totaal(klant_id, totaal_prijs, bestelling_datum, status) VALUES(?, ?, ?, ?)";
		try(Connection con = Connector.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			Date bestellingDatum = new Date(bestelling.getBestellingDate().getTime());
			ps.setInt(1,  bestelling.getKlant().getKlantId());
			ps.setBigDecimal(2, bestelling.getTotaalPrijs());
			ps.setDate(3, bestellingDatum);
			ps.setString(4, bestelling.getStatus().name());
			ps.executeUpdate();
			ps.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt nieuwe bestelling_totaal aan te maken");
		}
	}

	//methode om overzicht van bestellingen te maken van specifiek klant, open of gesloten
	@Override
	public ArrayList<Bestelling> getBestellingenPerKlant(Integer klantId, Status status) throws ExceptionIO {
		String sql = "SELECT * FROM bestelling_totaal WHERE  klant_id = ? AND status = ?";
		try(Connection con = Connector.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, klantId);
			ps.setString(2, status.name());
			try(ResultSet rs = ps.executeQuery();) {
				KlantIOImpl klantIO = KlantIOImpl.getInstance();
				Klant klant = klantIO.getKlant(klantId);
				return hulpGetBestellingen(rs, klant);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt overzicht van bestellingan van gegeven klant aan te maken");
		}
	}
	
	//wijzigen status van bestellingen
	@Override
	public void changeStatusBestelling(Bestelling bestelling, Status status) throws ExceptionIO {
		String sql = "UPDATE bestelling_totaal SET status = ? WHERE bestelling_id = ?";
		try(Connection con = Connector.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, Status.GESLOTEN.name());
			ps.setInt(2, bestelling.getBestellingId());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt om status van bestelling te wijzigen");
		}
	}
	
	
	//hulp methode om bestelligen  uit ResultSet te crieeren
	private ArrayList<Bestelling> hulpGetBestellingen(ResultSet rs, Klant klant) throws SQLException {
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
		
}
