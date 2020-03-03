package clases;

import java.util.ArrayList;

public class Doctor {
	
	String nombre, apellidos;
	private ArrayList<Consulta> consultas;
	
	public Doctor(String nombre, String apellidos, ArrayList<Consulta> consultas) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.consultas = consultas;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}	
}
