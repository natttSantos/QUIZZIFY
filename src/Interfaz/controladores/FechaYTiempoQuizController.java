/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.UsuarioInstructor;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.Action;

/**
 * FXML Controller class
 *
 * @author jaime
 */
public class FechaYTiempoQuizController implements Initializable {
    //Datos GenerarQuizNoAleatorio
    private String nombreQuiz; 
    private String menuCurso; 
    ObservableList<String> lista; 
    
    
    
    LocalDate dateInicio; 
    LocalDate dateFin; 
   
    private UsuarioInstructor instructorConectado;
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
    private Button acceptButton;
    
    private int mins;
    private boolean error = false;
    @FXML
    private Label tipoUsuarioLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tiempoLimite.setDisable(true);
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

    private void exit(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();;
    }

    @FXML
    private void accept(ActionEvent event) throws IOException {
        error = false; 
        comprobarCamposNulos();
        comprobarFechasCorrectas();

        if (enableTiempoLimiteButton.isSelected()&& !("".equals(tiempoLimite.getText()))) {
            mins = Integer.parseInt(tiempoLimite.getText());
        }
        else if("".equals(tiempoLimite.getText())){
             error = true; 
             enviarAlerta("ERROR", "Debe indicar un tiempo límite!");   
         }

        if(!error){
            enviarAlerta("CONFIRMATION", "Configuración quiz guardada!"); 
            navegarFormAnterior(event); 
        }
                
    }
    
    public void comprobarCamposNulos (){
        if(fechaInicioPicker.getValue()== null){
            enviarAlerta("ERROR", "Debe completar la fecha inicio!");
            error = true; 
        }    
        else{dateInicio = fechaInicioPicker.getValue();}
        
        if(fechaFinPicker.getValue()== null){
            enviarAlerta("ERROR", "Debe completar la fecha fin!");
            error = true; 
        }
        else{ dateFin = fechaFinPicker.getValue();}  
    }
    
    public void comprobarFechasCorrectas (){
        dateInicio = fechaInicioPicker.getValue(); 
        dateFin = fechaFinPicker.getValue(); 
        
        if(dateInicio.isAfter(dateFin)){
            enviarAlerta("ERROR", "Fecha apertura no puede ser anterior a la de fin!");
            error = true; 
        }  
    }

    @FXML
    private void pulsarAtras(ActionEvent event) {
    }
    
    private void enviarAlerta(String header,String text) {
        Alert dialogoAlerta;
        if(header.equals("ERROR")){
           dialogoAlerta = new Alert(Alert.AlertType.ERROR); 
        }else {
            dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION); 
        }
        dialogoAlerta.setTitle(null);
        dialogoAlerta.setHeaderText(header);
        dialogoAlerta.setContentText(text);
        java.awt.Toolkit.getDefaultToolkit().beep();
        dialogoAlerta.showAndWait(); 
    }

    public LocalDate getDateInicio() {
        return dateInicio;
    }

    public void setDateInicio(LocalDate dateInicio) {
        this.dateInicio = dateInicio;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public TextField getTiempoLimite() {
        return tiempoLimite;
    }

    public void setTiempoLimite(TextField tiempoLimite) {
        this.tiempoLimite = tiempoLimite;
    }

    public void navegarFormAnterior (ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/GenerarQuizNoAleatorio.fxml"));
        Parent root = loader.load();
        GenerarQuizNoAleatorioController controlador = loader.getController();
        controlador.setUsuario(instructorConectado);
        controlador.setDateInicio(dateInicio);
        controlador.setDateFin(dateFin);
        controlador.setTiempoLimite(mins);
        controlador.addCursosToMenu();
        controlador.recordarData(nombreQuiz, menuCurso, lista);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Crear un quiz");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void setInstructorConectado(UsuarioInstructor instructorConectado) {
        this.instructorConectado = instructorConectado;
    }

    public void setNombreQuiz(String nombreQuiz) {
        this.nombreQuiz = nombreQuiz;
    }

    public void setMenuCurso(String menuCurso) {
        this.menuCurso = menuCurso;
    }

    public void setLista(ObservableList<String> lista) {
        this.lista = lista;
    }
    
    
    
}
