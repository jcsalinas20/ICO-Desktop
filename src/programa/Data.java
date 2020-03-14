package programa;

import java.util.HashMap;

import clases.Medicamentos;
import com.mongodb.client.MongoDatabase;

import clases.Doctor;
import clases.Paciente;
import javafx.stage.Stage;

public class Data {
	
	public static MongoDatabase mongoDB;

	public static Doctor doctor;
	public static HashMap<Integer, Medicamentos> medicamentos;
	public static String direccionHospital;

	public static Stage interfaz;
}
