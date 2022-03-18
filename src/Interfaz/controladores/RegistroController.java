/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author margr
 */
public class RegistroController implements Initializable {

    private boolean credenciales; 
    private String tipoUsuario; 
    @FXML
    private Label tipoUsuarioLabel;
    private ComboBox<String> comboBoxCurso;
    @FXML
    private TextField nombre;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private ComboBox<String> comboBoxTipoUsuario;
    @FXML
    private TextField apellidos;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ObservableList<String> usuario = FXCollections.observableArrayList("Estudiante", "Instructor"); 
         comboBoxTipoUsuario.setItems(usuario);
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

    @FXML
    private void pulsarRegistrar(ActionEvent event) throws IOException {
        credenciales = comprobarCredenciales(); 
         Conexion conexion = new Conexion(); 
        if(credenciales){
            if(tipoUsuario.equals("Estudiante")){
                UsuarioAlumno student = new UsuarioAlumno(nombre.getText(), apellidos.getText(), email.getText(), password.getText(), "", ""); 
                conexion.crearUsuarioAlumno(student);  
                navegar_SesionEstudiante(event);
            } else{
                String [] cursos = null; 
                UsuarioInstructor instructor = new UsuarioInstructor(nombre.getText(), apellidos.getText(), email.getText(), password.getText(), cursos); 
                conexion.crearUsuarioInstructor(instructor);
                navegar_SesionInstructor(event);
            }
        } 
    }

    public void navegar_SesionEstudiante(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionEstudiante.fxml"));
        Parent root =(Parent) loader.load();   
        SesionEstudianteController sesionEstudiante = loader.<SesionEstudianteController>getController();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
     public void navegar_SesionInstructor(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionInstructor.fxml"));
        Parent root =(Parent) loader.load();   
        SesionInstructorController sesionEstudiante = loader.<SesionInstructorController>getController();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    public boolean comprobarCredenciales(){
        credenciales = true; 
        if(nombre.getText().equals("") || apellidos.getText().equals("") || 
           email.getText().equals("") || password.getText().equals("") || tipoUsuario.equals("")) {
            credenciales = false; 
            Alert dialogoAlerta = new Alert(AlertType.ERROR); 
            dialogoAlerta.setTitle(null);
            dialogoAlerta.setHeaderText("¡ERROR! Campos incompletos");
            dialogoAlerta.setContentText("Debe completar todos los campos, incluído el tipo de usuario");
            java.awt.Toolkit.getDefaultToolkit().beep();
            dialogoAlerta.showAndWait(); 
        }
        return credenciales; 
    }

    @FXML
    private void pulsarSeleccionTipoUsuario(ActionEvent event) {
        String tipoUsuarioSeleccionado = comboBoxTipoUsuario.getSelectionModel().getSelectedItem().toString();
        this.tipoUsuario = tipoUsuarioSeleccionado;
    }
    

    
}
