package interfaz;

import clases.Consulta;
import clases.DashboardList;
import clases.Doctor;
import clases.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import programa.Constantes;
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
    private ListView<AnchorPane> proximasConsultas;

    @FXML
    private Label siguienteConsulta;

    @FXML
    private Button atenderConsulta;

    private int consultasPending;

    private int medicamentosRecetados;

    private int pacientesAsignados;

    private int consultasHoy;

    private int hombres;

    private int mujeres;

    private int[] edades;

    private Map<Integer, DashboardList> consultasList;

    private Paciente pacienteAtender;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombre_doctor.setText("¡HOLA " + Data.doctor.getNombre().toUpperCase() + "!");
        calcularDatos();
        cargarListaConsultas();
    }

    private void cargarListaConsultas() {
        //CREO LOS ANCHOR PANE QUE AÑADIRE A LA LISTA
        ArrayList<AnchorPane> anchorList = new ArrayList<>();
        ArrayList<DashboardList> elementosListView = new ArrayList<>();
        AnchorPane posicion;
        Label hora, paciente;
        int contador = 0;
        for(DashboardList pos : consultasList.values()) {
            if(contador != 0) {
                final Paciente posPaciente = pos.getPaciente();
                elementosListView.add(pos);
                //CREO LAS LABEL
                Font fuente = new Font("Corbel", 14);
                hora = new Label(pos.getDatosConsulta().getHora() + ":");
                hora.setFont(fuente);
                hora.setLayoutX(10);
                hora.setLayoutY(15);
                hora.setStyle("-fx-text-fill: white;");

                paciente = new Label(posPaciente.getNombre() + " " + posPaciente.getApellidos());
                paciente.setFont(fuente);
                paciente.setLayoutX(50);
                paciente.setLayoutY(15);
                paciente.setStyle("-fx-text-fill: white;");

                //AÑADO LOS LABEL AL ANCHOR
                posicion = new AnchorPane(hora, paciente);

                //LE APLICO ESTILO AL ANCHOR
                posicion.setMinHeight(50);
                if(contador % 2 == 0) {
                    posicion.setStyle("-fx-background-color: rgba(44, 128, 239, 0.8); -fx-background-radius: 10;");
                } else {
                    posicion.setStyle("-fx-background-color: rgba(44, 128, 239, 0.5); -fx-background-radius: 10;");
                }

                posicion.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Alert a = new Alert(Alert.AlertType.CONFIRMATION, Constantes.DASHBOARD_EDIT_CONFIRMATION);
                        a.show();
                        a.setOnHidden(new EventHandler<DialogEvent>() {
                            @Override
                            public void handle(DialogEvent event) {
                                if (a.getResult() == ButtonType.OK) { //SI EL MEDICO CONFIRMA CARGO LA VENTANA PARA EDITAR EL PACIENTE
                                    cargarDatosPaciente(pacienteAtender);
                                }
                            }
                        });
                    }
                });

                anchorList.add(posicion);
            } else {
                pacienteAtender = pos.getPaciente();
                siguienteConsulta.setText("Tu próximo paciente es " + pacienteAtender.getNombre() + " " + pacienteAtender.getApellidos()); //LE DIGO AL DOCTOR EL NOMBRE DE SU PROXIMO PACIENTE

                //AÑADO UN LISTENER AL BUTTON DE ATENDER
                atenderConsulta.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        cargarDatosPaciente(pacienteAtender);
                    }
                });
            }
            contador++;
        }
        ObservableList<AnchorPane> listaAnchors = FXCollections.observableArrayList(anchorList);
        proximasConsultas.setItems(listaAnchors);
    }

    //CARGO LOS DATOS DEL PACIENTE
    private void cargarDatosPaciente(Paciente p) {
        try {
            SearchController.pacienteSeleccionado = p;
            AnchorPane root;
            AnchorPane scene_panel = Interfaz.scene_panel;
            root = (AnchorPane) FXMLLoader.load(getClass().getResource("Pacientes.fxml"));

            ObservableList<Node> panelHijos = scene_panel.getChildren();
            if(panelHijos.size() > 0) {
                panelHijos.remove(0);
            }

            scene_panel.getChildren().add(root);

            String screenTitle = p.getNombre() + " " + p.getApellidos();
            Interfaz.nombre_escena.setText(screenTitle);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void calcularDatos() {
        Doctor doc = Data.doctor;
        HashMap<String, Paciente> pacientes = doc.getPacientes();

        pacientesAsignados = pacientes.size(); //NUMERO DE PACIENTES ASIGNADOS

        //COJO LA FECHA ACTUAL
        String fechaActual = Data.getFecha();

        HashMap<Integer, DashboardList> mapaConsultas = new HashMap<>();
        consultasPending = 0;
        medicamentosRecetados = 0;
        hombres = 0;
        mujeres = 0;
        edades = new int[]{0, 0, 0, 0, 0};
        Paciente p;
        DashboardList dashboardList;
        ArrayList<Consulta> consultas;

        for(Map.Entry<String, Paciente> entry : pacientes.entrySet()) { //RECORRO LA INFORMACION DEL DOCTOR
            p = entry.getValue();
            consultas = p.getConsultas();
            consultasPending = consultasPending + consultas.size(); //SUMO LAS CONSULTAS DE ESTE PACIENTE
            for(Consulta c : consultas) {
                if(c.getDia().equals(fechaActual)) { //CALCULO LAS CONSULTAS PENDIENTES QUE TIENE EL DOCTOR
                    dashboardList = new DashboardList(c, p);
                    mapaConsultas.put(Integer.parseInt(c.getHora().replaceAll(":","")), dashboardList);
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

        consultasList = new TreeMap<>(mapaConsultas);
        consultasHoy = consultasList.size();

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
