package init;

import conexion.MongoConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML
	private TextField login_user;
	
	@FXML
	private TextField login_password;
	
	@FXML
	private Button login_button;
	
	
	public void controlarClick() {
		MongoConnection mongoDB = new MongoConnection();
		if(mongoDB.checkLogin()) {
			
		}
	}
	
}
