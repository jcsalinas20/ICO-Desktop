package init;

import conexion.MongoActions;
import interfaz.Interfaz;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import programa.Data;

public class LoginController {
	
	@FXML
	private TextField login_user;
	
	@FXML
	private PasswordField login_password;
	
	@FXML
	private Button login_button;
	
	@FXML
	private Label login_error;
	
	
	public void controlarClick() {
		MongoActions mongo = new MongoActions();
		String user = login_user.getText();
		String pass = login_password.getText();
		
		//COMPRUEBO SI LOS DATOS SON CORRECTOS
		if(mongo.checkLogin(user, pass)) { 
			loginDone(mongo);
		} else {
			loginError();
		}
	}
	
	//ES INVOCADO SI LOS DATOS DE LOGIN SON CORRECTOS
	private void loginDone(MongoActions mongo) {
		mongo.loadDoctor(); //CARGO LOS DATOS DEL DOCTOR
		Data.interfaz.close(); //CIERRO LA ESCENA ACTUAL
		new Interfaz().loadInterface(); //CARGO LA INTERFAZ
		
	}
	
	//ES INVOCADO SI LOS DATOS DE LOGIN SON INCORRECTOS
	private void loginError() {
		//LIMPIO LOS TEXTFIELD Y MUESTRO UN LABEL DE ERROR
		login_user.setText("");
		login_password.setText("");
		login_error.setVisible(true);
	}
}