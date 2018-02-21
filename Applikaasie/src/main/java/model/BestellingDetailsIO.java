package model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import logic.Bestelling;
import logic.Kaas;

public class BestellingDetailsIO {
	
	//besteling in database opslaan in tabel van bestelling_details 
	public static void maakBestellingDetails(Connection con, Bestelling bestelling, Integer bestellingId) throws SQLException {
		String sql = "INSERT INTO bestelling_details(bestelling_id, kaas_id, hoeveelheid_in_kg, totaal_prijs) VALUES(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
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
			KaasIO.vooraadAanpassen(kaas, nieuweVooraad, con);
		}
	}
	
	
	
	
	
}
