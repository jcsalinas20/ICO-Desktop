package interfaz;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import programa.Constantes;
import programa.Data;

import java.io.IOException;

public class Interfaz {
		
	private Scene escena;

	private AnchorPane root;

	@FXML
	public AnchorPane scene_panel;

	@FXML
	public Label nombre_escena;

	@FXML
	public ImageView dashboard_image;

	@FXML
	public ImageView consultas_image;

	@FXML
	public ImageView pacientes_image;

	public void loadInterface() {
		Stage interfaz_load = new Stage();
		try {
			AnchorPane interfaz = loadPanel("Interfaz.fxml"); //CARGO LA INTERFAZ

			//RELACIONO LAS IMAGENES
			ToolBar toolbar_left = (ToolBar) interfaz.getChildren().get(0);
			AnchorPane left_bar = (AnchorPane) toolbar_left.getItems().get(0);
			dashboard_image = (ImageView) left_bar.getChildren().get(0);
			pacientes_image = (ImageView) left_bar.getChildren().get(2);
			consultas_image = (ImageView) left_bar.getChildren().get(4);

			scene_panel = (AnchorPane) interfaz.getChildren().get(1); //RELACIONO EL ANCHOR PANE QUE IRA CAMBIANDO
			AnchorPane top_bar = (AnchorPane) interfaz.getChildren().get(2);
			nombre_escena = (Label) top_bar.getChildren().get(0); //RELACIONO CON EL LABEL DE LA ESCENA

			loadDashboard(); //MUESTRO AL MEDICO SU DASHBOARD
			escena = new Scene(interfaz);
			interfaz_load.setScene(escena);
			interfaz_load.setResizable(false); //NO PERMITO QUE SE PUEDA MODIFICAR EL SIZE
			interfaz_load.setMaximized(true); //MAXIMIZO LA VENTANA
			interfaz_load.initStyle(StageStyle.UNDECORATED); //BORRO LOS BORDES
			Data.interfaz = interfaz_load;
			Data.interfaz.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//CARGA EL DASHBOARD
	public void loadDashboard() {
		try {
			root = loadPanel("Dashboard.fxml");
			removeAnchorChildren();
			scene_panel.getChildren().add(root);
			nombre_escena.setText(Constantes.DASHBOARD_SCENE);
			dashboard_image.setOpacity(1);
			consultas_image.setOpacity(0.44);
			pacientes_image.setOpacity(0.44);
		} catch (Exception e) {

		}
	}

	//CARGA LA INTERFAZ DE CONSULTAS
	public void loadConsultas() {
		try {
			root = loadPanel("Consultas.fxml");
			removeAnchorChildren();
			scene_panel.getChildren().add(root);
			nombre_escena.setText(Constantes.CONSULTAS_SCENE);
			consultas_image.setOpacity(1);
			dashboard_image.setOpacity(0.44);
			pacientes_image.setOpacity(0.44);
		} catch (Exception e) {

		}
	}

	//CARGA LA INTERFAZ DE CONSULTAS
	public void loadSearch() {
		try {
			root = loadPanel("Search.fxml");
			removeAnchorChildren();
			scene_panel.getChildren().add(root);
			nombre_escena.setText(Constantes.SEARCH_SCENE);
			pacientes_image.setOpacity(1);
			consultas_image.setOpacity(0.44);
			dashboard_image.setOpacity(0.44);
		} catch(Exception e) {

		}
	}

	//CIERRA EL PROGRAMA
	public void logout() {
		Data.interfaz.close();
	}
	
	//BORRA LOS HIJOS DEL ANCHOR PANE PARA CAMBIAR EL CONTENIDO
	private void removeAnchorChildren() {
		ObservableList<Node> panelHijos = scene_panel.getChildren();
		if(panelHijos.size() > 0) {
			panelHijos.remove(0);
		}
	}

	//BUSCA EL ARCHIVO FXL
	private AnchorPane loadPanel(String nombreFXML) throws IOException {
		return (AnchorPane) FXMLLoader.load(getClass().getResource(nombreFXML));
	}
}
