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
	
	//TEXTOS DE ERROR EN LA BASE DE DATOS
	public static String MONGO_ERROR_CONNECTION = "No se ha podido establecer conexión con la base de datos.";
	
}
