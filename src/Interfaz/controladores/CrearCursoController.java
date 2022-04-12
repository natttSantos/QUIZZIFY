package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.Usuario;
import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.Spring;
import org.bson.Document;



public class CrearCursoController implements Initializable {
    private UsuarioInstructor instructorUser; 
    @FXML
    private Label instructor;
    @FXML
    private MenuButton menuEstudiantes;
    @FXML
    private ListView<String> listaEstudiantes;

    Conexion c = Conexion.obtenerConexion();
    @FXML
    private TextField nombreCurso;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addEstudiantesToMenu(); 
       
    }  

    public void setInstructorUser(UsuarioInstructor instructorUser) {
        this.instructorUser = instructorUser;
    }

    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
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
    private void pulsarCrearCurso(ActionEvent event) {
        String nombre = nombreCurso.getText(); 
        Document[] estudiantesEnCurso = cargarUsuariosEnCurso(); 
        c.crearCursosSinQuiz(nombre, estudiantesEnCurso, c.crearUsuarioInstructor(instructorUser)); 
    }

    @FXML
    private void pulsarAnyadirEstudiante(ActionEvent event) {
        String emailSelected = menuEstudiantes.getText(); 
        listaEstudiantes.getItems().add(emailSelected);
    }

    public void addEstudiantesToMenu(){
        ArrayList <UsuarioAlumno> estudiantes = c.obtenerTodosUsuariosAlumno(); 
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                menuEstudiantes.setText(((MenuItem)e.getSource()).getText()); 
            }
        };
        for(int i = 0; i < estudiantes.size(); i++){
            UsuarioAlumno alum = estudiantes.get(i); 
            String email_alum = alum.getEmail(); 
            MenuItem menuItem = new MenuItem(email_alum);
            menuEstudiantes.getItems().add(menuItem);
            menuItem.setOnAction(event);
        }
    }
    public Document[] cargarUsuariosEnCurso(){
         ObservableList <String> listaEmails_EnCurso = listaEstudiantes.getItems(); 
         ArrayList<UsuarioAlumno> estudiantesEnCurso = new ArrayList(); 
       
        int i = 0;
        for (String email: listaEmails_EnCurso){ 
            UsuarioAlumno user = c.obtenerUsuarioAlumno("email", email); 
            estudiantesEnCurso.add(user); 
            i++; 
       }
        Document [] estudiantes = crearDocumentCon_estudiantes(estudiantesEnCurso); 
        return estudiantes; 
    
    }
    public Document[] crearDocumentCon_estudiantes (ArrayList<UsuarioAlumno> estudiantesEnCurso ){
        Document[] estudiantes = new Document[estudiantesEnCurso.size()];
        int i = 0; 
        for(UsuarioAlumno u: estudiantesEnCurso){
            Document d = c.crearUsuarioAlumno(u); 
            estudiantes[i] = d; 
            i++;    
    }
        return estudiantes; 
    }

    
   
   
    
}
