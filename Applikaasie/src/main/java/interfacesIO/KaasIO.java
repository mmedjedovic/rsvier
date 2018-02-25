package interfacesIO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;

import model.Kaas;
import util.ExceptionIO;

public interface KaasIO {
	
	public void nieuweKaasMaken(Kaas kaas) throws ExceptionIO;
	
	public Kaas getKaas(Integer kaasId) throws ExceptionIO;
	
	public ArrayList<Kaas> getKazenLijst() throws ExceptionIO;
	
	public void vooraadAanpassen(Kaas kaas, BigDecimal nieuweVooraad, Connection con) throws ExceptionIO;
	
}
