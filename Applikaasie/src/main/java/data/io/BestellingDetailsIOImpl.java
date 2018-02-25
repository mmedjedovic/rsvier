package data.io;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import interfaces.io.BestellingDetailsIO;
import model.Bestelling;
import model.Kaas;
import util.ExceptionIO;

public class BestellingDetailsIOImpl implements BestellingDetailsIO{
	
	private static BestellingDetailsIOImpl instance = null;
	
	private BestellingDetailsIOImpl() {}
	
	public static BestellingDetailsIOImpl getInstance() {
		if(instance == null) {
			instance = new BestellingDetailsIOImpl();
		}
			return instance;
	}
	
	//besteling in database opslaan in tabel van bestelling_details 
	@Override
	public void maakBestellingDetails(Bestelling bestelling) throws ExceptionIO {
		String sql = "INSERT INTO bestelling_details(bestelling_id, kaas_id, hoeveelheid_in_kg, totaal_prijs) VALUES(?,?,?,?)";
		try(Connection con = Connector.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			HashMap<Kaas, BigDecimal> kazenList = bestelling.getBesteldeKazenList();
			for(Map.Entry<Kaas, BigDecimal> entry: kazenList.entrySet()) {
				Kaas kaas = entry.getKey();
				BigDecimal hoeveelheidInKg = entry.getValue();
				BigDecimal totaalPrijs = hoeveelheidInKg.multiply(kaas.getPrijsInKg(), new MathContext(4));
				Integer bestellingId = getLastId(con);
				ps.setInt(1, bestellingId);
				ps.setInt(2, kaas.getKaasId());
				ps.setBigDecimal(3, hoeveelheidInKg);
				ps.setBigDecimal(4, totaalPrijs);
				ps.executeUpdate();
				BigDecimal huidigeVooraad = kaas.getVooraadInKg();
				BigDecimal nieuweVooraad = huidigeVooraad.subtract(hoeveelheidInKg);
				//vooraad aanpassen in kaas tabel
				KaasIOImpl kaasIO = KaasIOImpl.getInstance();
				kaasIO.vooraadAanpassen(kaas, nieuweVooraad, con);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIO("Niet gelukt om bestellingsdetails aan te maken in database");
		}
	}
	
	//hulp methode om laatste gecrieerde bestelling_id van bestelling_totaal te krijgen
	private Integer getLastId(Connection con) throws SQLException {
		try(Statement st = con.createStatement(); 
			ResultSet rs = st.executeQuery ("SELECT last_insert_id() AS last_id FROM bestelling_totaal");) {
			rs.next();
			return rs.getInt("last_id");
		}
	}

		
}
