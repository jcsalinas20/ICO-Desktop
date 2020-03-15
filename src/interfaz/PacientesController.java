package interfaz;

import clases.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import programa.Data;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PacientesController implements Initializable {

    @FXML
    private TableView<TablaConsultas> consultasTable;

    @FXML
    private TableColumn<TablaConsultas, String> columnDia;

    @FXML
    private TableColumn<TablaConsultas, String> columnHora;

    @FXML
    private TableColumn<TablaConsultas, String> columnNotasPaciente;

    @FXML
    private TableColumn<TablaConsultas, String> columnNotasDoctor;

    @FXML
    private TableColumn<TablaConsultas, CheckBox> columnAsistencia;

    @FXML
    private TableView<TablaMedicamentos> medicamentosTable;

    @FXML
    private TableColumn<TablaConsultas, String> columnMedicamento;

    @FXML
    private TableColumn<TablaConsultas, String> columnHoras;

    @FXML
    private TableColumn<TablaConsultas, CheckBox> columnLunes;

    @FXML
    private TableColumn<TablaConsultas, CheckBox> columnMartes;

    @FXML
    private TableColumn<TablaConsultas, CheckBox> columnMiercoles;

    @FXML
    private TableColumn<TablaConsultas, CheckBox> columnJueves;

    @FXML
    private TableColumn<TablaConsultas, CheckBox> columnViernes;

    @FXML
    private TableColumn<TablaConsultas, CheckBox> columnSabado;

    @FXML
    private TableColumn<TablaConsultas, CheckBox> columnDomingo;

    private Paciente paciente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paciente = SearchController.pacienteSeleccionado;
        loadConsultas();
        loadMedicamentos();
    }

    private void loadConsultas() {

        ArrayList<TablaConsultas> tablaConsultas = new ArrayList<>();
        TablaConsultas tablaConsultaPos;
        String hora, dia, notasPaciente, notasDoctor;
        CheckBox checkBox;

        //RECORRO TODAS LAS CONSULTAS DEL PACIENTE
        for(Consulta consultaPos : paciente.getConsultas()) {
            hora = consultaPos.getHora();
            dia = consultaPos.getDia();
            notasPaciente = consultaPos.getNotasPaciente();
            notasDoctor = consultaPos.getNotasDoctor();
            checkBox = new CheckBox();
            if(consultaPos.isAsistido()) {
                checkBox.setSelected(true);
            } else {
                checkBox.setSelected(false);
            }
            tablaConsultaPos = new TablaConsultas(hora, dia, notasPaciente, notasDoctor, checkBox);
            tablaConsultas.add(tablaConsultaPos);
        }

        //CONFIGURO LA TABLA
        ObservableList<TablaConsultas> datosTabla = FXCollections.observableList(tablaConsultas);
        consultasTable.setItems(datosTabla);
        columnDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
        columnHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        columnNotasPaciente.setCellValueFactory(new PropertyValueFactory<>("notasPaciente"));
        columnNotasDoctor.setCellValueFactory(new PropertyValueFactory<>("notasDoctor"));
        columnAsistencia.setCellValueFactory(new PropertyValueFactory<>("asistido"));
    }

    private void loadMedicamentos() {

        ArrayList<TablaMedicamentos> medicamentos = new ArrayList<>();
        TablaMedicamentos tablaMedicamentoPos;

        String medicamento, horas;
        CheckBox lunes, martes, miercoles, jueves, viernes, sabado, domingo;
        int[] dias;

        for(MedicamentoPaciente medicamentoPos : paciente.getMedicinas()) {
            medicamento = Data.medicamentos.get(medicamentoPos.getIdMedicamento()).getNombre(); //COJO EL NOMBRE DEL MEDICAMENTO

            //RECOJO LAS HORAS A LAS QUE EL PACIENTE DEBE TOMAR EL MEDICAMENTO
            horas = "";
            for(String horaPos : medicamentoPos.getHoras()) {
                horas = horas + horaPos + "; ";
            }
            horas = horas.substring(0, horas.length()-2); //ELIMINO LA ULTIMA COMA

            //RECOJO LOS DIAS PARA TOMAR LA MEDICACION
            dias = medicamentoPos.getDias();
            lunes = checkDiaSeleccionado(dias[0]);
            martes = checkDiaSeleccionado(dias[1]);
            miercoles = checkDiaSeleccionado(dias[2]);
            jueves = checkDiaSeleccionado(dias[3]);
            viernes = checkDiaSeleccionado(dias[4]);
            sabado = checkDiaSeleccionado(dias[5]);
            domingo = checkDiaSeleccionado(dias[6]);

            tablaMedicamentoPos = new TablaMedicamentos(medicamento, horas, lunes, martes, miercoles, jueves, viernes, sabado, domingo);
            medicamentos.add(tablaMedicamentoPos);
            }

        //CONFIGURO LA TABLA
        ObservableList<TablaMedicamentos> datosTabla = FXCollections.observableList(medicamentos);
        medicamentosTable.setItems(datosTabla);
        columnMedicamento.setCellValueFactory(new PropertyValueFactory<>("medicamento"));
        columnHoras.setCellValueFactory(new PropertyValueFactory<>("horas"));
        columnLunes.setCellValueFactory(new PropertyValueFactory<>("lunes"));
        columnMartes.setCellValueFactory(new PropertyValueFactory<>("martes"));
        columnMiercoles.setCellValueFactory(new PropertyValueFactory<>("miercoles"));
        columnJueves.setCellValueFactory(new PropertyValueFactory<>("jueves"));
        columnViernes.setCellValueFactory(new PropertyValueFactory<>("viernes"));
        columnSabado.setCellValueFactory(new PropertyValueFactory<>("sabado"));
        columnDomingo.setCellValueFactory(new PropertyValueFactory<>("domingo"));
        }

    private CheckBox checkDiaSeleccionado(int valor) {
        CheckBox checkBox = new CheckBox();
        if(valor == 1) {
            checkBox.setSelected(true);
        }
        return checkBox;
    }

}
