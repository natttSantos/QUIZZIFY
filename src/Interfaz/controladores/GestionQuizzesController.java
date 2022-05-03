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
public class GestionQuizzesController implements Initializable {
    
    private UsuarioInstructor instructorConectado;
    private Curso cursoSeleccionado;
    private Conexion con;
    private QuizAbstracto quizSeleccionado;
    @FXML
    private Label instructor;
    @FXML
    private ListView<String> listaQuizzes;
    @FXML
    private Label sinQuizzes;
    @FXML
    private Label textTitulo;
    private ListView<String> listaAlumnos;
    @FXML
    private Label texto;
    private Button botonVolverAQuizzes;
    private Button botonAlumnos;
    @FXML
    private Button botonClonarQuiz;
    private TableView<PreguntaRespondida> tablaNotas;
    @FXML
    private Button gestionarQuiz;
    @FXML
    private Button botonLanzarQuiz;
    @FXML
    private Button botonTerminarQuiz;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Conexion.obtenerConexion();
       
        
    }    
    
    public void setIntructorConectado(UsuarioInstructor instructorConectado){
        this.instructorConectado = instructorConectado;
    }
    
    public void setCursoSeleccionado(Curso curso){
        this.cursoSeleccionado = curso;
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

    
    public void cargarQuizzesDelCurso(){
       ArrayList<QuizAbstracto> quizzes = con.obtenerQuizzesDeCurso(cursoSeleccionado);
       for (int i = 0; i < quizzes.size() && quizzes.size() > 0; i++) {
           QuizAbstracto quiz = quizzes.get(i);
           sinQuizzes.setVisible(false);
           String aux = quiz.getNombre() + " Estado: " + quiz.getEstado();
           listaQuizzes.getItems().add(aux);
       }
    }


    @FXML
    private void clonarQuiz(ActionEvent event) {
       String nombreQuiz = listaQuizzes.getSelectionModel().getSelectedItem();
        if(nombreQuiz != null) {
            QuizAbstracto quiz =con.obtenerQuiz("nombre", nombreQuiz);
            String nombre = "Copia de " + quiz.getNombre();
            Document curso = quiz.getCurso().obtenerDocument();
            ArrayList<PreguntaAbstracta> lista = quiz.getPreguntas();
            Document[] preguntas = new Document[lista.size()];
            int i = 0;
            for (PreguntaAbstracta pregunta:lista){
                Document d = new Document();
                d.append("text", pregunta.getText())
                    .append("dificultad", pregunta.getDificultad())
                    .append("tema", pregunta.getTema()) 
                    .append("respuestas", asList(pregunta.getRespuestas()));
                preguntas[i] = d;
                i++;
            }
            con.insertarQuiz(nombre, curso, "En preparación",preguntas);
            cargarQuizzesDelCurso();
        }
    }

    @FXML
    private void pulsarGestionarQuiz(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/GestionQuizzes2.fxml"));
        Parent root = miCargador.load();
        GestionQuizzes2Controller  quizzes= miCargador.getController();
        quizzes.setIntructorConectado(instructorConectado);
        String nombreQuiz = listaQuizzes.getSelectionModel().getSelectedItem();
        if (nombreQuiz != null) {
            QuizAbstracto quiz = con.obtenerQuiz("nombre", nombreQuiz);
            quizzes.setQuizSeleccionado(quiz);
            quizzes.setCursoSeleccionado(cursoSeleccionado);
            quizzes.cargarPreguntasDelQuiz();
            quizzes.alumnosDelQuiz();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

    @FXML
    private void lanzarQuiz(ActionEvent event) {
        String nombreQuiz = listaQuizzes.getSelectionModel().getSelectedItem();
        if (nombreQuiz != null){
            String estado = "Lanzado";
            con.cambiarEstado(nombreQuiz, estado);
        }
    }

    @FXML
    private void terminarQuiz(ActionEvent event) {
        String nombreQuiz = listaQuizzes.getSelectionModel().getSelectedItem();
        if (nombreQuiz != null){
            String estado = "Terminado";
            con.cambiarEstado(nombreQuiz, estado);
        }
    }
    
}
