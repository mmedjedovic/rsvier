package logic;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import io.data.AdresIOImpl;
import io.data.BestellingTotaalIOImpl;
import io.data.KaasIOImpl;
import io.data.KlantIOImpl;
import model.Bestelling;
import model.Bestelling.Status;
import model.Klant;
import model.Klant.KlantBuilder;
import util.ExceptionIO;
import model.Adres;


public class Applikaasie {
	
	
	
		
		
		public void maakNieuweKlantenAdres(String voornaam, String achternaam, String straatNaam, 
				String huisnummer, String toevoegingHuisnummer, String postcode, String woonplaats) throws ExceptionIO {
			Klant klant = new Klant.KlantBuilder(voornaam, achternaam).build();
			KlantIOImpl klantIo = KlantIOImpl.getInstance();
			Integer klantId = klantIo.maakNieuweKlant(klant);
			Adres adres = new Adres.AdresBuilder(klantId).straatNaam(straatNaam).huisnummer(huisnummer).
					toevoegingHuisnummer(toevoegingHuisnummer).postcode(postcode).woonplaats(woonplaats).build();
			AdresIOImpl adresIo = AdresIOImpl.getInstance();
			adresIo.maakNieuweAdres(adres);
		}
		
		public ArrayList<Klant> getKlantenLijst() throws ExceptionIO {
			return KlantIOImpl.getInstance().getKlanten();
		}
		
		public Adres getAdres(Integer klantId) throws ExceptionIO {
			return AdresIOImpl.getInstance().getAdres(klantId);
		}
		
		
		
		
		
		
		
		
		
		
		
		
	
}
