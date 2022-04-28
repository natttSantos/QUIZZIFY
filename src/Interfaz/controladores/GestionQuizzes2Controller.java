/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.NotaQuizz;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaRespondida;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import com.sun.glass.events.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author crivi
 */
public class GestionQuizzes2Controller implements Initializable {

    private UsuarioInstructor instructorConectado;
    private Curso cursoSeleccionado;
    private Conexion con;
    private QuizAbstracto quizSeleccionado;
    @FXML
    private Label instructor;
    private ListView<String> listaQuizzes;
    private Label sinQuizzes;
    @FXML
    private Label textTitulo;
    @FXML
    private ListView<String> listaAlumnos;
    @FXML
    private Label texto;
    @FXML
    private Button botonRespuestas;
    @FXML
    private TableView<PreguntaRespondida> tablaNotas;
    @FXML
    private ListView<String> listaPreguntas;
    @FXML
    private Label texto1;
    @FXML
    private Button botonMostrarRespuestas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Conexion.obtenerConexion();
        cargarPreguntasDelQuiz();
        alumnosDelQuiz();
        
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
    
    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/GestionQuizzes.fxml"));
        Parent root =(Parent) loader.load();      
        GestionQuizzesController  quizzes = loader.<GestionQuizzesController>getController();
        quizzes.setIntructorConectado(instructorConectado);
        quizzes.setCursoSeleccionado(cursoSeleccionado);
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();  
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    
    public void cargarPreguntasDelQuiz() {
        ArrayList<PreguntaAbstracta> preguntas = quizSeleccionado.getPreguntas();
        for (PreguntaAbstracta pregunta:preguntas){
            String nombre = pregunta.getText();
            listaPreguntas.getItems().add(nombre);
        }
        
    }
    
    public void alumnosDelQuiz(){
        ArrayList<NotaQuizz> notas = con.obtenerNotasDeQuiz(quizSeleccionado);
        for (NotaQuizz nota:notas){
            String texto = nota.getAlumno();
            listaAlumnos.getItems().add(texto);
        }
    }

    @FXML
    private void pulsarMostrarRespuestas(ActionEvent event) {
        String nombreAlumno = listaAlumnos.getSelectionModel().getSelectedItem();
        if (nombreAlumno != null) {
            tablaNotas.setVisible(true);
            TableColumn<PreguntaRespondida, String> col = new TableColumn<>("Pregunta");
            TableColumn<PreguntaRespondida, String> col1 = new TableColumn<>("Respuesta");
            tablaNotas.getColumns().addAll(col,col1);
            UsuarioAlumno alumno = con.obtenerUsuarioAlumno("nombre", nombreAlumno);
            NotaQuizz nota = con.obtenerRespuestasDeQuizDeAlumno(alumno, quizSeleccionado);
            ArrayList<PreguntaRespondida> respuestas = nota.getRespuestas();
            for (PreguntaRespondida respuesta:respuestas){
                tablaNotas.getItems().add(respuesta);
            }
        }
    }

    
}
