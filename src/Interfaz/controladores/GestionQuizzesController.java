/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.NotaQuizz;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Arrays.asList;
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
import javafx.scene.control.TableView;
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
    @FXML
    private Label instructor;
    @FXML
    private ListView<String> listaQuizzes;
    @FXML
    private Label sinQuizzes;
    @FXML
    private Label textTitulo;
    @FXML
    private TableView<UsuarioAlumno> tablaRespuestas;

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

    
    public void cargarQuizzesDelCurso(){
       ArrayList<QuizAbstracto> quizzes = con.obtenerQuizzesDeCurso(cursoSeleccionado);
       for (int i = 0; i < quizzes.size() && quizzes.size() > 0; i++) {
           QuizAbstracto quiz = quizzes.get(i);
           sinQuizzes.setVisible(false);
           listaQuizzes.getItems().add(quiz.getNombre());
       }
    }

    @FXML
    private void pulsarClonarQuiz(ActionEvent event) {
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
            con.insertarQuiz(nombre, curso, preguntas);
            cargarQuizzesDelCurso();
        }
    }

    @FXML
    private void pulsarMostrarRespuestas(ActionEvent event) {
        String nombreQuiz = listaQuizzes.getSelectionModel().getSelectedItem();
        if (nombreQuiz != null) {
            ArrayList<UsuarioAlumno> alumnos = con.obtenerTodosUsuariosAlumno();
            ArrayList<NotaQuizz> notas = new ArrayList();
            for (UsuarioAlumno alumno:alumnos){
                ArrayList<NotaQuizz> notasAlumno = alumno.getNotas();
                for (NotaQuizz nota:notasAlumno) {
                    nota.getQuizz() == nombreQuiz;
                }
            }
            tablaRespuestas.setDisable(false);
        }
    }
}
