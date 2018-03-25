package io.data;

import io.interfaces.AdresIO;
import io.interfaces.BestellingDetailsIO;
import io.interfaces.BestellingTotaalIO;
import io.interfaces.FactoryIO;
import io.interfaces.KaasIO;
import io.interfaces.KlantIO;

public class FactoryIOImpl implements FactoryIO{
	
	private static FactoryIO instance = null;
	
	private FactoryIOImpl() {}
	
	public static FactoryIO gestInstance() {
		if(instance == null) {
			instance = new FactoryIOImpl();
		}
		return instance;
	}
	
	@Override
	public AdresIO getAdresIO() {
		return new AdresIOImpl();
	}
	
	
	@Override
	public BestellingDetailsIO getBestellingsDetailsIO() {
		return new BestellingDetailsIOImpl();
	}
	
	
	@Override
	public BestellingTotaalIO getBestllingTotaalIO() {
		return new BestellingTotaalIOImpl();
	}
	
	
	@Override
	public KaasIO getKaasIO() {
		return new KaasIOImpl();
	}
	
	
	@Override
	public KlantIO getKlantIO() {
		return new KlantIOImpl();
	}

}
