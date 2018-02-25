package dataio;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import interfacesIO.KaasIO;
import model.Kaas;
import util.ExceptionIOImpl;

public class KaasIOImpl implements KaasIO{
	
	private static KaasIOImpl instance = null;
	
	private KaasIOImpl() {}
	
	public static KaasIOImpl getInstance() {
		if(instance == null) {
			instance = new KaasIOImpl();
		}
		return instance;
	}
	
	//nieuwe kaas in database opslaan
	@Override
	public void nieuweKaasMaken(Kaas kaas) throws ExceptionIOImpl {
		String sql = "INSERT INTO kaas (kaas_naam, prijs_in_kg, vooraad_in_kg) VALUES (?, ?, ?)";
		try(Connection con = Connector.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, kaas.getNaam());
			ps.setBigDecimal(2, kaas.getPrijsInKg());
			ps.setBigDecimal(3, kaas.getVooraadInKg());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIOImpl("Niet gelukt om nieuwe kaas in database aan te maken");
		}
	}
	
	//een speciefiek kaas uit database halen
	@Override
	public Kaas getKaas(Integer kaasId) throws ExceptionIOImpl {
		String sql = "SELECT * FROM kaas WHERE kaas_id = ?";
		try(Connection con = Connector.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, kaasId);
			try(ResultSet rs = ps.executeQuery();){
				return hulpGetKazenLijst(rs).get(0);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIOImpl("Niet gelukt om kaas uit database te halen");
		}
	}
	
	//lijst van alle kazen maken
	@Override
	public ArrayList<Kaas> getKazenLijst() throws ExceptionIOImpl {
		String sql = "SELECT * FROM kaas";
		try(Connection con = Connector.getInstance().getConnection(); 
										Statement st = con.createStatement();
												ResultSet rs = st.executeQuery(sql);) {
		return hulpGetKazenLijst(rs);
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIOImpl("Niet gelukt m list van kazen te halen uit database");
		}
	}
	
	
	//vooraad aantaal aanpassen
	@Override
	public void vooraadAanpassen(Kaas kaas, BigDecimal nieuweVooraad, Connection con) throws ExceptionIOImpl {
		String sql = "UPDATE kaas SET vooraad_in_kg = ? WHERE kaas_id = ?";
		try(PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setBigDecimal(1, nieuweVooraad);
			ps.setInt(2, kaas.getKaasId());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ExceptionIOImpl("Niet gelukt om vooraad van Kaas aan te passen");
		}
	}
	
	
	//hulp methode om lijst van kazen uit ResultSet maken
	private ArrayList<Kaas> hulpGetKazenLijst(ResultSet rs) throws SQLException {
		ArrayList<Kaas> kazenLijst = new ArrayList<Kaas>();
		while(rs.next()) {
			Kaas kaas = new Kaas.KaasBuilder(rs.getString("kaas_naam")).kaasId(rs.getInt("kaas_id")).
					prijsInKg(rs.getBigDecimal("prijs_in_kg")).vooraadInKg(rs.getBigDecimal("vooraad_in_kg")).build();
			kazenLijst.add(kaas);
		}
		return kazenLijst;
	}
}
