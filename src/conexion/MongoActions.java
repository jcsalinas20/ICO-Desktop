package conexion;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import cifrado.Md5;
import programa.Constantes;
import programa.Data;

public class MongoActions {

	/*
	 * FORMAS DE RECORRER DB
	 * 
	 * for (Document doc : fi) { System.out.println(doc.toJson());
	 * 
	 * } ----------------------------------------------------------
	 * MongoCursor<Document> cursor = fi.iterator(); try { while(cursor.hasNext()) {
	 * System.out.println(cursor.next().toJson()); } } finally { cursor.close(); }
	 */

	private MongoDatabase db;
	private MongoCollection<Document> doctores;
	private Document doctor;

	public MongoActions() {
		this.db = Data.mongoDB;
		doctores = db.getCollection(Constantes.MONGO_DOCTOR_COLLECTION);
	}

	// COMPRUEBA SI LOS DATOS DE LOGIN SON CORRECTOS
	public boolean checkLogin(String usuario, String password) {
		
		// BUSCO UN DOCUMENTO QUE CORRESPONDA CON EL NOMBRE DE USUARIO INTRODUCIDO
		Document buscar = new Document(Constantes.MONGO_DOCTORES_USERNAME, usuario);
		FindIterable<Document> fi = doctores.find(buscar);

		try {
			doctor = fi.first();
			String dbPassword = doctor.getString(Constantes.MONGO_DOCTORES_PASSWORD); //COJO LA PASSWORD EXISTENTE EN LA DB
			password = new Md5(password).encrypt(); //ENCRIPTO LA CONTRASEÑA CON MD5
			
			//COMPRUEBO QUE LA PASSWORD COINCIDA
			if(password.equals(dbPassword)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public void loadDoctor() {
		new MongoLoadData().loadDoctor(doctor);
	}
	
}
