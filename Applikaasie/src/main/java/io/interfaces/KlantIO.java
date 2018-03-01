package io.interfaces;

import java.util.ArrayList;

import model.Klant;
import util.ExceptionIO;

public interface KlantIO {
	
	public Integer maakNieuweKlant(Klant klant) throws ExceptionIO;
	
	public Klant getKlant(Integer klantId) throws ExceptionIO;
	
	public ArrayList<Klant> getKlanten() throws ExceptionIO;

}
