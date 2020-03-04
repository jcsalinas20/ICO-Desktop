package interfaz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import programa.Data;

public class Interfaz {
		
	private Scene escena;

	@FXML
	private AnchorPane scene_panel;
	
	public void loadInterface() {
		Stage interfaz_load = new Stage();
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Interfaz.fxml"));
			escena = new Scene(root,1280,720); //LE DOY DIMENSIONES DE 1280X720
			interfaz_load.setScene(escena); 
			interfaz_load.setResizable(false); //NO PERMITO QUE SE PUEDA MODIFICAR EL SIZE 
			Data.interfaz = interfaz_load;
			loadSearch();
			Data.interfaz.show();
		} catch(Exception e) {
			
		}
	}
	
	public void loadSearch() {
		
	}
	
	public void agregarPaciente() {
		
	}
	
	public void logout() {
		Data.interfaz.close();
	}
}
