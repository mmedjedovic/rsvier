package logic;

public class Klant {
	
	private final String voornaam;
	private final String achternaam;
	private final String tussenvoegsel;
	private final String straatNaam;
	private final String huisnummer;
	private final String toevoegingHuisnummer;
	private final String postcode;
	private final String woonplaats;
	private final BezorgAdres bezorgAdres;
	private final int klantId;
	
	
	//private constructor
	private Klant(KlantBuilder klantBuilder) {
		this.voornaam = klantBuilder.voornaam;
		this.achternaam = klantBuilder.achternaam;
		this.tussenvoegsel = klantBuilder.tussenvoegsel;
		this.straatNaam = klantBuilder.straatNaam;
		this.huisnummer = klantBuilder.huisnummer;
		this.toevoegingHuisnummer = klantBuilder.toevoegingHuisnummer;
		this.postcode = klantBuilder.postcode;
		this.woonplaats = klantBuilder.woonplaats;
		this.bezorgAdres = klantBuilder.bezorgAdres;
		this.klantId = klantBuilder.idKlant;
	}
	
	
	public static class KlantBuilder {
		
		private final String voornaam;
		private final String achternaam;
		private String tussenvoegsel = "nvt";
		private String straatNaam = "nvt";
		private String huisnummer = "nvt";
		private String toevoegingHuisnummer = "nvt";
		private String postcode = "nvt";
		private String woonplaats = "nvt";
		private BezorgAdres bezorgAdres = null;
		private int idKlant = -1;
		
		public KlantBuilder(String voornaam, String achterNaam) {
			this.voornaam = voornaam;
			this.achternaam = achterNaam;
		}
		
		public KlantBuilder tussenvoegsel(String tussenvoegsel) {
			this.tussenvoegsel = tussenvoegsel;
			return this;
		}
		
		public KlantBuilder straatNaam(String straatNaam) {
			this.straatNaam = straatNaam;
			return this;
		}
		
		public KlantBuilder huisnummer(String huisnummer) {
			this.huisnummer = huisnummer;
			return this;
		}
		
		public KlantBuilder toevoegingHuisnummer(String toevoegingHuisnummer) {
			this.toevoegingHuisnummer = toevoegingHuisnummer;
			return this;
		}
		
		public KlantBuilder postcode(String postcode) {
			this.postcode = postcode;
			return this;
		}
		
		public KlantBuilder woonplaats(String woonplaats) {
			this.woonplaats = woonplaats;
			return this;
		}
		
		public KlantBuilder bezorgAdres(BezorgAdres adres) {
			this.bezorgAdres = adres;
			return this;
		}
		
		public KlantBuilder idKlant(int idKlant) {
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


	public String getTussenvoegsel() {
		return tussenvoegsel;
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


	public BezorgAdres getBezorgAdres() {
		return bezorgAdres;
	}


	public int getKlantId() {
		return klantId;
	}
		
}

