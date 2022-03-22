/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.Pregunta;
import LogicaNegocio.modelo.Quiz;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import Persistencia.controladores.ControladorPreguntas;
import Persistencia.controladores.ControladorQuizzes;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    @FXML
    private ListView<String> listView2;
    @FXML
    private Button añadirAExamenButton1;
    
    private UsuarioInstructor instructor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Conexion.obtenerConexion();
        cargarLista();
        //estadoGlobal.obtenerUusarioInstructor(); arquitectura
    }    


    public void setUsuario(UsuarioInstructor i){
        this.instructor = i;
    }
    
    @FXML
    private void crearPreguntaButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/CrearPreguntaController.fxml"));
        Parent root = loader.load();
        CrearPreguntaController controlador = loader.getController();
        Scene scene = new Scene(root, 400, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setTitle("Crear pregunta");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void aceptarButtonClicked(ActionEvent event) {
        ObservableList<String> lista = listView2.getItems();
        Document[] preguntas = new Document[lista.size()];
        int i = 0;
        for (String text:lista){
            
            Pregunta pregunta = con.obtenerPregunta("text", text);
            Document d = new Document();
            d.append("text", pregunta.getText())
                .append("dificultad", pregunta.getDificultad())
                .append("tema", pregunta.getTema()) 
                .append("respuestas", asList(pregunta.getRespuestas()));
            preguntas[i] = d;
            i++;            
        }
        try {
            if(!nombreTextField.getText().equals("")) {
                con.insertarQuiz(nombreTextField.getText(), preguntas);
                enviarAlerta("Creado","Quizz creado correctamente!");
                //System.out.println(con.reducirCantQuizzesDisponibles(instructor.getEmail()));
            ((Node) event.getSource()).getScene().getWindow().hide();
            } else {
                enviarAlerta("ERROR","Escriba un texto descriptivo para  crear el Quizz!");
            }
            
        }catch(Exception e){
            enviarAlerta("ERROR","Ha ocurrido un error en la creación del Quizz! : "+ e.getMessage() );
        }
       
    }

    @FXML
    private void anularButtonClickedTest(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void añadirAExamenButtonClicked(ActionEvent event) {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            listView2.getItems().add(selectedItem);
        }else {
            enviarAlerta("ERROR", "Debe seleccionar una pregunta para añadirla al examen!");
        }
       
    }
    
    public void cargarLista(){
        ArrayList<Pregunta> preguntas = con.obtenerTodasPreguntas();
        for (Pregunta pregunta:preguntas ){
            listView.getItems().add(pregunta.getText());
        }
    }
    
      private void enviarAlerta(String header,String text) {
        Alert dialogoAlerta;
        if(header.equals("ERROR")){
           dialogoAlerta = new Alert(Alert.AlertType.ERROR); 
        }else {
            dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION); 
        }
        dialogoAlerta.setTitle(null);
        dialogoAlerta.setHeaderText(header);
        dialogoAlerta.setContentText(text);
        java.awt.Toolkit.getDefaultToolkit().beep();
        dialogoAlerta.showAndWait(); 
    }
}
