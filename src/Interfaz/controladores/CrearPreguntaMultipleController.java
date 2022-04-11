/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.RespuestaSeleccion;
import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author margr
 */
public class CrearPreguntaMultipleController implements Initializable {

    @FXML
    private Label instructor;
    @FXML
    private Button botonCrear;
    @FXML
    private TextArea textoPregunta;
    @FXML
    private Button addButton;
    @FXML
    private CheckBox r1checkBox;
    @FXML
    private CheckBox r2checkBox;
    @FXML
    private CheckBox r3checkBox;
    @FXML
    private CheckBox r4checkBox;
    @FXML
    private CheckBox r5checkBox;
    @FXML
    private CheckBox r6checkBox;
    @FXML
    private TextField respuestaText;
    
    private int numeroDeRespuestas;
    private RespuestaSeleccion respuestas;
    protected ArrayList<String> opciones; 
    protected ArrayList<Boolean> opcionesCorrectas; 
    private Conexion conexion;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = Conexion.obtenerConexion();
        
        numeroDeRespuestas = 0;
        addButton.setDisable(true);
        botonCrear.setDisable(true);
        
        respuestaText.textProperty().addListener((ObservableValue<? extends String> obs, String oldValue, String newValue) -> {
            if (newValue.equals("") || numeroDeRespuestas == 6){
                addButton.setDisable(true);
            } else {
                addButton.setDisable(false);
            }
        });
        
        textoPregunta.textProperty().addListener((ObservableValue<? extends String> obs, String oldValue, String newValue) -> {
            if (newValue.equals("") || numeroDeRespuestas < 2){
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
        // TODO
    }

    @FXML
    private void addButtonClicked(ActionEvent event) {
        String text = respuestaText.getText();
        respuestaText.setText("");
        numeroDeRespuestas++;
        
        switch (numeroDeRespuestas) {
            case 1:
                r1checkBox.setText(text);
                r1checkBox.setVisible(true);
                break;
            case 2:
                r2checkBox.setText(text);
                r2checkBox.setVisible(true);
                
                if (!textoPregunta.getText().equals("")) {  // 2 respuestas es minimo para crear pregunta
                    botonCrear.setDisable(false);
                }
                
                break;
            case 3:
                r3checkBox.setText(text);
                r3checkBox.setVisible(true);
                break;
            case 4:
                r4checkBox.setText(text);
                r4checkBox.setVisible(true);
                break;
            case 5:
                r5checkBox.setText(text);
                r5checkBox.setVisible(true);
                break;
            case 6:
                r6checkBox.setText(text);
                r6checkBox.setVisible(true);
                addButton.setDisable(true);
                break;
        }
    }

    
}
