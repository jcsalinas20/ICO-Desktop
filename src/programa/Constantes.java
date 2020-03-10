package programa;

public class Constantes {
	
	//DATOS DE CONEXION PARA MONGO DB
	public static String MONGO_CONNECTION_STRING = "mongodb+srv://admin:admin@clustersalinas-hvex2.mongodb.net/DB_ICO?retryWrites=true&w=majority\r\n";
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
	public static String MONGO_NOTAS_DOCTOR = "notas";

	//CAMPOS DEL ARRAY PACIENTES PERTENECIENTE AL ARRAY CONSULTAS EN LA COLLECTION DOCTORES
	public static String MONGO_PACIENTES_NOMBRE = "nombre";
	public static String MONGO_PACIENTES_APELLIDOS = "apellidos";
	public static String MONGO_PACIENTES_DNI = "dni";
	public static String MONGO_PACIENTES_NACIMIENTO = "fecha_nacimiento";
	public static String MONGO_PACIENTES_FOTO = "foto";
	public static String MONGO_PACIENTES_MEDICAMENTOS = "medicamentos";
	public static String MONGO_NOTAS_PACIENTE = "notas_paciente";
	
	//CAMPOS DEL ARRAY MEDICAMENTOS PERTENECIENTE AL ARRAY PACIENTES
	public static String MONGO_MEDICAMENTO_NOMBRE = "nombre";
	public static String MONGO_MEDICAMENTO_DIAS = "dias";
	public static String MONGO_MEDICAMENTO_HORAS = "hora";
	public static String MONGO_MEDICAMENTO_IMAGEN = "imagen";
	
	//CAMPOS DEL OBJETO DIAS
	public static String MONGO_DIA_LUNES = "lunes";
	public static String MONGO_DIA_MARTES = "martes";
	public static String MONGO_DIA_MIERCOLES = "miercoles";
	public static String MONGO_DIA_JUEVES = "jueves";
	public static String MONGO_DIA_VIERNES = "viernes";
	public static String MONGO_DIA_SABADO = "sabado";
	public static String MONGO_DIA_DOMINGO = "domingo";
	
	//TEXTOS DE ERROR EN LA BASE DE DATOS
	public static String MONGO_ERROR_CONNECTION = "No se ha podido establecer conexión con la base de datos.";

	//NOMBRE DE LAS ESCENAS
	public static String DASHBOARD_SCENE = "Dashboard";
	public static String SEARCH_SCENE = "Pacientes";
	public static String CONSULTAS_SCENE = "Consultas";
}
