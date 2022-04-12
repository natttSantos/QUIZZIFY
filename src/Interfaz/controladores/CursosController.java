package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class CursosController implements Initializable {
    private UsuarioInstructor instructorUser; 
    private Curso curso; 
    @FXML
    private Label instructor;
    @FXML
    private ListView<String> listaCursos;
    @FXML
    private Label sinCursos;
    
    Conexion c = Conexion.obtenerConexion();
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }  

    public void setInstructorUser(UsuarioInstructor instructorUser) {
        this.instructorUser = instructorUser;
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
        ((Node) event.getSource()).getScene().getWindow().hide();
        stage.show();  
    }

    @FXML
    private void pulsarCrearCurso(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/CrearCurso.fxml"));
        Parent root = miCargador.load();
        CrearCursoController cursos = miCargador.getController();
        cursos.setInstructorUser(instructorUser);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setTitle("Mis cursos");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void pulsarAbrirCurso(ActionEvent event) {
    }


    
   
   
    
}
