/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.Pregunta;
import LogicaNegocio.modelo.Quiz;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nata2
 */
public class SesionEstudianteController implements Initializable {

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
        ArrayList<Quiz> quizzes = con.obtenerTodosQuizzes();
        for (Quiz quiz:quizzes ){
            listaQuizes.getItems().add(quiz.getNombre());
        }
    }

    @FXML
    private void entrarQuiz(ActionEvent event) {
        String selectedItem = listaQuizes.getSelectionModel().getSelectedItem();
        //TODO este boton lleva a una nueva ventana en la que se realizara el quiz. Pasar en la conexion el nombre del quiz para
        //luego en la otra ventana ir sacando las preguntas.
    }
    

    
}
