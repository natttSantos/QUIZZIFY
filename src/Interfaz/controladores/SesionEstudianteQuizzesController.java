package Interfaz.controladores;

import static Interfaz.controladores.InicioSesionController.u;
import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.PreguntaVF;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.RespuestaAbstracta;
import LogicaNegocio.modelo.RespuestaSeleccion;
import LogicaNegocio.modelo.UsuarioAlumno;
import Persistencia.conexion.Conexion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.lang.reflect.Type;

public class SesionEstudianteQuizzesController implements Initializable {

    private String usuario;
    private ArrayList<PreguntaAbstracta> preguntas;
    
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
    private UsuarioAlumno estudianteConectado; 
    
    private String nombreCursoSelected; 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    con = Conexion.obtenerConexion(); 
    preguntas = con.obtenerTodasPreguntas();
    }    
    
    public void setUsuario(String usuario){
        this.usuario = usuario; 
    }
    
    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionEstudiante.fxml"));
        Parent root =(Parent) loader.load();
        SesionEstudianteController inicio = loader.<SesionEstudianteController>getController(); 
        inicio.setEstudianteConectado(u);
        inicio.cargarListaCursos();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.setResizable(false);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
     public void cargarListaQuizzes(){
        Curso cursoSelected = con.obtenerCurso("nombreCurso", nombreCursoSelected); 
        ArrayList<QuizAbstracto> quizzesCurso = con.obtenerQuizzesDeCurso(cursoSelected);
        if(quizzesCurso.isEmpty()){
            imagenReloj.setVisible(true);
            textoEspera.setVisible(true);
            listaQuizes.setVisible(false);
        }
        else{
            imagenReloj.setVisible(false);
            textoEspera.setVisible(false);
            listaQuizes.setVisible(true);
            listaQuizes.setDisable(false);
        }  
 
        for (QuizAbstracto quiz: quizzesCurso ){
            listaQuizes.getItems().add(quiz.getNombre());
        }
    }

    @FXML
    public void entrarQuiz(ActionEvent event) throws IOException {
        String nombrequizSeleccionado = listaQuizes.getSelectionModel().getSelectedItem();
        QuizAbstracto quiz = con.obtenerQuiz("nombre", nombrequizSeleccionado); 
        ArrayList <PreguntaSeleccionMultiple> preguntasMultiples = con.obtenerPreguntasQuiz_Multiples(quiz); 
        ArrayList <PreguntaVF> preguntasVF = con.obtenerPreguntasQuiz_VF(quiz); 
        
        FXMLLoader loader = null; 
        Parent root = null; 
        
        
                loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/ResolucionQuiz.fxml"));
                root =(Parent) loader.load(); 
                ResolucionQuizController resolucion = loader.<ResolucionQuizController>getController();
                resolucion.setNombreCursoSelected(nombreCursoSelected);
                
                
                resolucion.setNombreQuiz(nombrequizSeleccionado);
                resolucion.setPreguntasMultiple(preguntasMultiples);
                resolucion.setPreguntasVF(preguntasVF);
                resolucion.setNumeroPreguntas(preguntasMultiples.size() + preguntasVF.size());
                resolucion.validarTipoPregunta();
                
       
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.setResizable(false);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
   
    
    public void setEstudianteConectado(UsuarioAlumno estudianteConectado) {
        this.estudianteConectado = estudianteConectado;
    }

    public void setNombreCursoSelected(String nombreCursoSelected) {
        this.nombreCursoSelected = nombreCursoSelected; //Nombre curso selected
        
    }

}
