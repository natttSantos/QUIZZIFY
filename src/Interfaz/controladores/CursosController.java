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



public class CursosController implements Initializable {
    private UsuarioInstructor instructorUser; 
    private Curso curso; 
    @FXML
    private Label instructor;
    @FXML
    private ListView<String> listaCursos;
    @FXML
    private Label sinCursos;

    
   
    
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
    private void pulsarAbrirCurso(ActionEvent event) {
    }


    
   
   
    
}
