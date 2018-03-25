package io.datamongo;

import io.interfaces.AdresIO;
import io.interfaces.BestellingDetailsIO;
import io.interfaces.BestellingTotaalIO;
import io.interfaces.FactoryIO;
import io.interfaces.KaasIO;
import io.interfaces.KlantIO;
import util.ExceptionIOMongo;

public class FactoryIOMongoImpl implements FactoryIO {
	
	private static FactoryIO factoryIo = null;
	
	private FactoryIOMongoImpl() {}
	
	public static FactoryIO getInstance() {
		if(factoryIo == null) {
			factoryIo = new FactoryIOMongoImpl();
		}
		return factoryIo;
	}
	
	
	@Override
	public AdresIO getAdresIO() {
		return (AdresIO) new AdresIOMongoImpl();
	}
	
	@Override
	public BestellingDetailsIO getBestellingsDetailsIO() {
		return (BestellingDetailsIO) new BestellingDetailsIOMongoImpl();
	}
	
	@Override
	public BestellingTotaalIO getBestllingTotaalIO() {
		return (BestellingTotaalIO) new BestellingDetailsIOMongoImpl();
	}
	
	@Override
	public KaasIO getKaasIO() {
		return (KaasIO) new KaasIOMnogoImpl();
	}

	@Override
	public KlantIO getKlantIO() {
		KlantIO klantIo = null;
		try {
			klantIo = new KlantIOMongoImpl();
		} catch (ExceptionIOMongo e) {
			e.printStackTrace();
		}
		return klantIo;
	}

}
