package io.datamongo;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import io.interfaces.AdresIO;
import model.Adres;
import util.ExceptionIO;
import util.ExceptionIOMongo;

public class AdresIOMongoImpl implements AdresIO{
	
	DB db = null;
	DBCollection collection = null;
	
	public AdresIOMongoImpl() throws ExceptionIOMongo {
		db = ConnectorMongo.getInstance().getDatabase();
		collection = db.getCollection("klant");
	}
	
	@Override
	public void maakNieuweAdres(Adres adres) throws ExceptionIO {
		// impementatie niet nodig	
	}

	@Override
	public Adres getAdres(Integer klantId) throws ExceptionIO {
		DBCursor cursor = collection.find(new BasicDBObject("klantId", klantId));
		DBObject klantdb = cursor.next();
		DBObject adresdb = (DBObject) klantdb.get("adres");
		Adres adres = new Adres.AdresBuilder(klantId).straatNaam((String) adresdb.get("straatnaam"))
										.huisnummer((String) adresdb.get("huisnummer"))
										.toevoegingHuisnummer((String) adresdb.get("toevoegingsnummer"))
										.postcode((String) adresdb.get("postcode"))
										.woonplaats((String) adresdb.get("woonplaats")).build();									
		return adres;	
	}
	
	
	
}
