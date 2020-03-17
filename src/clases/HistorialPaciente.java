package clases;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class HistorialPaciente {

    private AnchorPane data;

    public HistorialPaciente(String fecha, String nota) {
        Label lab = new Label(fecha + ": " + nota);
        Font font = new Font("SansSerif", 16);
        lab.setFont(font);
        this.data = new AnchorPane(lab);
        lab.setLayoutY(32);
        lab.setLayoutX(5);
    }

    public AnchorPane getData() {
        return data;
    }
}
