/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.QuizAbstracto;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author crivi
 */
public class GestionQuizzesController implements Initializable {
    
    private UsuarioInstructor instructorConectado;
    private Curso cursoSeleccionado;
    private Conexion con;
    @FXML
    private Label instructor;
    @FXML
    private ListView<String> listaQuizzes;
    @FXML
    private Label sinQuizzes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Conexion.obtenerConexion();
        cargarQuizzesDelCurso();
    }    
    
    public void setIntructorConectado(UsuarioInstructor instructorConectado){
        this.instructorConectado = instructorConectado;
    }
    
    public void setCursoSeleccionado(Curso curso){
        this.cursoSeleccionado = cursoSeleccionado;
    }
    
    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/Cursos.fxml"));
        Parent root =(Parent) loader.load();      
        CursosController  cursos = loader.<CursosController>getController();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();  
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void pulsarCrearCurso(ActionEvent event) {
    }


    @FXML
    private void pulsarClonarCurso(ActionEvent event) {
    }

    @FXML
    private void pulsarCurso(ActionEvent event) {
    }
    
    public void cargarQuizzesDelCurso(){
       ArrayList<QuizAbstracto> quizzes = con.obtenerQuizzesDeCurso(cursoSeleccionado);
       for (int i = 0; i < quizzes.size() && quizzes.size() > 0; i++) {
           QuizAbstracto quiz = quizzes.get(i);
           sinQuizzes.setVisible(false);
           listaQuizzes.getItems().add(quiz.getNombre());
       }
    }
}
