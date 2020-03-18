package init;
	
import conexion.MongoConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import programa.Data;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
			
	@Override
	public void start(Stage primaryStage) {
		loadData(); //CARGO LOS DATOS DEL PROGRAMA
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
			Scene scene = new Scene(root,1000,800); //LE DOY DIMENSIONES DE 1000x600
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene); 
			primaryStage.setResizable(false); //NO PERMITO QUE SE PUEDA MODIFICAR EL SIZE 
			Data.interfaz = primaryStage;
			Data.interfaz.show();
		} catch(Exception e) {
			
		}
	}

	private void loadData() {
		new MongoConnection(); //CARGO LA BASE DE DATOS
	}

	public static void main(String[] args) {
		launch(args);
	}
}
