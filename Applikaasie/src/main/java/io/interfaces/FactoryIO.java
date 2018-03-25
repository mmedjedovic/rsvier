package io.interfaces;

public interface FactoryIO {
	
	public AdresIO getAdresIO() throws Exception;
	
	public BestellingDetailsIO getBestellingsDetailsIO() throws Exception;
	
	public BestellingTotaalIO getBestllingTotaalIO() throws Exception;
	
	public KaasIO getKaasIO() throws Exception;
	
	public KlantIO getKlantIO() throws Exception;
	
}
