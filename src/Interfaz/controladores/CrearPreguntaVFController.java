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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author margr
 */
public class CrearPreguntaVFController implements Initializable {

    @FXML
    private Label instructor;
    @FXML
    private Button botonCrear;
    @FXML
    private TextArea textoPregunta;
    @FXML
    private RadioButton verdadRadioButton;
    @FXML
    private RadioButton falsoRadioButton;
    
    private Conexion conexion;
    private boolean respuestaVerdad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = Conexion.obtenerConexion();
        
        ToggleGroup toggleGroup = new ToggleGroup();
        verdadRadioButton.setToggleGroup(toggleGroup);
        falsoRadioButton.setToggleGroup(toggleGroup);
        
        verdadRadioButton.setSelected(true);
        respuestaVerdad = true;
        
        botonCrear.setDisable(true);

        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal == verdadRadioButton) {
                respuestaVerdad = true;
            } else {
                respuestaVerdad = false;
            }
        });
        
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
        // respuestaVerdad : verdad o falso 
        // falta subir pregunta a base de dato
    }
    
}
