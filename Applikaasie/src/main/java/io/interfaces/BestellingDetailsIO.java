package io.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

import model.Bestelling;
import util.ExceptionIO;

public interface BestellingDetailsIO {
	 
	public void maakBestellingDetails(Bestelling bestelling) throws ExceptionIO;
	
}
