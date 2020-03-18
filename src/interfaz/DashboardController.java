package interfaz;

import clases.Consulta;
import clases.Doctor;
import clases.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import programa.Data;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DashboardController implements Initializable  {

    @FXML
    private Label nombre_doctor;

    @FXML
    private Label info_doctor_text;

    @FXML
    private Label medicamentos_recetados;

    @FXML
    private Label pacientes_asignados;

    @FXML
    private Label consultas_pendientes;

    @FXML
    private PieChart graficoGenero;

    @FXML
    private PieChart graficoEdad;

    @FXML
    public ListView proximasConsultas;

    private int consultasPending;

    private int medicamentosRecetados;

    private int pacientesAsignados;

    private int consultasHoy;

    private int hombres;

    private int mujeres;

    private int[] edades;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombre_doctor.setText("¡HOLA " + Data.doctor.getNombre().toUpperCase() + "!");
        calcularDatos();
    }

    private void calcularDatos() {
        Doctor doc = Data.doctor;
        HashMap<String, Paciente> pacientes = doc.getPacientes();

        pacientesAsignados = pacientes.size(); //NUMERO DE PACIENTES ASIGNADOS

        //COJO LA FECHA ACTUAL
        Calendar calendario = new GregorianCalendar();
        int dia = calendario.get(Calendar.DATE);
        int mes = calendario.get(Calendar.MONTH) + 1; //LE SUMO UNO PORQUE EMPIEZA A CONTAR DESDE 0
        int any = calendario.get(Calendar.YEAR);
        String fechaActual;

        if(mes < 10) { //GUARDO EL VALOR DE LA FECHA ACTUAL
            fechaActual = dia + "-0" + mes + "-" + any;
        } else {
            fechaActual = dia + "-" + mes + "-" + any;
        }

        //COJO LA HORA ACTUAL
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minuto = calendario.get(Calendar.MINUTE);
        int horaActual = Integer.parseInt( hora + "" + minuto);

        consultasPending = 0;
        medicamentosRecetados = 0;
        consultasHoy = 0;
        hombres = 0;
        mujeres = 0;
        edades = new int[]{0, 0, 0, 0, 0};
        Paciente p;
        ArrayList<Consulta> consultas;
        int horaConsulta;

        for(Map.Entry<String, Paciente> entry : pacientes.entrySet()) { //RECORRO LA INFORMACION DEL DOCTOR
            p = entry.getValue();
            consultas = p.getConsultas();
            consultasPending = consultasPending + consultas.size(); //SUMO LAS CONSULTAS DE ESTE PACIENTE
            for(Consulta c : consultas) {
                horaConsulta = Integer.parseInt(c.getHora().replace(":", ""));
                if(c.getDia().equals(fechaActual) && horaActual < horaConsulta) { //CALCULO LAS CONSULTAS PENDIENTES QUE TIENE EL DOCTOR
                    consultasHoy++;
                }
            }
            medicamentosRecetados = medicamentosRecetados + p.getMedicinas().size(); //SUMO LAS MEDICINAS DE ESTE PACIENTE
            if(p.getGenero().equals("H")) {
                hombres++;
            } else {
                mujeres++;
            }

            calcularEdad(p.getNacimiento()); //CALCULO LA EDAD DEL PACIENTE
        }

        //ACTUALIZO LOS LABEL
        pacientes_asignados.setText(Integer.toString(pacientesAsignados));
        consultas_pendientes.setText(Integer.toString(consultasPending));
        medicamentos_recetados.setText(Integer.toString(medicamentosRecetados));
        if(consultasHoy != 1) {
            info_doctor_text.setText("Tienes " + consultasHoy + " consultas pendientes hoy.");
        } else {
            info_doctor_text.setText("Tienes " + consultasHoy + " consulta pendiente hoy.");
        }
        cargarGraficoGenero(); //CARGO EL GRAFICO DE GENERO
        cargarGraficoEdad(); //CARGO EL GRAFICO DE LAS EDADES
    }

    private void calcularEdad(String nacimiento) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse(nacimiento.replaceAll("-", "/"), fmt);
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(fechaNac, ahora);
        int edad = periodo.getYears();
        if(edad <= 20) {
            edades[0]++;
        } else if(edad >= 21 && edad <= 40) {
            edades[1]++;
        } else if(edad >= 41 && edad <=60) {
            edades[2]++;
        } else if(edad >= 61 && edad <=80) {
            edades[3]++;
        } else if(edad >= 81) {
            edades[4]++;
        }
    }

    private void cargarGraficoGenero() {
        //AÑADO LOS ELEMENTOS
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                new PieChart.Data("Hombres", hombres),
                new PieChart.Data("Mujeres", mujeres));
        graficoGenero.setData(valueList);

        //CALCULO EL PORCENTAJE Y LO AÑADO COMO TOOLTIP
        graficoGenero.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / pacientesAsignados)*100);
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });
    }

    private void cargarGraficoEdad() {
        //AÑADO LOS ELEMENTOS
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                new PieChart.Data("0-20", edades[0]),
                new PieChart.Data("21-40", edades[1]),
                new PieChart.Data("41-60", edades[2]),
                new PieChart.Data("61-80", edades[3]),
                new PieChart.Data("+80", edades[4]));
        graficoEdad.setData(valueList);

        //CALCULO EL PORCENTAJE Y LO AÑADO COMO TOOLTIP
        graficoEdad.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / pacientesAsignados)*100);
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });
    }
}
