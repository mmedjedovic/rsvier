package logic;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class Bestelling {
	
	private final HashMap<Kaas, BigDecimal> besteldeKazenList;
	private final BigDecimal totaalPrijs;
	private final Klant klant;
	private final Date bestellingDate;
	private final Status status;
	
	public enum Status {OPEN, GESLOTEN}
	
	private Bestelling(BestellingBuilder bestellingBuilder) {
		this.besteldeKazenList = bestellingBuilder.besteldeKazenList;
		this.totaalPrijs = bestellingBuilder.totaalPrijs;
		this.klant = bestellingBuilder.klant;
		this.bestellingDate = bestellingBuilder.bestellingDate;
		this.status = bestellingBuilder.status;
	}
	
	public static class BestellingBuilder {
		
		private final Klant klant;
		private HashMap<Kaas, BigDecimal> besteldeKazenList;
		private BigDecimal totaalPrijs;
		private Date bestellingDate;
		private Status status;
		
		
		public BestellingBuilder(Klant klant) {
			this.klant = klant;
		}
		
		public BestellingBuilder besteldeKazenList(HashMap<Kaas, BigDecimal> besteldeKazenList) {
			this.besteldeKazenList = besteldeKazenList;
			return this;
		}
		
		public BestellingBuilder totaalPrijs(BigDecimal totaalPrijs) {
			this.totaalPrijs = totaalPrijs;
			return this;
		}
		
		public BestellingBuilder bestellingDate(Date bestellingDate) {
			this.bestellingDate = bestellingDate;
			return this;
		}
		
		public BestellingBuilder status(Status status) {
			this.status = status;
			return this;
		}
		
		public Bestelling build() {
			return new Bestelling(this);
		}
		
	}

	public HashMap<Kaas, BigDecimal> getBesteldeKazenList() {
		return besteldeKazenList;
	}

	public BigDecimal getTotaalPrijs() {
		return totaalPrijs;
	}

	public Klant getKlant() {
		return klant;
	}

	public Date getBestellingDate() {
		return bestellingDate;
	}

	public Status getStatus() {
		return status;
	}
	
}
