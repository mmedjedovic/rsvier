package io.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Bestelling;
import model.Bestelling.Status;
import util.ExceptionIO;

public interface BestellingTotaalIO {
	
	public void maakBestellingTotaal(Bestelling bestelling) throws ExceptionIO;
	
	public ArrayList<Bestelling> getBestellingenPerKlant(Integer klantId, Status status) throws ExceptionIO;
	
	public void changeStatusBestelling(Bestelling bestelling, Status status) throws ExceptionIO;

}
