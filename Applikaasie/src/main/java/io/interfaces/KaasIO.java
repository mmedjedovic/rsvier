package io.interfaces;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;

import model.Kaas;
import util.ExceptionIO;

public interface KaasIO {
	
	public void nieuweKaasMaken(Kaas kaas) throws Exception;
	
	public Kaas getKaas(Integer kaasId) throws Exception;
	
	public ArrayList<Kaas> getKazenLijst() throws Exception;
	
	public void deleteKaas(Integer kaasId) throws Exception;
	
	public void vooraadAanpassen(Kaas kaas, BigDecimal nieuweVooraad, Connection con) throws Exception;
	
}
