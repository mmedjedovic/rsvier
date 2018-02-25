package model;

import java.util.Date;

public class Klant {
	
	private final String voornaam;
	private final String achternaam;
	private final Date geboorteDatum;
	private final Integer klantId;
	
	
	//private constructor
	private Klant(KlantBuilder klantBuilder) {
		this.voornaam = klantBuilder.voornaam;
		this.achternaam = klantBuilder.achternaam;
		this.klantId = klantBuilder.idKlant;
		this.geboorteDatum = klantBuilder.geboorteDatum;
	}
	
	
	public static class KlantBuilder {
		
		private final String voornaam;
		private final String achternaam;
		private Date geboorteDatum = null;
		private Integer idKlant = -1;
		
		public KlantBuilder(String voornaam, String achterNaam) {
			this.voornaam = voornaam;
			this.achternaam = achterNaam;
		}
		
		public KlantBuilder idKlant(Integer idKlant) {
			this.idKlant = idKlant;
			return this;
		}
		
		public KlantBuilder geboorteDatum(Date geboorteDatum) {
			this.geboorteDatum = geboorteDatum;
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

	public Date getGeboorteDatum() {
		return geboorteDatum;
	}
		
}

