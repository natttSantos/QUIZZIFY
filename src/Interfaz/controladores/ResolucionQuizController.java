/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nata2
 */
public class ResolucionQuizController implements Initializable {

    @FXML
    private AnchorPane contenedor;
    @FXML
    private Label instructor;
    @FXML
    private Label fraccionPregunta;
    @FXML
    private Label pregunta;
    @FXML
    private RadioButton verdadero;
    @FXML
    private RadioButton falso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pulsarAtras(ActionEvent event) {
    }

    @FXML
    private void pulsarContinuar(ActionEvent event) {
    }
    
}
