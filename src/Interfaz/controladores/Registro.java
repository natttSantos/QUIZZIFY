/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Interfaz.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author margr
 */
public class Registro implements Initializable {

    @FXML
    private Label tipoUsuarioLabel;
    @FXML
    private ComboBox<String> comboBoxCurso;
    @FXML
    private TextField nombre;
    @FXML
    private TextField apelidos;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private ComboBox<String> comboBoxGrupo;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBoxCurso.getItems().addAll(
        "1º",
         "2º",
        "3º", 
        "4º"
        );
        comboBoxGrupo.getItems().addAll(
        "1º",
         "2º",
        "3º", 
        "4º"
        );
    }    

    @FXML
    private void pulsarIniciarSesion(ActionEvent event) {
    }

    @FXML
    private void pulsarAtras(ActionEvent event) {
    }


    

    
}
