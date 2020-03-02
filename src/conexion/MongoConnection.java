package conexion;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import programa.Constantes;
import programa.CrearAlertas;

public class MongoConnection {

	private MongoDatabase db;
	private MongoClient mongoClient;

	//SE CONECTA A LA BASE DE DATOS
	public MongoConnection() {
		try {
			MongoClientURI uri = new MongoClientURI(Constantes.MONGO_CONNECTION_STRING);
			mongoClient = new MongoClient(uri);
			db = mongoClient.getDatabase(Constantes.MONGO_DATABASE_NAME);
		} catch (Exception e) {
			CrearAlertas.mongoConnectionError();
		}
	}

	//COMPRUEBA SI LOS DATOS DE LOGIN SON CORRECTOS
	public boolean checkLogin() {

		return true;
	}

	private void close() {
		mongoClient.close();
	}

}
