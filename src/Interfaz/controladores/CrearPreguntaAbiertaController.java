/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import Persistencia.conexion.Conexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author margr
 */
public class CrearPreguntaAbiertaController implements Initializable {

    @FXML
    private Label instructor;
    @FXML
    private Button botonCrear;
    @FXML
    private TextArea textoPregunta;
    
    private Conexion conexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = Conexion.obtenerConexion();
        
        botonCrear.setDisable(true);
        
        textoPregunta.textProperty().addListener((ObservableValue<? extends String> obs, String oldValue, String newValue) -> {
            if (newValue.equals("")){
                botonCrear.setDisable(true);
            } else {
                botonCrear.setDisable(false);
            }
        });
    }    

    @FXML
    private void pulsarAtras(ActionEvent event) {
    }

    @FXML
    private void crearPregunta(ActionEvent event) {
        String texto = textoPregunta.getText();
        
        // texto : enunciado de pregunta
        // falta subir pregunta a base de dato
    }
    
}
