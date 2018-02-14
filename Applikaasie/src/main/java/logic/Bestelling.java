package logic;


import java.util.HashMap;

public class Bestelling {
	
	private final HashMap<String, Double> besteldeKazenList;
	private final double totaalPrijs;
	private final int klantId;
	
	
	private Bestelling(BestellingBuilder bestellingBuilder) {
		this.besteldeKazenList = bestellingBuilder.besteldeKazenList;
		this.totaalPrijs = bestellingBuilder.totaalPrijs;
		this.klantId = bestellingBuilder.klantId;
		
	}
	
	public static class BestellingBuilder {
		
		private HashMap<String, Double> besteldeKazenList;
		private double totaalPrijs;
		private final int klantId;
		
		public BestellingBuilder(int klantId) {
			this.klantId = klantId;
		}
		
		public BestellingBuilder besteldeKazenList(HashMap<String, Double> besteldeKazenList) {
			this.besteldeKazenList = besteldeKazenList;
			return this;
		}
		
		public BestellingBuilder totaalPrijs(double totaalPrijs) {
			this.totaalPrijs = totaalPrijs;
			return this;
		}
		
		public Bestelling build() {
			return new Bestelling(this);
		}
		
	}

	public HashMap<String, Double> getBesteldeKazenList() {
		return besteldeKazenList;
	}

	public double getTotaalPrijs() {
		return totaalPrijs;
	}

	public int getKlantId() {
		return klantId;
	}
	
}
