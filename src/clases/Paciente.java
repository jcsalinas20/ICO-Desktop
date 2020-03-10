package clases;

import java.util.ArrayList;

public class Paciente {
	
	private String nombre, apellidos, dni, foto, nacimiento, notas;
	private ArrayList<Medicamento> medicinas;
	
	public Paciente(String nombre, String apellidos, String dni, String foto, String nacimiento, ArrayList<Medicamento> medicinas, String notas) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.foto = foto;
		this.nacimiento = nacimiento;
		this.notas = notas;
	}
	
	public String getNotas() {
		return this.notas;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public String getDni() {
		return this.dni;
	}
	
	public String getFoto() {
		return this.foto;
	}
	
	public String getNacimiento() {
		return this.nacimiento;
	}
	
	public ArrayList<Medicamento> getMedicamentos() {
		return this.medicinas;
	}
	
}
