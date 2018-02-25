package interfacesIO;

import model.Adres;
import model.Klant;
import util.ExceptionIOImpl;

public interface AdresIO {
	
	public void maakNieuweAdres(Adres adres) throws ExceptionIOImpl;
	
	public Adres getAdres(Klant klant) throws ExceptionIOImpl;
	
	
}
