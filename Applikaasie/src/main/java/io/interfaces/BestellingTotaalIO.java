package io.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Bestelling;
import model.Bestelling.Status;
import model.Klant;
import util.ExceptionIO;

public interface BestellingTotaalIO {
	
	public Integer maakBestellingTotaal(Bestelling bestelling) throws Exception;
	
	public ArrayList<Bestelling> getBestellingenPerKlant(Klant klant, Status status) throws Exception;
	
	public ArrayList<Bestelling> getAlleBestellingenPerKlant(Klant klant) throws Exception;
	
	public void changeStatusBestelling(Bestelling bestelling, Status status) throws Exception;

}
