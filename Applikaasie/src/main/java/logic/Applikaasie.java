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
	
	
	public static void main(String[] args) {
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		Klant klant = null;
		
		/**
		try {
			//klant maken
			date = sf.parse("08-8-1975");
			System.out.println(sf.format(date));
			klant = new Klant.KlantBuilder("Slavo", "Slavic").geboorteDatum(date).idKlant(6).build();	
			//testen maakNieuweKlant methode
			KlantIO.maakNieuweKlant(klant);
		} catch (ExceptionIO e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		/**
		try {
			//adres maken
			Adres adres = new Adres.AdresBuilder(klant).straatNaam("straatNaam").huisnummer("huisnumer").
					toevoegingHuisnummer("toevoegingHuisnummer").postcode("postcode").woonplaats("woonplaats").build();
			AdresIO.maakNieuweAdres(adres);
		} catch (ExceptionIO e) {
			System.out.println(e.getMessage());
		}*/
		
		/**
		//adres uit datbase te halen
		try {
			Adres adres = AdresIO.getAdres(klant);
			System.out.println(adres.getKlant().getVoornaam() + "  " + adres.getHuisnummer());
		} catch (ExceptionIO e) {
			e.printStackTrace();
		}*/
		
		/**
		//testen getKlant methode
		try {
			Klant klant = KlantIO.getKlant(2);
			date = klant.getGeboorteDatum();
			System.out.println(klant.getVoornaam() + " " + klant.getAchternaam() + " " + sf.format(date));
		} catch (ExceptionIO e) {
			e.printStackTrace();
		}*/
		
		/**
		//testen getKlanten methode
		ArrayList <Klant> klantLijst = new ArrayList<Klant>();
		try {
			klantLijst = KlantIO.getKlanten();
			for(Klant klant: klantLijst) {
				date = klant.getGeboorteDatum();
				System.out.println(klant.getVoornaam() + " " + klant.getAchternaam() + " " + sf.format(date));
			}
		} catch (ExceptionIO e) {
			e.printStackTrace();
		}*/
		
		/**
		//twee kazen aanmaken
		Kaas kaas1 = new Kaas.KaasBuilder("Amsterdam old").prijsInKg(new BigDecimal(2.5)).vooraadInKg(new BigDecimal(500)).kaasId(1).build();
		Kaas kaas2 = new Kaas.KaasBuilder("Gouda belegen").prijsInKg(new BigDecimal(2.0)).vooraadInKg(new BigDecimal(600)).kaasId(2).build();
		try {
			//testen nieuweKaasMaken methode
			KaasIO.nieuweKaasMaken(kaas1);
			KaasIO.nieuweKaasMaken(kaas2);
		} catch (ExceptionIO e) {
			e.printStackTrace();
		}*/
		
		/**
		//testen getKaas methode
		try {
			Kaas kaas = KaasIO.getKaas(2);
			System.out.println(kaas.getNaam() + " " + kaas.getPrijsInKg() + " " + kaas.getVooraadInKg());
		} catch (ExceptionIO e) {
			e.printStackTrace();
		}*/
		
		/**
		//testen getKazenLijst methode
		try {
			ArrayList<Kaas> kazenLijst = KaasIO.getKazenLijst();
			for(Kaas kaas: kazenLijst) {
				System.out.println(kaas.getNaam() + " " + kaas.getPrijsInKg() + " " + kaas.getVooraadInKg());
			}
		} catch (ExceptionIO e) {
			e.printStackTrace();
		}*/
		
		/**
		//testen of bestellingen worden gemaakt en opgeslagen
		try {
			date = sf.parse("3-12-1965");
			System.out.println(sf.format(date));
			Klant klant = new Klant.KlantBuilder("Mirko", "Varesak").geboorteDatum(date).idKlant(4).build();	
			Kaas kaas1 = new Kaas.KaasBuilder("Amsterdam old").prijsInKg(new BigDecimal(2.5)).vooraadInKg(new BigDecimal(500)).kaasId(1).build();
			Kaas kaas2 = new Kaas.KaasBuilder("Gouda belegen").prijsInKg(new BigDecimal(2.0)).vooraadInKg(new BigDecimal(600)).kaasId(2).build();
			HashMap<Kaas, BigDecimal> map = new HashMap<Kaas, BigDecimal>();
			map.put(kaas1, new BigDecimal(50));
			map.put(kaas2, new BigDecimal(30));
			Bestelling bestelling = new Bestelling.BestellingBuilder(klant).besteldeKazenList(map).totaalPrijs(new BigDecimal(185)).
					bestellingDate(date).status(Status.OPEN).build();
			BestellingIO.maakNieuweBestelling(bestelling);
	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionIO e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/**
		//testen of lijst van gevraagde bestellingen wordt gemaakt
		ArrayList<Bestelling> bestellingLijst;
		try {
			bestellingLijst = BestellingIO.getBestellingenPerKlant(4, Status.OPEN);
			for(Bestelling b: bestellingLijst) {
				System.out.println(b.getBestellingId() + " " + b.getStatus().name());
			}
		} catch (ExceptionIO e) {
			e.printStackTrace();
		}*/
		
		
		
	}
	
}
