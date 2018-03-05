package logic;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.data.AdresIOImpl;
import io.data.BestellingDetailsIOImpl;
import io.data.BestellingTotaalIOImpl;
import io.data.KaasIOImpl;
import io.data.KlantIOImpl;
import io.interfaces.BestellingTotaalIO;
import model.*;
import model.Bestelling.Status;
import util.ExceptionIO;



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
		
		public void deleteKlant(Integer klantId) throws ExceptionIO {
			KlantIOImpl.getInstance().deleteKlant(klantId);
		}
		
		public Adres getAdres(Integer klantId) throws ExceptionIO {
			return AdresIOImpl.getInstance().getAdres(klantId);
		}
		
		public void artikelMaken(String kaasNaam, Double prijsInKg, Double voorraadInKg) throws ExceptionIO {
			BigDecimal prijsInKgBigDec = BigDecimal.valueOf(prijsInKg);
			BigDecimal vooraadInKgBigdec = BigDecimal.valueOf(voorraadInKg);
			Kaas kaas = new Kaas.KaasBuilder(kaasNaam).prijsInKg(prijsInKgBigDec).vooraadInKg(vooraadInKgBigdec).build();
			KaasIOImpl.getInstance().nieuweKaasMaken(kaas);
		}
		
		public void deleteKaas(Integer kaasId) throws ExceptionIO {
			KaasIOImpl.getInstance().deleteKaas(kaasId);
		}
		
		public ArrayList<Kaas> getKaasLijst() throws ExceptionIO {
			return KaasIOImpl.getInstance().getKazenLijst();
		}
		
		public void bestellingMaken(HashMap<Kaas, BigDecimal> besteldeKazenLijst, Integer klantId) throws ExceptionIO {
			BigDecimal totaalPrijs = new BigDecimal(0);
			Date date = new Date();
			for(Map.Entry<Kaas, BigDecimal> entry: besteldeKazenLijst.entrySet()) {
				totaalPrijs = totaalPrijs.add(entry.getKey().getPrijsInKg().multiply(entry.getValue()));
			}
			Bestelling bestelling = new Bestelling.BestellingBuilder(klantId).status(Status.OPEN).
					besteldeKazenList(besteldeKazenLijst).bestellingDate(date).totaalPrijs(totaalPrijs).build();
			BestellingTotaalIOImpl.getInstance().maakBestellingTotaal(bestelling);
			BestellingDetailsIOImpl.getInstance().maakBestellingDetails(bestelling);
		}
	
		
		
		
		
		
		
		
}
