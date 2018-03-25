package io.interfaces;

import model.Adres;
import model.Klant;
import util.ExceptionIO;

public interface AdresIO {
	
	public void maakNieuweAdres(Adres adres) throws Exception;
	
	public Adres getAdres(Integer klantId) throws Exception;
	
	
}
