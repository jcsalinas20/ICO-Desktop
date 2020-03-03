package programa;

public class Constantes {
	
	//DATOS DE CONEXION PARA MONGO DB
	public static String MONGO_CONNECTION_STRING = "mongodb+srv://admin:admin@clustersalinas-hvex2.mongodb.net/test?retryWrites=true&w=majority\r\n";
	public static String MONGO_DATABASE_NAME = "DB_ICO";
	
	//COLECCIONES DE LA BASE DE DATOS
	public static String MONGO_DOCTOR_COLLECTION = "Doctores";
	
	//CAMPOS DE LA COLLECTION DE DOCTORES
	public static String MONGO_DOCTORES_USERNAME = "username";
	public static String MONGO_DOCTORES_PASSWORD = "password";
	public static String MONGO_DOCTORES_NAME = "nombre";
	public static String MONGO_DOCTORES_APELLIDOS = "apellidos";
	public static String MONGO_DOCTORES_CONSULTAS = "consultas";
	
	//CAMPOS DEL ARRAY CONSULTAS DE LA COLLECTION DE DOCTORES
	public static String MONGO_CONSULTAS_HORA = "hora";
	public static String MONGO_CONSULTAS_DIA = "dia";
	public static String MONGO_CONSULTAS_PLANTA = "planta";
	public static String MONGO_CONSULTAS_SALA = "numero_sala";
	public static String MONGO_CONSULTAS_ASISTENCIA = "asistido";
	public static String MONGO_CONSULTAS_PACIENTES = "paciente";

	//CAMPOS DEL ARRAY PACIENTES PERTENECIENTE AL ARRAY CONSULTAS EN LA COLLECTION DOCTORES
	public static String MONGO_PACIENTES_NOMBRE = "nombre";
	public static String MONGO_PACIENTES_APELLIDOS = "apellidos";
	public static String MONGO_PACIENTES_DNI = "dni";
	
	//TEXTOS DE ERROR EN LA BASE DE DATOS
	public static String MONGO_ERROR_CONNECTION = "No se ha podido establecer conexión con la base de datos.";
	
}
