package Interfaz.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;



public class CursosController implements Initializable {

    @FXML
    private Label instructor;
    @FXML
    private Button botonCrear;
    @FXML
    private Button botonCrear1;
    @FXML
    private ListView<?> listaCursos;

    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
    }    

    @FXML
    private void pulsarAtras(ActionEvent event) {
    }

    @FXML
    private void crearPregunta(ActionEvent event) {
    }

   
   
    
}
