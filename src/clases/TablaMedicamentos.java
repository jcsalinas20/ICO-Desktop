package clases;

import javafx.scene.control.CheckBox;

public class TablaMedicamentos {

    private String medicamento, horas;
    private CheckBox lunes, martes, miercoles, jueves, viernes, sabado, domingo;

    public TablaMedicamentos(String medicamento, String horas, CheckBox lunes, CheckBox martes, CheckBox miercoles, CheckBox jueves, CheckBox viernes, CheckBox sabado, CheckBox domingo) {
        this.medicamento = medicamento;
        this.horas = horas;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public String getHoras() {
        return horas;
    }

    public CheckBox getLunes() {
        return lunes;
    }

    public CheckBox getMartes() {
        return martes;
    }

    public CheckBox getMiercoles() {
        return miercoles;
    }

    public CheckBox getJueves() {
        return jueves;
    }

    public CheckBox getViernes() {
        return viernes;
    }

    public CheckBox getSabado() {
        return sabado;
    }

    public CheckBox getDomingo() {
        return domingo;
    }
}
