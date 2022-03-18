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
import javafx.scene.control.Label;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import Persistencia.conexion.Conexion;
import LogicaNegocio.modelo.UsuarioAlumno;
import javafx.scene.control.Alert;


/**
 * FXML Controller class
 *
 * @author nata2
 */
public class InicioSesionController implements Initializable {
    @FXML
    private Label tipoUsuarioLabel;
    private String tipoUsuario;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField password;
    private Conexion con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        con = Conexion.obtenerConexion(); 

    }  

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        if(tipoUsuario.equals("Estudiante")){
            tipoUsuarioLabel.setText("ESTUDIANTE");
        } else{ tipoUsuarioLabel.setText("INSTRUCTOR");}
    }

    @FXML
    private void pulsarIniciarSesion(ActionEvent event) throws IOException {
        //Refactoring hecho Extract Method
        if(comprobarCredenciales()){
            Parent root = null;
            if(tipoUsuario.equals("Instructor")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionInstructor.fxml"));
                root =(Parent) loader.load();
                SesionInstructorController inicio = loader.<SesionInstructorController>getController();
                inicio.setNombreUsuario(usuario.getText());
                inicio.setConexion(con);
            } else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionEstudiante.fxml"));
                root =(Parent) loader.load();
                SesionEstudianteController inicio = loader.<SesionEstudianteController>getController(); 
             }
            Scene scene = new Scene (root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sesion " + tipoUsuario);
            stage.initModality(Modality.APPLICATION_MODAL); 
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/InicioPrograma.fxml"));
        Parent root =(Parent) loader.load();      
        InicioProgramaController inicio = loader.<InicioProgramaController>getController();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
     }
    
    public boolean comprobarCredenciales(){
        //Refactoring variable descriptiva
        String user = usuario.getText();
        String pass = password.getText();
        if(user.equals("") || pass.equals("") ){
            envioAlerta("Debe insertar usuario y contraseña!");
        } else {
            UsuarioAlumno u = con.login(user ,pass);
            if(u!=null) {
                return true;
            } else {
                envioAlerta("Usuario y/o contraseña incorrectos!");
            }
        }
        return false;
    }
    public void envioAlerta(String msgError){
            Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR); 
            dialogoAlerta.setTitle(null);
            dialogoAlerta.setHeaderText(null);
            dialogoAlerta.setContentText(msgError);
            java.awt.Toolkit.getDefaultToolkit().beep();
            dialogoAlerta.showAndWait(); 
    }
    
}  
