/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.QuizAbstracto;
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
import java.util.ArrayList;

/**
 * FXML Controller class
 *
 * @author crivi
 */
public class MostrarRespuestasController implements Initializable {

    @FXML
    private Label instructor;
    @FXML
    private Button aceptarButton;
    @FXML
    private ListView<String> listView;
    @FXML
    private ListView<?> listView2;
    
    private Conexion con;
    private ArrayList<QuizAbstracto> quizzes;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Conexion.obtenerConexion();
        quizzes = con.obtenerTodosQuizzes();
        for (QuizAbstracto quiz:quizzes){
            listView.getItems().add(quiz.getNombre());
        }
    }    

    @FXML
    private void pulsarAtras(ActionEvent event) {
    }

    @FXML
    private void aceptarButtonClicked(ActionEvent event) {
    }

    
}
