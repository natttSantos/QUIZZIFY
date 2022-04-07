package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.UsuarioInstructor;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;



public class CrearCursoController implements Initializable {
    private UsuarioInstructor instructorUser; 
    private Curso curso; 
    @FXML
    private Label instructor;
    @FXML
    private TextField nombreCurso;
    @FXML
    private TextField emailEstudiante;
    @FXML
    private ListView<?> listaEstudiantes;

    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
    }  

    public void setInstructorUser(UsuarioInstructor instructorUser) {
        this.instructorUser = instructorUser;
    }

    @FXML
    private void pulsarAtras(ActionEvent event) {
    }

    @FXML
    private void pulsarCrearCurso(ActionEvent event) {
    }

    @FXML
    private void pulsarAnyadirEstudiante(ActionEvent event) {
    }


    
   
   
    
}
