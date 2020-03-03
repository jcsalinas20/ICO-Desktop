package clases;

import java.util.ArrayList;

public class Doctor {
	
	String nombre, apellidos;
	private ArrayList<Consulta> consultas;
	
	public Doctor() {
		
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setConsultas(ArrayList<Consulta> consultas) {
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
