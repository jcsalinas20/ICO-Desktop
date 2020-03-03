package conexion;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import programa.Constantes;
import programa.CrearAlertas;
import programa.Data;

public class MongoConnection {

	private MongoClient mongoClient;

	//SE CONECTA A LA BASE DE DATOS
	public MongoConnection() {
		try {
			MongoClientURI uri = new MongoClientURI(Constantes.MONGO_CONNECTION_STRING);
			mongoClient = new MongoClient(uri);
			Data.mongoDB = mongoClient.getDatabase(Constantes.MONGO_DATABASE_NAME);
		} catch (Exception e) {
			CrearAlertas.mongoConnectionError();
		}
	}

	public void close() {
		mongoClient.close();
	}
}
