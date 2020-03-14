package interfaz;

import clases.Consulta;
import clases.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PacientesController implements Initializable {

    @FXML
    private TableView<Consulta> consultasTable;

    @FXML
    private TableView medicamentosTable;

    private Paciente paciente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paciente = SearchController.pacienteSeleccionado;
        loadConsultas();
        loadMedicamentos();
    }

    private void loadConsultas() {
        ArrayList<Consulta> consultas= paciente.getConsultas();
        for(Consulta consultaPos : consultas) {

        }
        ObservableList<Consulta> datosTabla = FXCollections.observableList(consultas);
        consultasTable.setItems(datosTabla);
    }

    private void loadMedicamentos() {

    }

}
