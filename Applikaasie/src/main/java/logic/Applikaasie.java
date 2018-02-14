package logic;

import java.sql.SQLException;

import logic.Klant.KlantBuilder;
import model.KlantIO;

public class Applikaasie {
	
	
	public static void main(String[] args) {
		
		BezorgAdres bezorgAdres = new BezorgAdres.BezorgAdresBuilder(1, "Momo", "Uzeir").
				straatNaam("Brinkstrat").huisnummer("74").toevoegingHuisnummer("1").postcode("1097").woonplaats("Amsterdam").build();
		Klant klant = new Klant.KlantBuilder("Marko", "Mededovic").
				straatNaam("Brinkstraat").huisnummer("74").toevoegingHuisnummer("1").postcode("1097").woonplaats("Amsterdam").bezorgAdres(bezorgAdres).build();
		
		try {
			KlantIO.maakNieuweKlant(klant);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(klant.getVoornaam() + " " + klant.getBezorgAdres().getAchternaam());
	}

	
}
