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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author crivi
 */
public class ModificarDificultadController implements Initializable {

    @FXML
    private Label instructor;
    @FXML
    private Label pregunta;
    @FXML
    private Label dificultadActual;
    @FXML
    private ComboBox<String> dificultadComboBox;
    ObservableList<String> dificultadesItems = FXCollections.observableArrayList();
    private UsuarioInstructor instructorConectado; 
    private QuizAbstracto quizSeleccionado;
    private Curso cursoSeleccionado;
    private PreguntaAbstracta preguntaSeleccionada;
    private Conexion conexion;
    @FXML
    private Button botonModificar;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = Conexion.obtenerConexion();
        ToggleGroup toggleGroup = new ToggleGroup();
        dificultadesItems.addAll("Baja", "Media", "Alta");
        dificultadComboBox.setItems(dificultadesItems);
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
        GestionQuizzes2Controller sesionInstructor = loader.<GestionQuizzes2Controller>getController();
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
        String dificultad = dificultadComboBox.getValue();
        conexion.modificarDificultad(dificultad, preguntaSeleccionada);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/GestionQuizzes2.fxml"));
        Parent root =(Parent) loader.load();      
        GestionQuizzes2Controller sesionInstructor = loader.<GestionQuizzes2Controller>getController();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.setResizable(false);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
    public void cargarPregunta(String text, String dificultad){
        pregunta.setText(text);
        dificultadActual.setText(dificultad);
    }
}
