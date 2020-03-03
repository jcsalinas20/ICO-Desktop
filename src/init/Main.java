package init;
	
import conexion.MongoConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		loadData(); //CARGO LOS DATOS DEL PROGRAMA
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
			Scene scene = new Scene(root,1280,720); //LE DOY DIMENSIONES DE 1280X720
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene); 
			primaryStage.setResizable(false); //NO PERMITO QUE SE PUEDA MODIFICAR EL SIZE 
			primaryStage.show();
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
