package model;

import java.util.Date;

public class Klant {
	
	private final String voornaam;
	private final String achternaam;
	private final Integer klantId;
	
	
	//private constructor
	private Klant(KlantBuilder klantBuilder) {
		this.voornaam = klantBuilder.voornaam;
		this.achternaam = klantBuilder.achternaam;
		this.klantId = klantBuilder.idKlant;
	}
	
	
	public static class KlantBuilder {
		
		private final String voornaam;
		private final String achternaam;
		private Integer idKlant = -1;
		
		public KlantBuilder(String voornaam, String achterNaam) {
			this.voornaam = voornaam;
			this.achternaam = achterNaam;
		}
		
		public KlantBuilder idKlant(Integer idKlant) {
			this.idKlant = idKlant;
			return this;
		}
		
		public Klant build() {
			return new Klant(this);
		}
		
	}


	public String getVoornaam() {
		return voornaam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public Integer getKlantId() {
		return klantId;
	}

	
}

