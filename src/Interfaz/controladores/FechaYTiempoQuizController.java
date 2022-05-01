/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jaime
 */
public class FechaYTiempoQuizController implements Initializable {

    @FXML
    private CheckBox abrirAlCrearButton;
    @FXML
    private DatePicker fechaInicioPicker;
    @FXML
    private DatePicker fechaFinPicker;
    @FXML
    private CheckBox enableTiempoLimiteButton;
    @FXML
    private TextField tiempoLimite;
    @FXML
    private Button exitButton;
    @FXML
    private Button acceptButton;
    private LocalDate fechaIni = null, fechaFin = null;
    private int mins;
    private boolean cancel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cancel = true;
    }    

    @FXML
    private void abrirAlCrear(ActionEvent event) {
        
        if (abrirAlCrearButton.isSelected()){
        fechaInicioPicker.setDisable(true);
        }
        else{
        fechaInicioPicker.setDisable(false);
        }
        
    }

    @FXML
    private void EnableTiempoLímite(ActionEvent event) {
        if (enableTiempoLimiteButton.isSelected()){
        tiempoLimite.setDisable(false);
        }
        else{
        tiempoLimite.setDisable(true);
        }
    }

    @FXML
    private void exit(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();;
    }

    @FXML
    private void accept(ActionEvent event) {
        cancel = false;
        if(abrirAlCrearButton.isSelected()){
        fechaIni = LocalDate.now();
        }
        else{
            if(fechaInicioPicker.getValue()== null){System.out.println("FALLO");}    
            else{
            fechaIni = fechaInicioPicker.getValue();
            }
        }
        if(fechaFinPicker.getValue()== null){System.out.println("FALLO2");}
        else{
        fechaFin = fechaFinPicker.getValue();
        }
        if (enableTiempoLimiteButton.isSelected()&& !("".equals(tiempoLimite.getText()))) {mins = Integer.parseInt(tiempoLimite.getText());}
        else{if("".equals(tiempoLimite.getText())){System.out.println("FALLO3");}}
         Stage stage = (Stage) acceptButton.getScene().getWindow();
        stage.close();
                
    }
        public boolean getCancelar() {
        return cancel;
    }
    
    public LocalDate getfechaIni() {
        return fechaIni;
    }
    
    public LocalDate getfechaFIn() {
        return fechaFin;
    }
    public int getMins(){
        return mins;
    }
    
}
