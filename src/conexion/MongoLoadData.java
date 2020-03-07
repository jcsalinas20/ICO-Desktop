package conexion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import clases.Consulta;
import clases.Doctor;
import clases.Medicamento;
import clases.Paciente;
import programa.Constantes;
import programa.Data;

public class MongoLoadData {
		
		@SuppressWarnings("unchecked")
		public void loadDoctor(Document doctor) {
			Doctor doc = new Doctor();
			
			//RECOJO EL NOMBRE DEL DOCTOR
			String nombreDoctor = doctor.getString(Constantes.MONGO_DOCTORES_NAME);
			doc.setNombre(nombreDoctor);

			//RECOJO LOS APELLIDOS DEL DOCTOR
			String apellidosDoctor = doctor.getString(Constantes.MONGO_DOCTORES_APELLIDOS);
			doc.setApellidos(apellidosDoctor);
			
			//RECOJO LAS CONSULTAS DEL DOCTOR
			HashMap<String, Paciente> pacienteList = new HashMap<>();
			ArrayList<Consulta> consultasDoctor = new ArrayList<>();
			Consulta consultaPosition;
			Paciente pacientePosition;
			List<Document> consultas, medicamentosPosition; 
			List<String> horaPosition;
			String nombrePaciente, apellidosPaciente, dniPaciente, hora, dia, foto, fechaNacimiento;
			ArrayList<Medicamento> medicinas;
			String nombreMedicamento, imagenMedicamento;
			int[] diasMedicamento;
			ArrayList<String> horasMedicamento;
			
			int planta, numero_sala;
			boolean asistido;
			Document pacienteDocument, diasDocument;
			
			consultas = (List<Document>) doctor.get(Constantes.MONGO_DOCTORES_CONSULTAS);
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
				foto = pacienteDocument.getString(Constantes.MONGO_PACIENTES_FOTO);
				fechaNacimiento = pacienteDocument.getString(Constantes.MONGO_PACIENTES_NACIMIENTO);
				
				
				//RECOJO SUS MEDICINAS
				medicamentosPosition = (List<Document>) pacienteDocument.get(Constantes.MONGO_PACIENTES_MEDICAMENTOS);
				medicinas = new ArrayList<>();
				diasMedicamento = new int[7];
				for(Document medicamento: medicamentosPosition) {
					nombreMedicamento = medicamento.getString(Constantes.MONGO_MEDICAMENTO_NOMBRE);
					imagenMedicamento = medicamento.getString(Constantes.MONGO_MEDICAMENTO_IMAGEN);	
					
					//CARGO LAS HORAS A LAS QUE EL PACIENTE DEBE TOMAR LOS MEDICAMENTOS
					horasMedicamento = new ArrayList<String>();
					horaPosition = (List<String>) medicamento.get(Constantes.MONGO_MEDICAMENTO_HORAS);
					for(String horaPos: horaPosition) {
						horasMedicamento.add(horaPos);
					}
					
					//CARGO LOS DIAS EN LOS QUE EL PACIENTE DEBE TOMAR EL MEDICAMENTO
					diasDocument = (Document) medicamento.get(Constantes.MONGO_MEDICAMENTO_DIAS);
					diasMedicamento[0] = diasDocument.getInteger(Constantes.MONGO_DIA_LUNES);
					diasMedicamento[1] = diasDocument.getInteger(Constantes.MONGO_DIA_MARTES);
					diasMedicamento[2] = diasDocument.getInteger(Constantes.MONGO_DIA_MIERCOLES);
					diasMedicamento[3] = diasDocument.getInteger(Constantes.MONGO_DIA_JUEVES);
					diasMedicamento[4] = diasDocument.getInteger(Constantes.MONGO_DIA_VIERNES);
					diasMedicamento[5] = diasDocument.getInteger(Constantes.MONGO_DIA_SABADO);
					diasMedicamento[6] = diasDocument.getInteger(Constantes.MONGO_DIA_DOMINGO);
					medicinas.add(new Medicamento(nombreMedicamento, imagenMedicamento, diasMedicamento, horasMedicamento));
				}
				
				pacientePosition = new Paciente(nombrePaciente, apellidosPaciente, dniPaciente, foto, fechaNacimiento, medicinas);
				pacienteList.put(nombrePaciente, pacientePosition);
				consultaPosition.setPaciente(pacientePosition);
				
				consultasDoctor.add(consultaPosition);
			}
			
			doc.setConsultas(consultasDoctor);
			
			Data.doctor = doc;
			Data.pacientes = pacienteList;
		}

}
