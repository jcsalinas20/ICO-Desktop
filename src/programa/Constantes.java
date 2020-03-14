package programa;

public class Constantes {
	
	//DATOS DE CONEXION PARA MONGO DB
	public static String MONGO_CONNECTION_STRING = "mongodb+srv://admin:admin@clustersalinas-hvex2.mongodb.net/DB_ICO?retryWrites=true&w=majority\r\n";
	public static String MONGO_DATABASE_NAME = "DB_ICO";
	
	//COLECCIONES DE LA BASE DE DATOS
	public static String MONGO_DOCTOR_COLLECTION = "Doctores";
	public static String MONGO_HOSPITAL_COLLECTION = "Hospitales";
	public static String MONGO_CONSULTAS_COLLECTION = "Consultas";
	public static String MONGO_PACIENTES_COLLECTION = "Pacientes";
	public static String MONGO_MEDICAMENTOS_COLLECTION = "Medicamentos";
	
	//CAMPOS DE LA COLLECTION DE DOCTORES
	public static String MONGO_DOCTORES_USERNAME = "username";
	public static String MONGO_DOCTORES_PASSWORD = "password";
	public static String MONGO_DOCTORES_ID = "id_doctor";
	public static String MONGO_DOCTORES_NAME = "nombre";
	public static String MONGO_DOCTORES_APELLIDOS = "apellidos";
	public static String MONGO_DOCTORES_PLANTA = "planta";
	public static String MONGO_DOCTORES_SALA = "sala";
	public static String MONGO_DOCTORES_HOSPITAL = "id_hospital";

	//CAMPOS DE LA COLLECTION HOSPITAL
	public static String MONGO_HOSPITAL_ID = "id_hospital";
	public static String MONGO_HOSPITAL_DIRECCION = "direccion";

	//CAMPOS DE LA COLLECTION DE MEDICAMENTOS
	public static String MONGO_MEDICAMENTOS_ID = "id";
	public static String MONGO_MEDICAMENTOS_NOMBRE = "nombre";
	public static String MONGO_MEDICAMENTOS_IMAGEN = "imagen";

	//CAMPOS DE LA COLLECTION DE CONSULTAS
	public static String MONGO_CONSULTAS_DOCTORID = "id_doctor";
	public static String MONGO_CONSULTAS_PACIENTEID = "id_paciente";
	public static String MONGO_CONSULTAS_CONSULTASARRAY = "consultas";

	//CAMPOS DEL ARRAY DE CONSULTAS
	public static String MONGO_CONSULTA_HORA = "hora";
	public static String MONGO_CONSULTA_DIA = "dia";
	public static String MONGO_CONSULTA_ASISTENCIA = "asistido";
	public static String MONGO_CONSULTA_NOTASPACIENTE = "notas";
	public static String MONGO_CONSULTA_NOTASDOCTOR = "notas_doc";

	//CAMPOS DEL ARRAY PACIENTES PERTENECIENTE AL ARRAY CONSULTAS EN LA COLLECTION DOCTORES
	public static String MONGO_PACIENTES_ID = "id_paciente";
	public static String MONGO_PACIENTES_NOMBRE = "nombre";
	public static String MONGO_PACIENTES_APELLIDOS = "apellidos";
	public static String MONGO_PACIENTES_DNI = "dni";
	public static String MONGO_PACIENTES_NACIMIENTO = "fecha_nacimiento";
	public static String MONGO_PACIENTES_FOTO = "foto";
	public static String MONGO_PACIENTES_MEDICAMENTOS = "medicamentos";

	//CAMPOS DEL ARRAY MEDICAMENTOS PERTENECIENTE A PACIENTES
	public static String MONGO_MEDICAMENTO_ID = "id";
	public static String MONGO_MEDICAMENTO_DIAS = "dias";
	public static String MONGO_MEDICAMENTO_HORAS = "hora";
	
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
