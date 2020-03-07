package clases;

import java.util.ArrayList;

public class Medicamento {
	
	String nombre, imagen;
	int[] dias;
	ArrayList<String> horas;
	
	public Medicamento(String nombre, String imagen, int[] dias, ArrayList<String> horas) {
		super();
		this.nombre = nombre;
		this.dias = dias;
		this.horas = horas;
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int[] getDias() {
		return this.dias;
	}

	public ArrayList<String> getHoras() {
		return this.horas;
	}
	
	public String getImagen() {
		return this.imagen;
	}
	
}
