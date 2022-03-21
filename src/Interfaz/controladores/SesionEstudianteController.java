/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.Pregunta;
import LogicaNegocio.modelo.Quiz;
import LogicaNegocio.modelo.Respuesta;
import Persistencia.conexion.Conexion;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author nata2
 */
public class SesionEstudianteController implements Initializable {

    private Stage stage = new Stage(); 
    private TilePane tilePane = new TilePane(); 
    
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
        imagenReloj.setVisible(true);
        textoEspera.setVisible(true);
        listaQuizes.setVisible(false);
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
    private void entrarQuiz(ActionEvent event) throws IOException {
        String nombrequizSeleccionado = listaQuizes.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/ResolucionQuiz.fxml"));
        Parent root =(Parent) loader.load();      
        ResolucionQuizController resolucionQuiz = loader.<ResolucionQuizController>getController();
        resolucionQuiz.setNombreQuiz(nombrequizSeleccionado);
        resolucionQuiz.setPreguntas(cargarPreguntasQuiz());
        resolucionQuiz.setIndexPregunta(0);
        resolucionQuiz.cargarPreguntasEnQuiz();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
    public Document[] cargarPreguntasQuiz(){
        String nombrequizSeleccionado = listaQuizes.getSelectionModel().getSelectedItem();
        Quiz quizSeleccionado = con.obtenerQuiz("nombre", nombrequizSeleccionado);
        Document preguntas [] = quizSeleccionado.getPreguntas(); 
        Document dpregunta; 
        Document dquestion;
        return preguntas; 
    
    
    }
    
    
    
          
//     public void cargarQuiz(){ 
//        String nombrequizSeleccionado = listaQuizes.getSelectionModel().getSelectedItem();
//        Quiz quizSeleccionado = con.obtenerQuiz("nombre", nombrequizSeleccionado);
//        Document preguntas [] = quizSeleccionado.getPreguntas(); 
//        Document dpregunta; 
//        Document dquestion; 
//
//        tilePane.setHgap(10);
//        tilePane.setVgap(10);
//        tilePane.setTileAlignment(Pos.CENTER);
//        
//         
//         for(int i = 0; i < preguntas.length; i++){
//            ToggleGroup tgroup = new ToggleGroup(); 
//            dpregunta = preguntas[i];  
//            String enunciadoPregunta = dpregunta.getString("text"); 
//            Pregunta pregunta = con.obtenerPregunta("text", enunciadoPregunta);
//            Document [] respuestas = pregunta.getRespuestas(); 
//            addEnunciadoPregunta(enunciadoPregunta);  
//            for(int j = 0; j < respuestas.length; j++){
//                dquestion = respuestas[j]; 
//                String enunciadoRespuesta = dquestion.getString("text"); 
//                addEnunciadoRespuesta(enunciadoRespuesta, tgroup);
//            }
//        }
//        Button botonNext = new Button("Finalizar"); 
//        botonNext.setPrefWidth(125);
//        botonNext.setPrefHeight(40);
//        tilePane.getChildren().add(botonNext); 
//        Scene scene = new Scene(tilePane,900, 900); 
//        stage.setScene(scene);
//        stage.show();
//     }
//     
//      public void addEnunciadoPregunta(String enunciadoPregunta) {
//        Label label = new Label(enunciadoPregunta); 
//        tilePane.getChildren().add(label); 
//    }
//  
//      public void addEnunciadoRespuesta(String enunciadoRespuesta, ToggleGroup tgroup){
//         RadioButton r = new RadioButton(enunciadoRespuesta); 
//         r.setToggleGroup(tgroup);
//         tilePane.getChildren().add(r);
//      }
    
     // button.setOnAction(new EventHandler<ActionEvent>() {
//    @Override
//    public void handle(ActionEvent event) {
//        System.out.println("Hello World!");
//    }
//});

}
