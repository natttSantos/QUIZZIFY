package Interfaz.controladores;

import LogicaNegocio.modelo.OpcionRespuestaSeleccion;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.RespuestaAbstracta;
import LogicaNegocio.modelo.RespuestaSeleccion;
import LogicaNegocio.modelo.NotaQuizz;
import Persistencia.conexion.Conexion;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import org.bson.Document;

public class ResolucionQuizController implements Initializable {
    private Conexion con;
    private String nombreQuiz; 
    private ArrayList <PreguntaAbstracta> preguntas; 
    private int indexPregunta; 
    private int [] arrayRespuestasUsuario = new int [50]; 
    private int [] arrayRespuestasCorrectas = new int[50]; 
    private String usuario;
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
    
    public void setUsuario(String usuario){
        this.usuario = usuario; 
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

    public void setPreguntas(ArrayList<PreguntaAbstracta> preguntas) {
        this.preguntas = preguntas;
    }
    
    public void cargarPreguntasEnQuiz(){
        fraccionNumeroPreguntas();
        final Gson gson = new Gson();
	String representacionJSON_pregunta = gson.toJson(preguntas.get(indexPregunta)); 
        PreguntaAbstracta preg = new Gson().fromJson(representacionJSON_pregunta, PreguntaSeleccionMultiple.class);

        String enunciadoPregunta = preg.getText();
        ArrayList respuestas = preg.getRespuestas(); 
        addEnunciadoPregunta(enunciadoPregunta);  

        for(int indexRespuesta = 1; indexRespuesta <= respuestas.size(); indexRespuesta++){
            String representacionJSON_respuesta =  gson.toJson(respuestas.get(indexRespuesta - 1));
            RespuestaAbstracta resp = new Gson().fromJson(representacionJSON_respuesta, RespuestaSeleccion.class);
            OpcionRespuestaSeleccion opcion = new Gson().fromJson(representacionJSON_respuesta, OpcionRespuestaSeleccion.class);
            
            String enunciadoRespuesta = resp.obtenerText(); 
            opcion.isCorrecta(); 
            boolean respuestaCorrecta = opcion.isCorrecta(); 
             
            if(respuestaCorrecta == true){
                 respuestasCorrectas(indexRespuesta);
             }
             addEnunciadoRespuesta(enunciadoRespuesta, indexRespuesta);
       }
       
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
        subirRespuestas(nota);
        notafraccion = nota + "/" + numeroTotalPreguntas; 
        mostrarAlerta(nota, notafraccion);
    }
    
    public void subirRespuestas(int nota){
        con.subirNotaQuiz(nombreQuiz, nota, usuario);
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
