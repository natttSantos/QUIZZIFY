package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
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
import javafx.stage.Modality;
import javafx.stage.Stage;



public class CursosController implements Initializable {
    private UsuarioInstructor instructorConectado; 
    private Curso curso; 
    @FXML
    private Label instructor;
    @FXML
    private ListView<String> listaCursos;
    @FXML
    private Label sinCursos;
    private ArrayList<Curso> listCursos = new ArrayList<Curso>(); 
    
    private Conexion con; 

    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        con = Conexion.obtenerConexion();
    }  

    public void setInstructorConectado(UsuarioInstructor instructorConectado) {
        this.instructorConectado = instructorConectado;
    }

    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionInstructor.fxml"));
        Parent root =(Parent) loader.load();      
        SesionInstructorController sesionInstructor = loader.<SesionInstructorController>getController();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        ((Node) event.getSource()).getScene().getWindow().hide();
        stage.show();  
        
    }

    @FXML
    private void pulsarCrearCurso(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/CrearCurso.fxml"));
        Parent root = miCargador.load();
        CrearCursoController cursos = miCargador.getController();
        cursos.setInstructorUser(instructorConectado);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void pulsarAbrirCurso(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/GestionQuizzes.fxml"));
        Parent root = miCargador.load();
        GestionQuizzesController  quizzes= miCargador.getController();
        quizzes.setIntructorConectado(instructorConectado);
        String nombreCurso = listaCursos.getSelectionModel().getSelectedItem();
        if (nombreCurso != null) {
            Curso curso = con.obtenerCurso("nombreCurso", nombreCurso);
            quizzes.setCursoSeleccionado(curso);
            quizzes.cargarQuizzesDelCurso();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

    public void cargarCursosDeInstructor(){
       ArrayList <Curso> cursosInstructor = con.obtenerCursosDeInstructor(instructorConectado); 
       for(int i = 0; i < cursosInstructor.size() && cursosInstructor.size() > 0; i++){
           Curso curso = cursosInstructor.get(i); 
           sinCursos.setVisible(false);
           listaCursos.getItems().add(curso.getNombreCurso());
       }
    }

    public void setListaCursos(ArrayList<Curso> listCursos) {
        this.listCursos = listCursos;
    }

    @FXML
    private void pulsarClonarCurso(ActionEvent event) {
        String nombreCurso = listaCursos.getSelectionModel().getSelectedItem();
        if(nombreCurso != null) {
            Curso curso =con.obtenerCurso("nombreCurso", nombreCurso);
            Curso aux = curso;
            aux.setNombreCurso("Copia de " + curso.getNombreCurso());
            con.insertarCurso(aux);
            cargarCursosDeInstructor();
        }
    }

   
    

}
