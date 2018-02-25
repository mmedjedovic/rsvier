package interfacesIO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Bestelling;
import model.Bestelling.Status;
import util.ExceptionIOImpl;

public interface BestellingTotaalIO {
	
	public void maakBestellingTotaal(Bestelling bestelling) throws ExceptionIOImpl;
	
	public ArrayList<Bestelling> getBestellingenPerKlant(Integer klantId, Status status) throws ExceptionIOImpl;
	
	public void changeStatusBestelling(Bestelling bestelling, Status status) throws ExceptionIOImpl;

}
