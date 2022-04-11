/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionInstructor.fxml"));
        Parent root =(Parent) loader.load();      
        SesionInstructorController sesionInstructor = loader.<SesionInstructorController>getController();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void crearPregunta(ActionEvent event) {
        String texto = textoPregunta.getText();
        
        // texto : enunciado de pregunta
        // falta subir pregunta a base de dato
    }
    
}
