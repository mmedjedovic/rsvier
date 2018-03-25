package io.interfaces;

import java.util.ArrayList;

import model.Adres;
import model.Klant;
import util.ExceptionIO;

public interface KlantIO {
	
	public Integer maakNieuweKlant(Klant klant, Adres adres) throws Exception;
	
	public Klant getKlant(Integer klantId) throws Exception;
	
	public ArrayList<Klant> getKlanten() throws Exception;
	
	public void deleteKlant(Integer klantId) throws Exception;

}
