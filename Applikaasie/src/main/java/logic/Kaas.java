package logic;

import java.math.BigDecimal;

public class Kaas {
	
	private final String kaasNaam;
	private final BigDecimal prijsInKg;
	private final BigDecimal vooraadInKg;
	private final Integer kaasId;
	
	private Kaas(KaasBuilder kaasBuilder) {
		this.kaasNaam = kaasBuilder.kaasNaam;
		this.prijsInKg = kaasBuilder.prijsInKg;
		this.vooraadInKg = kaasBuilder.vooraadInKg;
		this.kaasId = kaasBuilder.kaasId;
	}
	
	public static class KaasBuilder {
		
		private final String kaasNaam;
		private BigDecimal prijsInKg = new BigDecimal(0);
		private BigDecimal vooraadInKg = new BigDecimal(0);
		private Integer kaasId = -1;
		
		public KaasBuilder(String kaasNaam) {
			this.kaasNaam = kaasNaam;
		}
		
		public KaasBuilder prijsInKg(BigDecimal prijsInKg) {
			this.prijsInKg = prijsInKg;
			return this;
		}
		
		public KaasBuilder vooraadInKg(BigDecimal vooraadInKg) {
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
		return kaasNaam;
	}

	public BigDecimal getPrijsInKg() {
		return prijsInKg;
	}

	public BigDecimal getVooraadInKg() {
		return vooraadInKg;
	}

	public Integer getKaasId() {
		return kaasId;
	}
	
}
