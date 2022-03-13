/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author margr
 */
public class DatosCrearAleatorioController implements Initializable {

    @FXML
    private TextField numeroTextField;
    @FXML
    private CheckBox checkbox;
    @FXML
    private Label temaLabel;
    @FXML
    private ChoiceBox<?> temaChoiceBox;
    @FXML
    private Button aceptarButton;
    @FXML
    private Button anularButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void numeroTextFieldClicked(ActionEvent event) {
    }

    @FXML
    private void checkboxClicked(ActionEvent event) {
        if (checkbox.isSelected()) {
            temaLabel.setDisable(false);
            temaChoiceBox.setDisable(false);
        } else {
            temaLabel.setDisable(true);
            temaChoiceBox.setDisable(true);
        }
    }

    @FXML
    private void temaChoiceBoxClicked(MouseEvent event) {
    }

    @FXML
    private void aceptarButtonClicked(ActionEvent event) {
    }

    @FXML
    private void anularButtonClicked(ActionEvent event) {
    }
    
}
