package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nata2
 */
public class SesionInstructorController implements Initializable {

    private String nombreUsuario; 
    @FXML
    private Label instructor;
    
    private Conexion conexion;
    @FXML
    private Label quizzesDisponibles;
    @FXML
    private Button buttonQuiz;
    @FXML
    private Button buttonQuizAleatorio;
    
    
    private UsuarioInstructor i;
    @FXML
    private Button buttonPreguntaMultiple;
    @FXML
    private Button buttonPreguntaVF;
    @FXML
    private Button buttonPreguntaAbierta;
    @FXML
    private MenuItem misCursos;
    @FXML
    private MenuBar myMenuBar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = Conexion.obtenerConexion();   
    }   

    public void setUsuario(UsuarioInstructor i) {
        this.i = i;
        instructor.setText(i.getNombre() + " " + i.getApellidos());
        quizzesDisponibles.setText(i.getQuizzesDisponibles()+"");
        if(i.getQuizzesDisponibles()== 0){
            buttonQuiz.setDisable(true);
            buttonQuizAleatorio.setDisable(true);
        }   
    }

    
    
    public void setConexion(Conexion con) {
        conexion = con;
    }

    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/InicioSesion.fxml"));
        Parent root =(Parent) loader.load();      
        InicioSesionController inicio = loader.<InicioSesionController>getController();
        inicio.setTipoUsuario("Instructor");
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void quizAleatorioPulsar(ActionEvent event) throws IOException {
        
        ArrayList<PreguntaSeleccionMultiple> listaPreguntas = conexion.obtenerTodasPreguntas();
        
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/DatosCrearAleatorio.fxml"));
        Parent root = miCargador.load();
        
        DatosCrearAleatorioController controlador = miCargador.getController();
       
        controlador.setUsuario(i);
        controlador.setListaPreguntas(listaPreguntas);
        controlador.addCursosToMenu();
        controlador.setConexion(conexion);
                
        Scene scene = new Scene(root, 400, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Crear un quiz aleatorio");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();  
    }

    private void crearPreguntaPulsar(ActionEvent event) throws IOException{
    
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/CrearPregunta.fxml"));
        Parent root = miCargador.load();
        CrearPreguntaController controlador = miCargador.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setResizable(false);
        stage.setTitle("Crear un quiz aleatorio");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void crearQuiz(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/GenerarQuizNoAleatorio.fxml"));
        Parent root = loader.load();
        GenerarQuizNoAleatorioController controlador = loader.getController();
        controlador.setUsuario(i);
        controlador.addCursosToMenu();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Crear un quiz");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void pulsarCrear(ActionEvent event) {
    }

    @FXML
    private void pulsarGestionQuiz(ActionEvent event) {
    }

    @FXML
    private void pulsarVerRespuestas(ActionEvent event) {
    }

    @FXML
    private void pulsarCursos(ActionEvent event) throws IOException {
        ArrayList<Curso> listCursos = conexion.obtenerTodosLosCursos();
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/Cursos.fxml"));
        Parent root = miCargador.load();
        CursosController cursos = miCargador.getController();
        
        cursos.setListaCursos(listCursos);
        cursos.setInstructorConectado(i);
        cursos.cargarCursosDeInstructor();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Mis cursos");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        
    }

    @FXML
    private void PreguntaMultipleClicked(ActionEvent event) throws IOException {
        
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/CrearPreguntaMultiple.fxml"));
        Parent root = miCargador.load();
        CrearPreguntaMultipleController controlador = miCargador.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setTitle("Crear pregunta mútiple");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
        
    }

    @FXML
    private void PreguntaVFClicked(ActionEvent event) throws IOException {
        
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/CrearPreguntaVF.fxml"));
        Parent root = miCargador.load();
        CrearPreguntaVFController controlador = miCargador.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setTitle("Crear pregunta verdadero/falso");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void PreguntaAbiertaClicked(ActionEvent event) throws IOException {
        
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/CrearPreguntaAbierta.fxml"));
        Parent root = miCargador.load();
        CrearPreguntaAbiertaController controlador = miCargador.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setTitle("Crear pregunta mútiple");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}    
