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
    @FXML
    private ListView<String> listaAlumnos;
    @FXML
    private Label texto;
    @FXML
    private Button botonVolverAQuizzes;
    @FXML
    private Button botonAlumnos;
    @FXML
    private Button botonRespuestas;
    @FXML
    private Button botonClonarQuiz;
    @FXML
    private TableView<PreguntaRespondida> tablaNotas;

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
    private void volverAQuizzes(ActionEvent event) {
        listaAlumnos.setVisible(false);
        listaQuizzes.setVisible(true);
        textTitulo.setText("QUIZZES DEL CURSO SELECCIONADO");
        texto.setText("Seleccione el curso que quera ver las respuestas de los estudiantes");
        botonAlumnos.setVisible(true);
        botonVolverAQuizzes.setVisible(false);
        botonClonarQuiz.setVisible(true);
    }

    @FXML
    private void pulsarMostrarAlumnos(ActionEvent event) {
        String nombreQuiz = listaQuizzes.getSelectionModel().getSelectedItem();
        if (nombreQuiz != null){
            QuizAbstracto quiz = con.obtenerQuiz("nombre", nombreQuiz);
            quizSeleccionado = quiz;
            ArrayList<NotaQuizz> notas = con.obtenerNotasDeQuiz(quiz);
            for (NotaQuizz nota:notas){
                String texto = nota.getAlumno();
                listaAlumnos.getItems().add(texto);
            }
            listaAlumnos.setVisible(true);
        listaQuizzes.setVisible(false);
        textTitulo.setText("ESTUDIANTES QUE HAN RESPONDIDO AL QUIZ SELECCIONADO");
        texto.setText("Seleccione el estudiante que quera ver sus respuestas");
        botonAlumnos.setVisible(false);
        botonVolverAQuizzes.setVisible(true);
        botonClonarQuiz.setVisible(false);
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
            con.insertarQuiz(nombre, curso, preguntas);
            cargarQuizzesDelCurso();
        }
    }
    
}
