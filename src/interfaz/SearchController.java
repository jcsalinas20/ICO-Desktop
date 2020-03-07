package interfaz;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import clases.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import programa.Data;

public class SearchController implements Initializable {

	private HashMap<String, Paciente> pacientes;
	
	private ArrayList<String> nombresPacientes;

	@FXML
	private TextField search_text;

	@FXML
	private ListView<String> pacientes_list;

	@FXML
	private ImageView paciente_image;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pacientes = Data.pacientes;
		nombresPacientes = new ArrayList<>();
		busqueda(); //CARGO TODOS LOS PACIENTES
	}

	public void busqueda() {
		pacientes_list.getItems().removeAll(nombresPacientes); //BORRO TODO EL CONTENIDO DE LA LISTA
		nombresPacientes = new ArrayList<>();
		
		String textoBusqueda = search_text.getText(); //RECOJO EL TEXTO ESCRITO POR EL USUARIO
		String nombrePos;
		for(Entry<String, Paciente> entry : pacientes.entrySet()) {
			nombrePos = entry.getKey(); //RECORRO EL MAPA RECOGIENDO LOS NOMBRES DE PACIENTES
			if(nombrePos.startsWith(textoBusqueda)) {
				nombresPacientes.add(nombrePos); //SI EMPIEZAN POR EL TEXTO ESCRITO POR EL USUARIO LOS AÑADO A UNA LISTA
			}
		}
		ObservableList<String> items = FXCollections.observableArrayList(nombresPacientes);
		pacientes_list.setItems(items); //AÑADO LOS NOMBRES DE LA LISTA AL LISTVIEW
	}
	
}
