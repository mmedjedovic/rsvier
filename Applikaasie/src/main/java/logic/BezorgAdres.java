package logic;

public class BezorgAdres {
	
	private final String voornaam;
	private final String achternaam;
	private final String straatNaam;
	private final String huisnummer;
	private final String toevoegingHuisnummer;
	private final String postcode;
	private final String woonplaats;
	private final int klantId;
	
	private BezorgAdres(BezorgAdresBuilder bezorgAdresBuilder) {
		this.voornaam = bezorgAdresBuilder.voornaam;
		this.achternaam = bezorgAdresBuilder.achternaam;
		this.straatNaam = bezorgAdresBuilder.straatNaam;
		this.huisnummer = bezorgAdresBuilder.huisnummer;
		this.toevoegingHuisnummer = bezorgAdresBuilder.toevoegingHuisnummer;
		this.postcode = bezorgAdresBuilder.postcode;
		this.woonplaats = bezorgAdresBuilder.woonplaats;
		this.klantId = bezorgAdresBuilder.klantId;
	}
	
	public static class BezorgAdresBuilder {
		
		private final int klantId;
		private final String voornaam;
		private final String achternaam;
		private String straatNaam = "nvt";
		private String huisnummer = "nvt";
		private String toevoegingHuisnummer = "nvt";
		private String postcode = "nvt";
		private String woonplaats = "nvt";
		
		public BezorgAdresBuilder(int klantId, String voornaam, String achternaam) {
			this.klantId = klantId;
			this.voornaam = voornaam;
			this.achternaam = achternaam;
		}
		
		public BezorgAdresBuilder straatNaam(String straatNaam) {
			this.straatNaam = straatNaam;
			return this;
		}
		
		public BezorgAdresBuilder huisnummer(String huisnumer) {
			this.huisnummer = huisnumer;
			return this;
		}
		
		public BezorgAdresBuilder toevoegingHuisnummer(String toevoegingHuisnummer) {
			this.toevoegingHuisnummer = toevoegingHuisnummer;
			return this;
		}
		
		public BezorgAdresBuilder postcode(String postcode) {
			this.postcode = postcode;
			return this;
		}
		
		public BezorgAdresBuilder woonplaats(String woonplaats) {
			this.woonplaats = woonplaats;
			return this;
		}
		
		public BezorgAdres build() {
			return new BezorgAdres(this);
		}
		
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getAchternaam() {
		return achternaam;
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

	public int getKlantId() {
		return klantId;
	}
	
}
