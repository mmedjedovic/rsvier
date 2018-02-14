package logic;

public class Kaas {
	
	private final String naam;
	private final double prijsInKg;
	private final double vooraadInKg;
	private final int kaasId;
	
	private Kaas(KaasBuilder kaasBuilder) {
		this.naam = kaasBuilder.naam;
		this.prijsInKg = kaasBuilder.prijsInKg;
		this.vooraadInKg = kaasBuilder.vooraadInKg;
		this.kaasId = kaasBuilder.kaasId;
	}
	
	public static class KaasBuilder {
		
		private final String naam;
		private double prijsInKg = 0.0;
		private double vooraadInKg = 0.0;
		private int kaasId = -1;
		
		public KaasBuilder(String naam) {
			this.naam = naam;
		}
		
		public KaasBuilder prijsInKg(double prijsInKg) {
			this.prijsInKg = prijsInKg;
			return this;
		}
		
		public KaasBuilder vooraadInKg(double vooraadInKg) {
			this.vooraadInKg = vooraadInKg;
			return this;
		}
		
		public KaasBuilder kaasId(int kaasId) {
			this.kaasId = kaasId;
			return this;
		}
		
		public Kaas build() {
			return new Kaas(this);
		}
	}

	public String getNaam() {
		return naam;
	}

	public double getPrijsInKg() {
		return prijsInKg;
	}

	public double getVooraadInKg() {
		return vooraadInKg;
	}

	public int getKaasId() {
		return kaasId;
	}
	
}
