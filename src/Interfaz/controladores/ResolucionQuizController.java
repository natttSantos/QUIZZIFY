package Interfaz.controladores;

import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.RespuestaAbstracta;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResolucionQuizController implements Initializable {
    private Conexion con;
    private String nombreQuiz; 
    private ArrayList <PreguntaAbstracta> preguntas; 
    private int indexPregunta; 
    private int [] arrayRespuestasUsuario = new int [50]; 
    private int [] arrayRespuestasCorrectas = new int[50]; 
    @FXML
    private Label instructor;
    @FXML
    private RadioButton respuesta1;
    @FXML
    private AnchorPane contenedor;
    @FXML
    private Label pregunta;
    @FXML
    private RadioButton respuesta2;
    @FXML
    private RadioButton respuesta3;
    @FXML
    private RadioButton respuesta4;
    @FXML
    private Label fraccionPregunta;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Conexion.obtenerConexion();
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

    @FXML
    private void pulsarContinuar(ActionEvent event) throws IOException {
        guardarRespuestasUsuario();
        indexPregunta++; 
        if(indexPregunta < preguntas.size()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/ResolucionQuiz.fxml"));
            Parent root =(Parent) loader.load();      
            ResolucionQuizController resolucionQuiz = loader.<ResolucionQuizController>getController(); 
            resolucionQuiz.setPreguntas(preguntas);
            resolucionQuiz.setIndexPregunta(indexPregunta); 
            resolucionQuiz.cargarPreguntasEnQuiz();
            Scene scene = new Scene (root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); 
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
            } else{
                compararResultados();
            }
    } 
      public void addEnunciadoPregunta(String enunciadoPregunta) {
        pregunta.setText(enunciadoPregunta);
       }
  
      public void addEnunciadoRespuesta(String enunciadoRespuesta,int index){
          switch(index){
             case 1: 
                  respuesta1.setText(enunciadoRespuesta);
                  break; 
             case 2: 
                 respuesta2.setText(enunciadoRespuesta);
                 break; 
             case 3: 
                 respuesta3.setText(enunciadoRespuesta);
                 break; 
             case 4: 
                 respuesta4.setText(enunciadoRespuesta);
                 break; 
          }
      }

    public void setNombreQuiz(String nombreQuiz) {
        this.nombreQuiz = nombreQuiz;
    }

    public void setPreguntas(ArrayList preguntas) {
        this.preguntas = preguntas;
    }
    
    public void cargarPreguntasEnQuiz(){
        
        System.out.println("cargarPregQuiz" + preguntas.toString());
       
        System.out.println("INDEX X" + indexPregunta);
        fraccionNumeroPreguntas();
        System.out.println("PREGUNTA X" + preguntas.get(indexPregunta));
        String json = ""+preguntas.get(indexPregunta);  
        PreguntaAbstracta preg = new Gson().fromJson(json, PreguntaSeleccionMultiple.class);
        System.out.println("preg X" + preg.toString());
        
       
        String enunciadoPregunta = pregunta.getText();
        System.out.println("eNUNCIOADO X" + enunciadoPregunta);
        
        PreguntaAbstracta pregunta = con.obtenerPregunta("text", enunciadoPregunta);
        ArrayList respuestas = pregunta.getRespuestas(); 
        addEnunciadoPregunta(enunciadoPregunta);  
        
        /*for(int indexRespuesta = 1; indexRespuesta <= respuestas.size(); indexRespuesta++){
            
            RespuestaAbstracta resp = (RespuestaAbstracta)respuestas.get(indexRespuesta - 1);
             
             String enunciadoRespuesta = resp.obtenerDescricpion();
             boolean respuestaCorrecta = resp.esCorrecta(indexRespuesta - 1); 
             
             if(respuestaCorrecta == true){
                 respuestasCorrectas(indexRespuesta);
             }
             addEnunciadoRespuesta(enunciadoRespuesta, indexRespuesta);
       }*/
       
    }

    public void setIndexPregunta(int indexPregunta) {
        this.indexPregunta = indexPregunta;
    }

    public int getIndexPregunta() {
        return indexPregunta;
    }

    public void fraccionNumeroPreguntas(){
        int numeroTotalPreguntas = preguntas.size(); 
        int numeroPregunta = indexPregunta + 1; 
        fraccionPregunta.setText("Pregunta " + numeroPregunta + "/" + numeroTotalPreguntas);
    }
   
    public void respuestasDeUsuario(int respuestasUsuario){
         arrayRespuestasUsuario[indexPregunta] = respuestasUsuario; 
    }

    public void respuestasCorrectas(int indexRespuesta){
        arrayRespuestasCorrectas[indexPregunta] = indexRespuesta; 
    }
    public void compararResultados(){
        int numeroTotalPreguntas = preguntas.size();  
        int nota = numeroTotalPreguntas; 
        String notafraccion; 
        for(int i = 0; i < numeroTotalPreguntas; i++){
               if(arrayRespuestasCorrectas[i] != arrayRespuestasUsuario[i]){
                   nota--; 
               } 
        }
        notafraccion = nota + "/" + numeroTotalPreguntas; 
        mostrarAlerta(nota, notafraccion);
    }
    
    public void guardarRespuestasUsuario(){
        int respuestaUsuario = 0; 
        if(respuesta1.isSelected()){
            respuestaUsuario = 1; 
        }if(respuesta2.isSelected()){
            respuestaUsuario = 2; 
        }if(respuesta3.isSelected()){
            respuestaUsuario = 3; 
        }if(respuesta4.isSelected()){
            respuestaUsuario = 4; 
        }
        respuestasDeUsuario(respuestaUsuario); 
    }
    
    public void mostrarAlerta(double nota, String notafraccion){
        String header; 
        if(nota >= 5 ){ 
            header = "ENHORABUENA"; 
        } else { 
            header = "LO SIENTO"; 
        }
        Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION); 
        dialogoAlerta.setTitle(null);
        dialogoAlerta.setHeaderText(header);
        dialogoAlerta.setContentText("Tu nota es " + notafraccion);
        java.awt.Toolkit.getDefaultToolkit().beep();
        dialogoAlerta.showAndWait(); 
    }
}
