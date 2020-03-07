package programa;

import javafx.scene.control.Alert;

public class CrearAlertas {

	//ERROR AL ESTABLECER LA CONEXION CON MONGO DB
	public static void mongoConnectionError() {
		Alert alert = new Alert(Alert.AlertType.ERROR, Constantes.MONGO_ERROR_CONNECTION);
		alert.show();
	}
	
}
