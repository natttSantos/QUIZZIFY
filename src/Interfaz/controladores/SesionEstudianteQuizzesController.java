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

public class SesionEstudianteQuizzesController implements Initializable {

    private Stage stage = new Stage(); 
    private TilePane tilePane = new TilePane(); 
    private String usuario;
    
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
        PreguntaAbstracta preg1 = cargarPrimeraPregunta(quiz); 
        
        Gson gson = new Gson(); 
        String json =  gson.toJson(preg1);
        FXMLLoader loader = null; 
        Parent root = null; 
        
        switch(preg1.getTipo()){
            case "multiple": 
                loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/ResolucionPreguntaMultiple.fxml"));
                root =(Parent) loader.load(); 
                ResolucionPreguntaMultipleController resolucionMultiple = loader.<ResolucionPreguntaMultipleController>getController();
                resolucionMultiple.setUsuario(estudianteConectado.getEmail());
                resolucionMultiple.setNombreQuiz(nombrequizSeleccionado);
                resolucionMultiple.setPreguntas(quiz.getPreguntas());
                resolucionMultiple.setIndexPregunta(0);
                resolucionMultiple.cargarPreguntasEnQuiz();
                break; 
                
            case "vf": 
                loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/ResolucionPreguntaVF.fxml"));
                root =(Parent) loader.load(); 
                ResolucionPreguntaVFController resolucionVF = loader.<ResolucionPreguntaVFController>getController();
                break; 
        
        }
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
    public PreguntaAbstracta cargarPrimeraPregunta (QuizAbstracto quiz){    
        Gson gson = new Gson(); 
        
        String jsonQuiz = gson.toJson(quiz); 
        QuizAbstracto quizSelected = new Gson().fromJson(jsonQuiz, QuizAbstracto.class);
        
        ArrayList <PreguntaAbstracta> preguntasQuizSelected = quizSelected.getPreguntas();
        String enunaciadoPreg1 = preguntasQuizSelected.get(0).getText(); 
        PreguntaAbstracta preg1 = con.obtenerPreguntaSegunTipo(enunaciadoPreg1); 
        return preg1;
    }
    
    public void setEstudianteConectado(UsuarioAlumno estudianteConectado) {
        this.estudianteConectado = estudianteConectado;
    }

    public void setNombreCursoSelected(String nombreCursoSelected) {
        this.nombreCursoSelected = nombreCursoSelected; //Nombre curso selected
        
    }

}
