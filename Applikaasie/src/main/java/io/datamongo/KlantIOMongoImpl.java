package io.datamongo;



import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import io.data.Connector;
import io.interfaces.KlantIO;
import model.Adres;
import model.Klant;
import util.ExceptionIO;
import util.ExceptionIOMongo;

public class KlantIOMongoImpl implements KlantIO{
	
	DB db = null;
	DBCollection klantTable = null;
	
	public KlantIOMongoImpl() throws ExceptionIOMongo {
		this.db = ConnectorMongo.getInstance().getDatabase();
		this.klantTable = db.getCollection("klant");
	}	
	
	@Override
	public Integer maakNieuweKlant(Klant klant, Adres adres) {
		BasicDBObject document = new BasicDBObject();
		document.put("voornaam", klant.getVoornaam());
		document.put("achternaam", klant.getAchternaam());
		document.put("adres", new BasicDBObject("straatnaam", adres.getStraatNaam()).
												append("huisnummer", adres.getHuisnummer()).
												append("toevoegingsnummer", adres.getToevoegingHuisnummer()).
												append("postcode", adres.getPostcode()).
												append("woonplaats", adres.getWoonplaats()));
		klantTable.insert(document);
		return 1;
	}
	
	@Override
	public Klant getKlant(Integer klantId) throws ExceptionIO {
		return null;
	}
	
	@Override
	public void deleteKlant(Integer klantId) throws ExceptionIO {
		klantTable.remove(new BasicDBObject("_id", klantId));
	}
	
	@Override
	public ArrayList<Klant> getKlanten() throws ExceptionIO {
		ArrayList<Klant> arrayListKlant = new ArrayList<Klant>();
		DBCursor cursor  = klantTable.find();
		while(cursor.hasNext()) {
			BasicDBObject document = (BasicDBObject) cursor.next();
			Klant klant = new Klant.KlantBuilder(document.getString("voornaam"), document.getString("achternaam"))
																				.idKlant(document.getInt("_id")).build();
			arrayListKlant.add(klant);
		}
		return arrayListKlant;
	}
	
}
