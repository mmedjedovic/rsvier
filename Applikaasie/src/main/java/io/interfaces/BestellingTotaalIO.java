package io.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Bestelling;
import model.Bestelling.Status;
import model.Klant;
import util.ExceptionIO;

public interface BestellingTotaalIO {
	
	public Integer maakBestellingTotaal(Bestelling bestelling) throws ExceptionIO;
	
	public ArrayList<Bestelling> getBestellingenPerKlant(Klant klant, Status status) throws ExceptionIO;
	
	public ArrayList<Bestelling> getAlleBestellingenPerKlant(Klant klant) throws ExceptionIO;
	
	public void changeStatusBestelling(Bestelling bestelling, Status status) throws ExceptionIO;

}
