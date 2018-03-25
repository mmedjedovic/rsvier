package io.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Bestelling;
import util.ExceptionIO;

public interface BestellingDetailsIO {
	 
	public void maakBestellingDetails(Bestelling bestelling, Integer bestellingId) throws Exception;
	
	public ArrayList<ArrayList<String>> getBestellingsDetails(Bestelling bestelling) throws Exception;
	
}
