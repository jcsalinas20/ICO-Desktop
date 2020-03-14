package clases;

public class Consulta {
	
	private String hora, dia, notasPaciente, notasDoctor;
	private boolean asistido;

	public Consulta(String hora, String dia, String notasPaciente, String notasDoctor, boolean asistido) {
		this.hora = hora;
		this.dia = dia;
		this.notasPaciente = notasPaciente;
		this.notasDoctor = notasDoctor;
		this.asistido = asistido;
	}

	public String getHora() {
		return hora;
	}

	public String getDia() {
		return dia;
	}

	public String getNotasPaciente() {
		return notasPaciente;
	}

	public String getNotasDoctor() {
		return notasDoctor;
	}

	public boolean isAsistido() {
		return asistido;
	}
}