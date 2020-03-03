package conexion;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import cifrado.Md5;
import clases.Consulta;
import clases.Doctor;
import clases.Paciente;
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
		Doctor doc = new Doctor();
		
		//RECOJO EL NOMBRE DEL DOCTOR
		String nombreDoctor = doctor.getString(Constantes.MONGO_DOCTORES_NAME);
		doc.setNombre(nombreDoctor);

		//RECOJO LOS APELLIDOS DEL DOCTOR
		String apellidosDoctor = doctor.getString(Constantes.MONGO_DOCTORES_APELLIDOS);
		doc.setApellidos(apellidosDoctor);
		
		//RECOJO LAS CONSULTAS DEL DOCTOR
		
		ArrayList<Consulta> consultasDoctor = new ArrayList<>();
		Consulta consultaPosition;
		Paciente pacientePosition;
		String nombrePaciente, apellidosPaciente, dniPaciente, hora, dia;
		int planta, numero_sala;
		boolean asistido;
		Document pacienteDocument;
		
		@SuppressWarnings("unchecked")
		List<Document> consultas = (List<Document>) doctor.get(Constantes.MONGO_DOCTORES_CONSULTAS);
		for(Document consulta: consultas) {
			consultaPosition = new Consulta();
			
			//RECOJO LA HORA DE LA CONSULTA
			hora = consulta.getString(Constantes.MONGO_CONSULTAS_HORA);
			consultaPosition.setHora(hora);
			
			//RECOJO EL DIA DE LA CONSULTA
			dia = consulta.getString(Constantes.MONGO_CONSULTAS_DIA);
			consultaPosition.setDia(dia);
			
			//RECOJO LA PLANTA DE LA CONSULTA
			planta = consulta.getInteger(Constantes.MONGO_CONSULTAS_PLANTA);
			consultaPosition.setPlanta(planta);
			
			//RECOJO LA SALA DE LA CONSULTA
			numero_sala = consulta.getInteger(Constantes.MONGO_CONSULTAS_SALA);
			consultaPosition.setNumero_sala(numero_sala);
			
			//RECOJO EL BOOLEANO DE ASISTENCIA A LA CONSULTA
			asistido = consulta.getBoolean(Constantes.MONGO_CONSULTAS_ASISTENCIA);
			consultaPosition.setAsistido(asistido);
			
			//RECOJO LOS DATOS DEL PACIENTE DE LA CONSULTA
			pacienteDocument = (Document) consulta.get(Constantes.MONGO_CONSULTAS_PACIENTES);
			nombrePaciente = pacienteDocument.getString(Constantes.MONGO_PACIENTES_NOMBRE);
			apellidosPaciente = pacienteDocument.getString(Constantes.MONGO_PACIENTES_APELLIDOS);
			dniPaciente = pacienteDocument.getString(Constantes.MONGO_PACIENTES_DNI);
			pacientePosition = new Paciente(nombrePaciente, apellidosPaciente, dniPaciente);
			consultaPosition.setPaciente(pacientePosition);
			
			consultasDoctor.add(consultaPosition);
		}
		
		doc.setConsultas(consultasDoctor);
		
		Data.doctor = doc;
	}
	
}
