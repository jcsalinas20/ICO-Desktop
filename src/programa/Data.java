package programa;

import java.util.HashMap;

import clases.Medicamentos;
import com.mongodb.client.MongoDatabase;

import clases.Doctor;
import clases.Paciente;
import conexion.MongoConnection;
import javafx.stage.Stage;

public class Data {
	
	public static MongoConnection mongoDB;

	public static Doctor doctor;
	public static HashMap<Integer, Medicamentos> medicamentos;
	public static String direccionHospital;

	public static Stage interfaz;
}
