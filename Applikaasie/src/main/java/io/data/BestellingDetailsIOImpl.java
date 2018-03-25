package io.data;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import io.interfaces.BestellingDetailsIO;
import io.interfaces.FactoryIO;
import io.interfaces.KaasIO;
import model.Bestelling;
import model.Kaas;
import util.ExceptionIO;

public class BestellingDetailsIOImpl implements BestellingDetailsIO{
	
	
	//besteling in database opslaan in tabel van bestelling_details 
	@Override
	public void maakBestellingDetails(Bestelling bestelling, Integer bestellingId) throws Exception {
		String sql = "INSERT INTO bestelling_details(bestelling_id, kaas_id, hoeveelheid_in_kg, totaal_prijs) VALUES(?,?,?,?)";
		try(Connection con = Connector.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			HashMap<Kaas, BigDecimal> kazenList = bestelling.getBesteldeKazenList();
			for(Map.Entry<Kaas, BigDecimal> entry: kazenList.entrySet()) {
				Kaas kaas = entry.getKey();
				BigDecimal hoeveelheidInKg = entry.getValue();
				BigDecimal totaalPrijs = hoeveelheidInKg.multiply(kaas.getPrijsInKg(), new MathContext(4));
				ps.setInt(1, bestellingId);
				ps.setInt(2, kaas.getKaasId());
				ps.setBigDecimal(3, hoeveelheidInKg);
				ps.setBigDecimal(4, totaalPrijs);
				ps.executeUpdate();
				BigDecimal huidigeVooraad = kaas.getVooraadInKg();
				BigDecimal nieuweVooraad = huidigeVooraad.subtract(hoeveelheidInKg);
				//vooraad aanpassen in kaas tabel
				FactoryIO factoryIo = FactoryIOImpl.gestInstance();
				KaasIO kaasIo = factoryIo.getKaasIO();
				kaasIo.vooraadAanpassen(kaas, nieuweVooraad, con);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt om bestellingsdetails aan te maken in database");
		}
	}
	
	@Override
	public ArrayList<ArrayList<String>> getBestellingsDetails(Bestelling bestelling) throws ExceptionIO {
		ArrayList<ArrayList<String>> bestellingDetailsList = new ArrayList<>();
		String sql = "SELECT * FROM bestelling_details WHERE bestelling_id = ?";
		try(Connection con = Connector.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, bestelling.getBestellingId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bestellingDetailsList.add(getDetails(rs, con));
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt bestellingdetails uit database te halen");
		}
		return bestellingDetailsList;
	}
	
	private ArrayList<String> getDetails(ResultSet rs, Connection con) throws SQLException {
		ArrayList<String> detailsList = new ArrayList<>();
		String kaasId = Integer.toString(rs.getInt("kaas_id"));
		detailsList.add(kaasId);
		detailsList.add(rs.getString("hoeveelheid_in_kg"));
		detailsList.add(rs.getString("totaal_prijs"));
		return detailsList;
	}

		
}
