package logic;

public class Kaas {
	
	private final String naam;
	private final Double prijsInKg;
	private final Double vooraadInKg;
	private final Integer kaasId;
	
	private Kaas(KaasBuilder kaasBuilder) {
		this.naam = kaasBuilder.naam;
		this.prijsInKg = kaasBuilder.prijsInKg;
		this.vooraadInKg = kaasBuilder.vooraadInKg;
		this.kaasId = kaasBuilder.kaasId;
	}
	
	public static class KaasBuilder {
		
		private final String naam;
		private Double prijsInKg = 0.0;
		private Double vooraadInKg = 0.0;
		private Integer kaasId = -1;
		
		public KaasBuilder(String naam) {
			this.naam = naam;
		}
		
		public KaasBuilder prijsInKg(Double prijsInKg) {
			this.prijsInKg = prijsInKg;
			return this;
		}
		
		public KaasBuilder vooraadInKg(Double vooraadInKg) {
			this.vooraadInKg = vooraadInKg;
			return this;
		}
		
		public KaasBuilder kaasId(Integer kaasId) {
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

	public Double getPrijsInKg() {
		return prijsInKg;
	}

	public Double getVooraadInKg() {
		return vooraadInKg;
	}

	public Integer getKaasId() {
		return kaasId;
	}
	
}
