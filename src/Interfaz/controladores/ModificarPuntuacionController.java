/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author crivi
 */
public class ModificarPuntuacionController implements Initializable {

    @FXML
    private Label instructor;
    @FXML
    private Button botonModificar;
    @FXML
    private Label pregunta;
    
    private UsuarioInstructor instructorConectado; 
    private QuizAbstracto quizSeleccionado;
    private Curso cursoSeleccionado;
    private PreguntaAbstracta preguntaSeleccionada;
    private Conexion conexion;
    @FXML
    private Label puntuacionActual;
    @FXML
    private TextField textNuevaPuntuacion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = Conexion.obtenerConexion();
    }    
    
    public void setIntructorConectado(UsuarioInstructor instructorConectado){
        this.instructorConectado = instructorConectado;
    }
    
    public void setQuizSeleccionado(QuizAbstracto quiz){
        this.quizSeleccionado = quiz;
    }
    public void setCursoSeleccionado(Curso curso){
        this.cursoSeleccionado = curso;
    }
    public void setPreguntaSeleccionado(PreguntaAbstracta pregunta){
        this.preguntaSeleccionada = pregunta;
    }
    
    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/GestionQuizzes2.fxml"));
        Parent root =(Parent) loader.load();      
        GestionQuizzes2Controller quizzes = loader.<GestionQuizzes2Controller>getController();
        quizzes.setQuizSeleccionado(quizSeleccionado);
        quizzes.setIntructorConectado(instructorConectado);
            quizzes.setCursoSeleccionado(cursoSeleccionado);
            quizzes.cargarPreguntasDelQuiz();
            quizzes.alumnosDelQuiz();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.setResizable(false);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void modificar(ActionEvent event) throws IOException {
        String puntuacion = textNuevaPuntuacion.getText();
        Double nuevaPuntuacion = Double.parseDouble(puntuacion);
        conexion.modificarPuntuacion(nuevaPuntuacion, preguntaSeleccionada);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/GestionQuizzes2.fxml"));
        Parent root =(Parent) loader.load();      
        GestionQuizzes2Controller quizzes = loader.<GestionQuizzes2Controller>getController();
        quizzes.setQuizSeleccionado(quizSeleccionado);
        quizzes.setIntructorConectado(instructorConectado);
            quizzes.setCursoSeleccionado(cursoSeleccionado);
            quizzes.cargarPreguntasDelQuiz();
            quizzes.alumnosDelQuiz();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.setResizable(false);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
    public void cargarPregunta(String text, Double puntuacion){
        pregunta.setText(text);
        puntuacionActual.setText(puntuacion.toString());
    }
}
