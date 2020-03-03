package clases;

public class Consulta {
	
	private Paciente paciente;
	private String hora, dia;
	private int planta, numero_sala;
	private boolean asistido;
	
	public Consulta(Paciente paciente, String hora, String dia, int planta, int numero_sala, boolean asistido) {
		super();
		this.paciente = paciente;
		this.hora = hora;
		this.dia = dia;
		this.planta = planta;
		this.numero_sala = numero_sala;
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