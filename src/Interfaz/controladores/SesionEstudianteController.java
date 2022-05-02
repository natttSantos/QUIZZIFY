package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.UsuarioAlumno;
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

    private String usuario;
    
    @FXML
    private Label instructor;
    @FXML
    private Label textoEspera;
    @FXML
    private ImageView imagenReloj;
    private Conexion con;
    private UsuarioAlumno estudianteConectado; 
    @FXML
    private Label textoSeleccionar;
    @FXML
    private Button botonContinuar;
    @FXML
    private ListView<String> listaCursos;
    @FXML
    private Label instructor2;


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
    
    public void setEstudianteConectado(UsuarioAlumno estudianteConectado) {
        this.estudianteConectado = estudianteConectado;
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
     public void cargarListaCursos(){
        ArrayList<Curso> cursosEstudiante = con.obtenerCursosDeEstudiante(estudianteConectado);
        if(cursosEstudiante.isEmpty()){
            imagenReloj.setVisible(true);
            textoEspera.setVisible(true);
            listaCursos.setVisible(false);
            textoSeleccionar.setVisible(false);
            botonContinuar.setVisible(false);
        }
        else{
            imagenReloj.setVisible(false);
            textoEspera.setVisible(false);
            listaCursos.setVisible(true);
            textoSeleccionar.setVisible(true);
            listaCursos.setDisable(false);
        }  
 
        for (Curso curso: cursosEstudiante ){
            listaCursos.getItems().add(curso.getNombreCurso());
        }
    }

   
    public void entrarCurso (ActionEvent event) throws IOException {
        String cursoSelected = listaCursos.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionEstudianteQuizzes.fxml"));
        Parent root =(Parent) loader.load();      
        SesionEstudianteQuizzesController sesionQuizzes = loader.<SesionEstudianteQuizzesController>getController();
        sesionQuizzes.setNombreCursoSelected(cursoSelected);
        sesionQuizzes.setEstudianteConectado(estudianteConectado);
        sesionQuizzes.cargarListaQuizzes(); 
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }



    
}
