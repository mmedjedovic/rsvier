package interfacesIO;

import java.util.ArrayList;

import model.Klant;
import util.ExceptionIOImpl;

public interface KlantIO {
	
	public void maakNieuweKlant(Klant klant) throws ExceptionIOImpl;
	
	public Klant getKlant(Integer klantId) throws ExceptionIOImpl;
	
	public ArrayList<Klant> getKlanten() throws ExceptionIOImpl;

}
