package Interfaz.controladores;

import LogicaNegocio.modelo.QuizAbstracto;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SesionEstudianteController implements Initializable {

    private Stage stage = new Stage(); 
    private TilePane tilePane = new TilePane(); 
    
    @FXML
    private Label instructor;
    @FXML
    private Label textoEspera;
    @FXML
    private ImageView imagenReloj;
    @FXML
    private ListView<String> listaQuizes;
    private Conexion con;
    @FXML
    private Button realizarQuizBoton;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    con = Conexion.obtenerConexion();    
    if(con.obtenerTodosQuizzes().isEmpty()){
        imagenReloj.setVisible(true);
        textoEspera.setVisible(true);
        listaQuizes.setVisible(false);
    }
    else{
        cargarLista();
        imagenReloj.setVisible(false);
        textoEspera.setVisible(false);
        listaQuizes.setVisible(true);
        listaQuizes.setDisable(false);
    
        }    
    }    

    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/InicioSesion.fxml"));
        Parent root =(Parent) loader.load();      
        InicioSesionController inicio = loader.<InicioSesionController>getController();
        inicio.setTipoUsuario("Estudiante");
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
     public void cargarLista(){
        ArrayList<QuizAbstracto> quizzes = con.obtenerTodosQuizzes();
        for (QuizAbstracto quiz:quizzes ){
            listaQuizes.getItems().add(quiz.getNombre());
        }
    }

    @FXML
    private void entrarQuiz(ActionEvent event) throws IOException {
        String nombrequizSeleccionado = listaQuizes.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/ResolucionQuiz.fxml"));
        Parent root =(Parent) loader.load();      
        ResolucionQuizController resolucionQuiz = loader.<ResolucionQuizController>getController();
        resolucionQuiz.setNombreQuiz(nombrequizSeleccionado);
        resolucionQuiz.setPreguntas(cargarPreguntasQuiz());
        resolucionQuiz.setIndexPregunta(0);
        resolucionQuiz.cargarPreguntasEnQuiz();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
    public ArrayList cargarPreguntasQuiz(){
        String nombrequizSeleccionado = listaQuizes.getSelectionModel().getSelectedItem();
        QuizAbstracto quizSeleccionado = con.obtenerQuiz("nombre", nombrequizSeleccionado);
        ArrayList preguntas = quizSeleccionado.getPreguntas();
        System.out.println("PREGUNTAS " + preguntas.toString());
        return preguntas;
    }

}
