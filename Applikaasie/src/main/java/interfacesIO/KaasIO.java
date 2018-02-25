package interfacesIO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;

import model.Kaas;
import util.ExceptionIOImpl;

public interface KaasIO {
	
	public void nieuweKaasMaken(Kaas kaas) throws ExceptionIOImpl;
	
	public Kaas getKaas(Integer kaasId) throws ExceptionIOImpl;
	
	public ArrayList<Kaas> getKazenLijst() throws ExceptionIOImpl;
	
	public void vooraadAanpassen(Kaas kaas, BigDecimal nieuweVooraad, Connection con) throws ExceptionIOImpl;
	
}
