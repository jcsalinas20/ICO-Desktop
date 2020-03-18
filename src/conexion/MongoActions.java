package conexion;

import clases.TablaConsultas;
import clases.TablaMedicamentos;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import cifrado.Md5;
import programa.Constantes;
import programa.Data;

public class MongoActions {

	private MongoDatabase db;
	private MongoCollection<Document> doctores;
	private Document doctor;

	public MongoActions() {
		this.db = Data.mongoDB.getDB();
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
			password = new Md5(password).encrypt(); //ENCRIPTO LA CONTRASE�A CON MD5

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
		//CARGO LAS COLECCIONES NECESARIAS
		MongoCollection<Document> hospital = db.getCollection(Constantes.MONGO_HOSPITAL_COLLECTION);
		MongoCollection<Document> consultas = db.getCollection(Constantes.MONGO_CONSULTAS_COLLECTION);
		MongoCollection<Document> pacientes = db.getCollection(Constantes.MONGO_PACIENTES_COLLECTION);
		MongoCollection<Document> medicamentos = db.getCollection(Constantes.MONGO_MEDICAMENTOS_COLLECTION);
		MongoCollection<Document> historial = db.getCollection(Constantes.MONGO_HISTORIAL_COLLECTION);

		//LLAMO A LAS FUNCIONES PARA CARGAR DATOS
		MongoLoadData loadFunctions = new MongoLoadData();
		loadFunctions.loadDoctor(doctor, hospital);
		loadFunctions.loadConsultas(consultas, pacientes, historial);
		loadFunctions.loadMedicamentos(medicamentos);
	}

	public void removeMedicamento(int id_paciente, int id_medicamento) {
		new MongoMedicamentos(getcollectionPacientes()).eliminarMedicamento(id_paciente, id_medicamento);
	}

	public void editHorasMedicamento(int id_paciente, int id_medicamento, String horas) {
		new MongoMedicamentos(getcollectionPacientes()).editarHorasMedicamento(id_paciente, id_medicamento, horas);
	}

	public void addMedicamento(TablaMedicamentos nuevoMedicamento) {
		new MongoMedicamentos(getcollectionPacientes()).addMedicamento(nuevoMedicamento);
	}

	public void editMedicamentoDay(TablaMedicamentos data) {
		new MongoMedicamentos(getcollectionPacientes()).editDayMedicamento(data);
	}

	private MongoCollection<Document> getcollectionPacientes() {
		MongoCollection<Document> pacientes = db.getCollection(Constantes.MONGO_PACIENTES_COLLECTION);
		return pacientes;
	}

	private MongoCollection<Document> getCollectionConsultas() {
		MongoCollection<Document> consultas = db.getCollection(Constantes.MONGO_CONSULTAS_COLLECTION);
		return consultas;
	}

	public void changeAsistido(TablaConsultas data) {
		MongoCollection<Document> historial = db.getCollection(Constantes.MONGO_HISTORIAL_COLLECTION);
		new MongoConsultas(getCollectionConsultas(), data).changeAsistido(historial);
	}

	public void changeNotasPaciente(TablaConsultas data) {
		new MongoConsultas(getCollectionConsultas(), data).createDocument();
	}

	public void changeNotasDoctor(TablaConsultas data) {
		new MongoConsultas(getCollectionConsultas(), data).createDocument();
	}
}
