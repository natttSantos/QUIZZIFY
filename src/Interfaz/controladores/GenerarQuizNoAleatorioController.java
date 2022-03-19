/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.Pregunta;
import LogicaNegocio.modelo.Quiz;
import Persistencia.conexion.Conexion;
import Persistencia.controladores.ControladorPreguntas;
import Persistencia.controladores.ControladorQuizzes;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author crivi
 */
public class GenerarQuizNoAleatorioController implements Initializable {

    @FXML
    private TextField nombreTextField;
    @FXML
    private Button crearPreguntaButton;
    @FXML
    private Button aceptarButton;
    @FXML
    private Button anularButton;     
    @FXML
    private ListView<String> listView;
    
    private Conexion con;
    private ControladorPreguntas controlador;
    private ControladorQuizzes controlador1;
    @FXML
    private ListView<String> listView2;
    @FXML
    private Button añadirAExamenButton1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Conexion.obtenerConexion();
        ArrayList<Pregunta> preguntas = controlador.obtenerTodasPreguntas();
        for (Pregunta pregunta:preguntas ){
            listView.getItems().add(pregunta.getText());
        }
    }    


    @FXML
    private void crearPreguntaButtonClicked(ActionEvent event) {
    }

    @FXML
    private void aceptarButtonClicked(ActionEvent event) {
        ObservableList<String> lista = listView2.getItems();
        Document[] preguntas = new Document[lista.size()];
        int i = 0;
        for (String text:lista){
            Pregunta pregunta = controlador.obtenerPregunta("text", text);
            Document d = new Document();
                d.append("text", pregunta.getText())
            .append("dificultad", pregunta.getDificultad())
            .append("tema", pregunta.getTema()) 
            .append("respuestas", pregunta.getRespuestas());
            preguntas[i] = d;
            i++;            
        }
        controlador1.insertarQuiz(nombreTextField.getText(), preguntas);
    }

    @FXML
    private void anularButtonClickedTest(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void añadirAExamenButtonClicked(ActionEvent event) {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        listView2.getItems().add(selectedItem);
        
    }
    
}
