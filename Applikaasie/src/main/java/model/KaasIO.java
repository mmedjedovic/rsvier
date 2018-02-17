package model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import logic.Kaas;

public class KaasIO {
	
	//nieuwe kaas in database opslaan
	public static void nieuweKaasMaken(Kaas kaas) throws SQLException {
		Connection con = Connector.getInstance().getConnection();
		String sql = "INSERT INTO kaas (kaas_naam, prijs_in_kg, vooraad_in_kg,) VALUES (?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, kaas.getNaam());
		ps.setBigDecimal(2, kaas.getPrijsInKg());
		ps.setBigDecimal(3, kaas.getVooraadInKg());
		ps.executeQuery();
	}
	
	//een speciefiek kaas uit database halen
	public static Kaas getKaas(Integer kaasId) throws SQLException {
		String sql = "SELECT * FROM kaas WHERE kaas_id = ?";
		Connection con = Connector.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		return hulpGetKazenLijst(rs).get(0);
	}
	
	//lijst van alle kazen maken
	public static ArrayList<Kaas> getKazenLijst() throws SQLException {
		String sql = "SELECT * FROM kaas WHERE kaas";
		Connection con = Connector.getInstance().getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		return hulpGetKazenLijst(rs);
	}
	
	public static BigDecimal getVooraadVanKaas(Integer kaasId, Connection con) throws SQLException {
		String sql = "SELECT vooraad_in_kg FROM kaas WHERE kaas_id = ?";
		BigDecimal huidigeVooraad = null;
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, kaasId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			huidigeVooraad = rs.getBigDecimal("vooraad_in_kg");
		}
		return huidigeVooraad;
	}
	
	//vooraad aantaal aanpassen
	public static void vooraadAanpassen(Integer kaasId, BigDecimal kg, Connection con) throws SQLException {
		String sql = "UPDATE kaas SET vooraad_in_kg = ? WHERE kaas_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setBigDecimal(1, kg);
		ps.setInt(2, kaasId);
		ps.executeUpdate();
	}
	
	
	//hulp methode om lijst van kazen uit ResultSet maken
	private static ArrayList<Kaas> hulpGetKazenLijst(ResultSet rs) throws SQLException {
		ArrayList<Kaas> kazenLijst = new ArrayList<Kaas>();
		while(rs.next()) {
			Kaas kaas = new Kaas.KaasBuilder(rs.getString("kaas_naam")).kaasId(rs.getInt("kaas_id")).
					prijsInKg(rs.getBigDecimal("prijs_in_kg")).vooraadInKg(rs.getBigDecimal("vooraad_in_kg")).build();
			kazenLijst.add(kaas);
		}
		return kazenLijst;
	}
}
