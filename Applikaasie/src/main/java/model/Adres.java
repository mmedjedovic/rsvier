package model;

public class Adres {
	
	private final Klant klant;
	private final String straatNaam;
	private final String huisnummer;
	private final String toevoegingHuisnummer;
	private final String postcode;
	private final String woonplaats;
	
	
	private Adres(AdresBuilder adresBuilder) {
		this.straatNaam = adresBuilder.straatNaam;
		this.huisnummer = adresBuilder.huisnummer;
		this.toevoegingHuisnummer = adresBuilder.toevoegingHuisnummer;
		this.postcode = adresBuilder.postcode;
		this.woonplaats = adresBuilder.woonplaats;
		this.klant = adresBuilder.klant;
	}
	
	public static class AdresBuilder {
		
		private final Klant klant;
		private String straatNaam = "nvt";
		private String huisnummer = "nvt";
		private String toevoegingHuisnummer = "nvt";
		private String postcode = "nvt";
		private String woonplaats = "nvt";
		
		public AdresBuilder(Klant klant) {
			this.klant = klant;
		}
		
		public AdresBuilder straatNaam(String straatNaam) {
			this.straatNaam = straatNaam;
			return this;
		}
		
		public AdresBuilder huisnummer(String huisnumer) {
			this.huisnummer = huisnumer;
			return this;
		}
		
		public AdresBuilder toevoegingHuisnummer(String toevoegingHuisnummer) {
			this.toevoegingHuisnummer = toevoegingHuisnummer;
			return this;
		}
		
		public AdresBuilder postcode(String postcode) {
			this.postcode = postcode;
			return this;
		}
		
		public AdresBuilder woonplaats(String woonplaats) {
			this.woonplaats = woonplaats;
			return this;
		}
		
		public Adres build() {
			return new Adres(this);
		}
		
	}
	
	
	public Klant getKlant() {
		return klant;
	}
	
	public String getStraatNaam() {
		return straatNaam;
	}

	public String getHuisnummer() {
		return huisnummer;
	}

	public String getToevoegingHuisnummer() {
		return toevoegingHuisnummer;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getWoonplaats() {
		return woonplaats;
	}


}
