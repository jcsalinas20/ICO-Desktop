package clases;

import conexion.MongoActions;
import interfaz.PacientesController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import programa.Data;

public class TablaConsultas {

    private TablaConsultas tabla;
    private int row, id_consulta, num_consulta;
    private String hora, dia, notasPaciente, notasDoctor;
    private CheckBox asistido;

    public TablaConsultas(int id_consulta, int num_consulta, int row, String hora, String dia, String notasPaciente, String notasDoctor, CheckBox asistido) {
        this.id_consulta = id_consulta;
        this.num_consulta = num_consulta;
        this.row = row;
        this.hora = hora;
        this.dia = dia;
        this.notasPaciente = notasPaciente;
        this.notasDoctor = notasDoctor;
        this.asistido = asistido;
        this.tabla = this;
        checkBoxListener();
    }

    public void setNotasPaciente(String notasPaciente) {
        this.notasPaciente = notasPaciente;
    }

    public void setNotasDoctor(String notasDoctor) {
        this.notasDoctor = notasDoctor;
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

    //CONFIGURO EL LISTENER DEL CHECKBOX
    private void checkBoxListener() {
        asistido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Data.doctor.getPacientes().get(PacientesController.pacienteKey).getConsultas().get(row).setAsistido(asistido.isSelected());
                new MongoActions().changeAsistido(tabla);
            }
        });
    }
}
