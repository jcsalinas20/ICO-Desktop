package programa;

import java.util.HashMap;

import com.mongodb.client.MongoDatabase;

import clases.Doctor;
import clases.Paciente;
import javafx.stage.Stage;

public class Data {
	
	public static MongoDatabase mongoDB;
	public static Doctor doctor;
	public static HashMap<String, Paciente> pacientes;
	public static Stage interfaz;
}
