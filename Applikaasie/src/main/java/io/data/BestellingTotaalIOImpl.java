package io.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.interfaces.BestellingDetailsIO;
import io.interfaces.BestellingTotaalIO;
import io.interfaces.FactoryIO;
import io.interfaces.KlantIO;
import model.Bestelling;
import model.Klant;
import util.ExceptionIO;
import model.Bestelling.Status;

public class BestellingTotaalIOImpl implements BestellingTotaalIO{
	
	//bestelling_totaal tabel wordt gemaakt
	@Override
	public Integer maakBestellingTotaal(Bestelling bestelling) throws ExceptionIO{
		Integer index = -1;
		String sql = "INSERT INTO bestelling_totaal(klant_id, totaal_prijs, bestelling_datum, status) VALUES(?, ?, ?, ?)";
		try(Connection con = Connector.getInstance().getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			Date bestellingDatum = new Date(bestelling.getBestellingDate().getTime());
			ps.setInt(1,  bestelling.getKlantId());
			ps.setBigDecimal(2, bestelling.getTotaalPrijs());
			ps.setDate(3, bestellingDatum);
			ps.setString(4, bestelling.getStatus().name());
			ps.executeUpdate();
			try(ResultSet rs = ps.getGeneratedKeys()) {
				if(rs.isBeforeFirst()) {
					rs.next();
					index = rs.getInt(1);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt nieuwe bestelling_totaal aan te maken");
		}
		return index;
	}

	//methode om overzicht van bestellingen te maken van specifiek klant, open of gesloten
	@Override
	public ArrayList<Bestelling> getBestellingenPerKlant(Klant klant, Status status) throws ExceptionIO {
		String sql = "SELECT * FROM bestelling_totaal WHERE  klant_id = ? AND status = ?";
		try(Connection con = Connector.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, klant.getKlantId());
			ps.setString(2, status.name());
			try(ResultSet rs = ps.executeQuery();) {
				return hulpGetBestellingen(rs, klant.getKlantId());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt overzicht van bestellingan van gegeven klant aan te maken");
		}
	}
	@Override
	//methode om overzicht van alle bestellingen van een specifiek klant opvragen
	public ArrayList<Bestelling> getAlleBestellingenPerKlant(Klant klant) throws ExceptionIO {
		String sql = "SELECT * FROM bestelling_totaal WHERE klant_id = ?";
		try(Connection con = Connector.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, klant.getKlantId());
			try(ResultSet rs = ps.executeQuery();) {
				return hulpGetBestellingen(rs, klant.getKlantId());
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
	private ArrayList<Bestelling> hulpGetBestellingen(ResultSet rs, Integer klantId) throws SQLException {
		ArrayList<Bestelling> bestellingLijst = new ArrayList<Bestelling>();
		while(rs.next()) {
			java.util.Date date = new Date(rs.getDate("bestelling_datum").getTime());
			Status status = Status.valueOf("OPEN");
			Bestelling bestelling = new Bestelling.BestellingBuilder(klantId).
					totaalPrijs(rs.getBigDecimal("totaal_prijs")).bestellingDate(date).bestellingId(rs.getInt("bestelling_id")).status(status).build();
			bestellingLijst.add(bestelling);
		}
		return bestellingLijst;
	}
		
}
