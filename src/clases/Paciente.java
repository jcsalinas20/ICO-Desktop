package clases;

import java.util.ArrayList;

public class Paciente {
	
	private String nombre, apellidos, dni, foto, nacimiento;
	private ArrayList<Consulta> consultas;
	private ArrayList<MedicamentoPaciente> medicinas;

	public Paciente(String nombre, String apellidos, String dni, String foto, String nacimiento, ArrayList<Consulta> consultas, ArrayList<MedicamentoPaciente> medicinas) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.foto = foto;
		this.nacimiento = nacimiento;
		this.consultas = consultas;
		this.medicinas = medicinas;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getDni() {
		return dni;
	}

	public String getFoto() {
		return foto;
	}

	public String getNacimiento() {
		return nacimiento;
	}

	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}

	public ArrayList<MedicamentoPaciente> getMedicinas() {
		return medicinas;
	}
}
