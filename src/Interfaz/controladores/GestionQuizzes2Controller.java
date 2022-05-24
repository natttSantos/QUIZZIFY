/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import Interfaz.tablas.PreguntaAciertoFallo;
import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.NotaQuizz;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaRespondida;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.PreguntaVF;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.Respuesta;
import LogicaNegocio.modelo.RespuestaSeleccion;
import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import com.sun.glass.events.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ListView<String> listaPreguntas;
    @FXML
    private Label texto1;
    @FXML
    private Button botonMostrarRespuestas;
    @FXML
    private Button botonAnularPregunta;
    @FXML
    private Button botonModificarDificultad;
    @FXML
    private TableView<PreguntaAciertoFallo> tablaPreguntas;
    @FXML
    private TableColumn<PreguntaAciertoFallo, String> colPregunta;
    @FXML
    private TableColumn<PreguntaAciertoFallo, Double> colAcierto;
    @FXML
    private TableColumn<PreguntaAciertoFallo, Double> colFallo;
    @FXML
    private Button botonModificarPuntuacion;

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
            quizzes.cargarQuizzesDelCurso();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();  
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    
    public void cargarPreguntasDelQuiz() {
        System.out.println("En ultimo" + quizSeleccionado);
        //ArrayList<PreguntaAbstracta> preguntas = quizSeleccionado.getPreguntas();
        
        colPregunta.setCellValueFactory(new PropertyValueFactory("pregunta"));
        colAcierto.setCellValueFactory(new PropertyValueFactory("acierto"));
        colFallo.setCellValueFactory(new PropertyValueFactory("fallo"));
        ObservableList<PreguntaAciertoFallo> datos = FXCollections.observableArrayList();
        ArrayList <PreguntaSeleccionMultiple> preguntasMul = con.obtenerPreguntasQuiz_Multiples(quizSeleccionado); 
        ArrayList <PreguntaVF> pregutasVF = con.obtenerPreguntasQuiz_VF(quizSeleccionado); 
        ArrayList <NotaQuizz> notas = con.obtenerNotasDeQuiz(quizSeleccionado);
        int total = notas.size();
        for (PreguntaAbstracta pregunta:preguntasMul){
            ArrayList <Respuesta> listaAux = pregunta.getRespuestas();
            Respuesta correcta = null;
            int contador = 0;
            for (Respuesta respuesta:listaAux) {
                if (respuesta.getEsCorrecta()){
                    correcta = respuesta;
                }
            }
            String correcto = correcta.getTexto();
            String nombre = pregunta.getText();
            for (NotaQuizz nota:notas){
                ArrayList <PreguntaRespondida> respuestas = nota.getRespuestas();
                for (PreguntaRespondida respuesta:respuestas){
                    String aux = respuesta.getRespuesta();
                    if (correcto.equals(aux)) {
                        contador++;
                    }
                    
                }
            }
            double acierto = (contador / total) * 100;
            System.out.print(acierto);
            double fallo = 100 - acierto;
            PreguntaAciertoFallo objeto = new PreguntaAciertoFallo(nombre, acierto, fallo );
            datos.add(objeto);
            listaPreguntas.getItems().add(nombre);
        }
        for (PreguntaAbstracta pregunta:pregutasVF){
            String nombre = pregunta.getText();
            listaPreguntas.getItems().add(nombre);
        }
        
        tablaPreguntas.setItems(datos);
    }
    
    public void alumnosDelQuiz(){
        ArrayList<NotaQuizz> notas = con.obtenerNotasDeQuiz(quizSeleccionado);
        for (NotaQuizz nota:notas){
            String texto = nota.getAlumno();
            listaAlumnos.getItems().add(texto);
        }
    }

    @FXML
    private void pulsarMostrarRespuestas(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/MostrarRespuestas.fxml"));
        Parent root = miCargador.load();
        MostrarRespuestasController respuestas = miCargador.getController();
        String nombreAlumno = listaAlumnos.getSelectionModel().getSelectedItem();
        System.out.println("nom" + nombreAlumno);
        if (nombreAlumno != null) {
            UsuarioAlumno alumno = con.obtenerUsuarioAlumno("email", nombreAlumno);
            System.out.println("de bd" + alumno);
            respuestas.setAlumnnoSeleccionado(alumno);
            respuestas.setCursoSeleccionado(cursoSeleccionado);
            respuestas.setIntructorConectado(instructorConectado);
            respuestas.setQuizSeleccionado(quizSeleccionado);
            respuestas.cargarNotas();
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
    private void pulsarAnularPregunta(ActionEvent event) {
        String tituloPregunta = listaPreguntas.getSelectionModel().getSelectedItem();
        if (tituloPregunta != null){
            PreguntaAbstracta pregunta = con.obtenerPreguntaSegunTipo( tituloPregunta);
            ObservableList<String> lista = listaPreguntas.getItems();
            lista.remove(pregunta.getText());
            Document[] preguntas = new Document[lista.size()];
            int i = 0;
            for (String text:lista){
                System.out.println("item " + text);
                PreguntaAbstracta pregAux = con.obtenerPreguntaSegunTipo(text);
                Document d = pregAux.obtenerDocument();
                preguntas[i] = d;
                i++;
        } 
            con.anularPregunta(quizSeleccionado, preguntas);    
            ArrayList<NotaQuizz> notasDelQuiz = con.obtenerNotasDeQuiz(quizSeleccionado);
            for (int y = 0; y < notasDelQuiz.size(); y++) {
                NotaQuizz nota = notasDelQuiz.get(y);
                ArrayList<PreguntaRespondida> respuestas = nota.getRespuestas();
                ArrayList<PreguntaRespondida> respuestasModificadas = new ArrayList();
                for (PreguntaRespondida respuesta:respuestas){
                    if (respuesta.getPregunta().equals(tituloPregunta)){}
                    else respuestasModificadas.add(respuesta);
                }                
                NotaQuizz nuevaNota = new NotaQuizz(quizSeleccionado.getNombre(), nota.getAlumno(), nota.getNota(), respuestasModificadas);
                con.modificarNota(nuevaNota);
            }
        }
    }

    @FXML
    private void modificarDificultad(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/ModificarDificultad.fxml"));
        Parent root = miCargador.load();
        ModificarDificultadController dificultad = miCargador.getController();
        String tituloPregunta = listaPreguntas.getSelectionModel().getSelectedItem();
        if (tituloPregunta != null){
            PreguntaAbstracta pregunta = con.obtenerPreguntaSegunTipo(tituloPregunta);
            System.out.print(pregunta);
            dificultad.setCursoSeleccionado(cursoSeleccionado);
            dificultad.setIntructorConectado(instructorConectado);
            dificultad.setQuizSeleccionado(quizSeleccionado);
            dificultad.setPreguntaSeleccionado(pregunta);
            dificultad.cargarPregunta(tituloPregunta, pregunta.getDificultad());
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
    private void pulsarModificarPuntuacion(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/ModificarPuntuacion.fxml"));
        Parent root = miCargador.load();
        ModificarPuntuacionController dificultad = miCargador.getController();
        String tituloPregunta = listaPreguntas.getSelectionModel().getSelectedItem();
        if (tituloPregunta != null){
            PreguntaAbstracta pregunta = con.obtenerPreguntaSegunTipo(tituloPregunta);
            System.out.print(pregunta);
            dificultad.setCursoSeleccionado(cursoSeleccionado);
            dificultad.setIntructorConectado(instructorConectado);
            dificultad.setQuizSeleccionado(quizSeleccionado);
            dificultad.setPreguntaSeleccionado(pregunta);
            dificultad.cargarPregunta(tituloPregunta, pregunta.getPuntos());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }
}
