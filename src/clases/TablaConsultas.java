package clases;

import javafx.scene.control.CheckBox;

public class TablaConsultas {

    private String hora, dia, notasPaciente, notasDoctor;
    private CheckBox asistido;

    public TablaConsultas(String hora, String dia, String notasPaciente, String notasDoctor, CheckBox asistido) {
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

    public CheckBox getAsistido() {
        return asistido;
    }
}
