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
import io.data.FactoryIOImpl;
import io.data.KaasIOImpl;
import io.data.KlantIOImpl;
import io.interfaces.AdresIO;
import io.interfaces.BestellingTotaalIO;
import io.interfaces.FactoryIO;
import io.interfaces.KlantIO;
import model.*;
import model.Bestelling.Status;
import util.ExceptionIO;



public class Applikaasie {
	
	
		FactoryIO factoryIo = FactoryIOImpl.gestInstance();
		
		
		public void maakNieuweKlantenAdres(String voornaam, String achternaam, String straatNaam, 
				String huisnummer, String toevoegingHuisnummer, String postcode, String woonplaats) throws ExceptionIO {
			Klant klant = new Klant.KlantBuilder(voornaam, achternaam).build();
			KlantIO klantIo = factoryIo.getKlantIO();
			Integer klantId = klantIo.maakNieuweKlant(klant);
			Adres adres = new Adres.AdresBuilder(klantId).straatNaam(straatNaam).huisnummer(huisnummer).
					toevoegingHuisnummer(toevoegingHuisnummer).postcode(postcode).woonplaats(woonplaats).build();
			AdresIO adresIo = factoryIo.getAdresIO();
			adresIo.maakNieuweAdres(adres);
		}
		
		public ArrayList<Klant> getKlantenLijst() throws ExceptionIO {
			return factoryIo.getKlantIO().getKlanten();
		}
		
		public void deleteKlant(Integer klantId) throws ExceptionIO {
			factoryIo.getKlantIO().deleteKlant(klantId);
		}
		
		public Adres getAdres(Integer klantId) throws ExceptionIO {
			return factoryIo.getAdresIO().getAdres(klantId);
		}
		
		public void artikelMaken(String kaasNaam, Double prijsInKg, Double voorraadInKg) throws ExceptionIO {
			BigDecimal prijsInKgBigDec = BigDecimal.valueOf(prijsInKg);
			BigDecimal vooraadInKgBigdec = BigDecimal.valueOf(voorraadInKg);
			Kaas kaas = new Kaas.KaasBuilder(kaasNaam).prijsInKg(prijsInKgBigDec).vooraadInKg(vooraadInKgBigdec).build();
			factoryIo.getKaasIO().nieuweKaasMaken(kaas);
		}
		
		public Kaas getKaas(int kaasId) throws ExceptionIO {
			return factoryIo.getKaasIO().getKaas(kaasId);
		}
		
		public void deleteKaas(Integer kaasId) throws ExceptionIO {
			factoryIo.getKaasIO().deleteKaas(kaasId);
		}
		
		public ArrayList<Kaas> getKaasLijst() throws ExceptionIO {
			return factoryIo.getKaasIO().getKazenLijst();
		}
		
		public void bestellingMaken(HashMap<Kaas, BigDecimal> besteldeKazenLijst, Klant klant) throws ExceptionIO {
			BigDecimal totaalPrijs = new BigDecimal(0);
			Date date = new Date();
			for(Map.Entry<Kaas, BigDecimal> entry: besteldeKazenLijst.entrySet()) {
				totaalPrijs = entry.getKey().getPrijsInKg().multiply(entry.getValue());
			}
			Bestelling bestelling = new Bestelling.BestellingBuilder(klant.getKlantId()).status(Status.OPEN).
					besteldeKazenList(besteldeKazenLijst).bestellingDate(date).totaalPrijs(totaalPrijs).build();
			Integer bestellingId = factoryIo.getBestllingTotaalIO().maakBestellingTotaal(bestelling);
			factoryIo.getBestellingsDetailsIO().maakBestellingDetails(bestelling, bestellingId);
		}
		
		public ArrayList<Bestelling> getBestellingen(Klant klant, String status) throws ExceptionIO {
			ArrayList<Bestelling> bestellingLijst = new ArrayList<Bestelling>();
			BestellingTotaalIO bestellingTotaalIO = factoryIo.getBestllingTotaalIO();
			if(status.equals("open")) {
				bestellingLijst = bestellingTotaalIO.getBestellingenPerKlant(klant, Status.OPEN);
			} if (status.equals("gesloten")) {
				bestellingLijst = bestellingTotaalIO.getBestellingenPerKlant(klant, Status.GESLOTEN);
			} else {
				bestellingLijst = bestellingTotaalIO.getAlleBestellingenPerKlant(klant);
			}
			return bestellingLijst;
		}
		
		public ArrayList<ArrayList<String>> getBestellingDetails(Bestelling bestelling) throws ExceptionIO {
			return factoryIo.getBestellingsDetailsIO().getBestellingsDetails(bestelling);
		}
	
		
		
		
		
		
		
		
}
