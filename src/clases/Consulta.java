package clases;

public class Consulta {
	
	private Paciente paciente;
	private String hora, dia, notas;
	private int planta, numero_sala;
	private boolean asistido;
		
	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public void setPlanta(int planta) {
		this.planta = planta;
	}

	public void setNumero_sala(int numero_sala) {
		this.numero_sala = numero_sala;
	}

	public void setAsistido(boolean asistido) {
		this.asistido = asistido;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public String getHora() {
		return hora;
	}

	public String getDia() {
		return dia;
	}

	public int getPlanta() {
		return planta;
	}

	public int getNumero_sala() {
		return numero_sala;
	}

	public boolean isAsistido() {
		return asistido;
	}
	
}