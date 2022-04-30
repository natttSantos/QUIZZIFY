/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.NotaQuizz;
import LogicaNegocio.modelo.PreguntaRespondida;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author crivi
 */
public class MostrarRespuestasController implements Initializable {

    @FXML
    private Label instructor;
    private Curso cursoSeleccionado;
    private QuizAbstracto quizSeleccionado;
    private UsuarioInstructor instructorConectado;
    private UsuarioAlumno alumnoSeleccionado;
    private Conexion con;
    private ArrayList<QuizAbstracto> quizzes;
    @FXML
    private TableView<PreguntaRespondida> tablaNotas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Conexion.obtenerConexion();
        cargarNotas();
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
    
    public void setAlumnnoSeleccionado(UsuarioAlumno alumno){
        this.alumnoSeleccionado = alumno;
    }
    
    public void cargarNotas(){
        TableColumn<PreguntaRespondida, String> col = new TableColumn<>("Pregunta");
        TableColumn<PreguntaRespondida, String> col1 = new TableColumn<>("Respuesta");
        tablaNotas.getColumns().addAll(col,col1);
        NotaQuizz nota = con.obtenerRespuestasDeQuizDeAlumno(alumnoSeleccionado, quizSeleccionado);
        ArrayList<PreguntaRespondida> respuestas = nota.getRespuestas();
        for (PreguntaRespondida respuesta:respuestas){
            tablaNotas.getItems().add(respuesta);
        }
    }
    
    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/GestionQuizzes2.fxml"));
        Parent root =(Parent) loader.load();      
        GestionQuizzes2Controller  quizzes = loader.<GestionQuizzes2Controller>getController();
        quizzes.setIntructorConectado(instructorConectado);
        quizzes.setCursoSeleccionado(cursoSeleccionado);
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();  
        ((Node) event.getSource()).getScene().getWindow().hide();
    }


    
}
