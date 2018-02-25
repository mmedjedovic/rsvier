package interfacesIO;

import java.sql.Connection;
import java.sql.SQLException;

import model.Bestelling;
import util.ExceptionIOImpl;

public interface BestellingDetailsIO {
	 
	public void maakBestellingDetails(Bestelling bestelling) throws ExceptionIOImpl;
	
}
