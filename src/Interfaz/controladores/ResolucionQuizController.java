package Interfaz.controladores;

import LogicaNegocio.modelo.OpcionRespuestaSeleccion;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.RespuestaAbstracta;
import LogicaNegocio.modelo.RespuestaSeleccion;
import LogicaNegocio.modelo.NotaQuizz;
import LogicaNegocio.modelo.PreguntaRespondida;
import LogicaNegocio.modelo.PreguntaVF;
import LogicaNegocio.modelo.UsuarioAlumno;
import Persistencia.conexion.Conexion;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResolucionQuizController implements Initializable {
    private Conexion con;
    private String nombreQuiz; 
    private PreguntaAbstracta nextPregunta; 
    private ArrayList <PreguntaSeleccionMultiple> preguntasMultiples; 
    private ArrayList <PreguntaVF> preguntasVF; 
    private int numeroPreguntas; 
    
    private String tipoPregunta; 
    
    private ArrayList<PreguntaRespondida> respuestas;
    private int indexPreguntaMultiple = 0; 
    private int indexPreguntaVF = 0; 
    
    private int [] arrayRespuestasUsuario = new int [50]; 
    private int [] arrayRespuestasCorrectas = new int[50];
    private int numeroPregunta;
    private PreguntaRespondida aux;
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
    @FXML
    private ToggleGroup grupoToggle;
    
    private String nombreCursoSelected; 
    private UsuarioAlumno estudianteConectado; 
    @FXML
    private Label labelTipo;
    @FXML
    private Button botonContinuar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Conexion.obtenerConexion();
        respuestas = new ArrayList();
    }    
    
    public void setUsuario(String usuario){
        this.usuario = usuario; 
    }
    
    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        navegarAtras(event);
    }

    @FXML
    private void pulsarContinuar(ActionEvent event) throws IOException {
        guardarRespuestasUsuario();
        
        indexPreguntaMultiple++; 
        if(indexPreguntaMultiple < preguntasMultiples.size()){
            navegarFormularioResolucion(event);
        } else{
                
                if(preguntasVF.size() > 0 && indexPreguntaVF < preguntasMultiples.size()){
                    tipoPregunta = "vf"; 
                    navegarFormularioResolucion(event);
                }     
        }
    } 
     
    public void navegarFormularioResolucion (ActionEvent event) throws IOException{
        FXMLLoader loader = null; 
        Parent root = null; 

            loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/ResolucionQuiz.fxml"));
            root =(Parent) loader.load(); 
            ResolucionQuizController resolucion = loader.<ResolucionQuizController>getController();
       if(tipoPregunta.equals("vf")){
            resolucion.addEnunciadoRespuestaVF();
            
        } 
        resolucion.setIndexPreguntaVF(indexPreguntaVF);
        resolucion.setIndexPreguntaMultiple(indexPreguntaMultiple);
        resolucion.setNombreCursoSelected(nombreCursoSelected);
        resolucion.setPreguntasMultiple(preguntasMultiples);
        resolucion.setPreguntasVF(preguntasVF);
        resolucion.setNumeroPreguntas(numeroPreguntas);
        resolucion.setTipoPregunta(tipoPregunta);
        resolucion.setArrayRespuestasCorrectas(arrayRespuestasCorrectas);
        resolucion.setRespuestas(respuestas);
        resolucion.validarTipoPregunta();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.setResizable(false);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
  

    
      public void addEnunciadoPregunta(String enunciadoPregunta) {
        pregunta.setText(enunciadoPregunta);
       }
  
      public void addEnunciadoRespuestaMultiple(String enunciadoRespuesta,int index){
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

       public void addEnunciadoRespuestaVF(){
           ToggleGroup group = new ToggleGroup(); 
           respuesta1.setText("Verdadero");
           respuesta2.setText("Falso");
           respuesta1.setToggleGroup(group);
           respuesta2.setToggleGroup(group);   
           
           respuesta3.setVisible(false);
           respuesta4.setVisible(false);
           labelTipo.setText("VERDADERO/FALSO");
      }
    public void setNombreQuiz(String nombreQuiz) {
        this.nombreQuiz = nombreQuiz;
    }

    public void setPreguntasMultiple(ArrayList<PreguntaSeleccionMultiple> preguntasMultiples) {
        this.preguntasMultiples = preguntasMultiples;
    }

    public void setPreguntasVF(ArrayList<PreguntaVF> preguntasVF) {
        this.preguntasVF = preguntasVF;
    }
    

    public void setNextPregunta(PreguntaAbstracta nextPregunta) {
        this.nextPregunta = nextPregunta;
    }
    
   public void validarTipoPregunta(){
        if (preguntasMultiples.size() > 0 && tipoPregunta.equals("multiple")){
            cargarPreguntasMultiples();
        } else if(preguntasVF.size() > 0 && tipoPregunta.equals("vf")){
            cargarPreguntasVF();
        }
        if(indexPreguntaMultiple + indexPreguntaVF == numeroPreguntas){
            botonContinuar.setDisable(true);
        }
    }
   
   
    public void cargarPreguntasMultiples(){ 
        fraccionNumeroPreguntas();
        final Gson gson = new Gson();
	String jsonPregunta = gson.toJson(preguntasMultiples.get(indexPreguntaMultiple)); 
        PreguntaSeleccionMultiple preg = new Gson().fromJson(jsonPregunta, PreguntaSeleccionMultiple.class);

        String enunciadoPregunta = preg.getText();
        ArrayList respuestasPregunta = preg.getRespuestas(); 
        addEnunciadoPregunta(enunciadoPregunta);  

        for(int indexRespuesta = 1; indexRespuesta <= respuestasPregunta.size(); indexRespuesta++){
            String jsonRespuesta =  gson.toJson(respuestasPregunta.get(indexRespuesta - 1));
            RespuestaAbstracta resp = new Gson().fromJson(jsonRespuesta, RespuestaSeleccion.class);
            OpcionRespuestaSeleccion opcion = new Gson().fromJson(jsonRespuesta, OpcionRespuestaSeleccion.class);
            
            String enunciadoRespuesta = resp.obtenerText(); 
            opcion.isCorrecta(); 
            boolean respuestaCorrecta = opcion.isCorrecta(); 
             
            if(respuestaCorrecta == true){
                 respuestasCorrectas(indexRespuesta);
             }
             addEnunciadoRespuestaMultiple(enunciadoRespuesta, indexRespuesta);
       }
    }
    public void cargarPreguntasVF(){ 
        fraccionNumeroPreguntas(); 
        final Gson gson = new Gson();
	String jsonPregunta = gson.toJson(preguntasVF.get(indexPreguntaVF)); 
        PreguntaVF preg = new Gson().fromJson(jsonPregunta, PreguntaVF.class);

        String enunciadoPregunta = preg.getText();
        addEnunciadoPregunta(enunciadoPregunta); 
        if(preg.isRespuestaVerdadera()){ //VERDADERA
             arrayRespuestasCorrectas[indexPreguntaMultiple + indexPreguntaVF] = 1; 
        }
        else {
            arrayRespuestasCorrectas[indexPreguntaMultiple + indexPreguntaVF] = 2; 
        }
        indexPreguntaVF ++; 
    }
    
    public void setIndexPreguntaMultiple(int indexPregunta) {
        this.indexPreguntaMultiple = indexPregunta;
    }
    public void setIndexPreguntaVF(int indexPregunta) {
        this.indexPreguntaVF = indexPregunta;
    }

    

    public void fraccionNumeroPreguntas(){
        numeroPregunta = indexPreguntaMultiple + indexPreguntaVF+ 1; 
        fraccionPregunta.setText("Pregunta " + numeroPregunta + "/" + numeroPreguntas);
    }
   
    public void respuestasDeUsuario(int respuestasUsuario){
         arrayRespuestasUsuario[indexPreguntaMultiple] = respuestasUsuario; 
    }

    public void respuestasCorrectas(int indexRespuesta){
        arrayRespuestasCorrectas[indexPreguntaMultiple] = indexRespuesta; 
    }
    public void compararResultados(){
        int aciertos = numeroPreguntas; 
        String notafraccion;
        double notaCalculada; 
        for(int i = 0; i < numeroPreguntas; i++){
               if(arrayRespuestasCorrectas[i] != arrayRespuestasUsuario[i]){
                   aciertos--; 
               } 
        }
  
        notaCalculada = (aciertos * 10)/numeroPreguntas; 
        subirRespuestas(notaCalculada);
    }
    
    public void subirRespuestas(double nota){
        NotaQuizz notaQuizz = new NotaQuizz(nombreQuiz,estudianteConectado.getEmail(),nota,this.respuestas);
        con.insertarNota(notaQuizz);
    }
    
    public void guardarRespuestasUsuario(){
        int respuestaUsuario = 0; 
        switch(tipoPregunta){
            case "multiple": 
                if(respuesta1.isSelected()){
                respuestaUsuario = 1; 
                }if(respuesta2.isSelected()){
                respuestaUsuario = 2; 
                }if(respuesta3.isSelected()){
                respuestaUsuario = 3; 
                }if(respuesta4.isSelected()){
                respuestaUsuario = 4; 
                }
                break;
                
            case "vf": 
                if(respuesta1.isSelected()){ //Verdadero
                    respuestaUsuario = 1; 
                }
                if(respuesta2.isSelected()){ //Falso
                    respuestaUsuario = 2; 
                }
                break;    
        }
                String aux1 = pregunta.getText()+"";
                String aux2 = respuestaUsuario+"";
                aux = new PreguntaRespondida(aux1,aux2);
                respuestas.add(aux);
                respuestasDeUsuario(respuestaUsuario); 
    }

    public void setNumeroPreguntas(int numeroPreguntas) {
        this.numeroPreguntas = numeroPreguntas;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }
   
    public void setEstudianteConectado(UsuarioAlumno estudianteConectado) {
        this.estudianteConectado = estudianteConectado;
    }

    public void setNombreCursoSelected(String nombreCursoSelected) {
        this.nombreCursoSelected = nombreCursoSelected; //Nombre curso selected
        
    }

    @FXML
    private void pulsarFinalizar(ActionEvent event) throws IOException {
        guardarRespuestasUsuario();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Está seguro de finalizar el quiz?");
        Optional<ButtonType> action = alert.showAndWait();
        
        // Si hemos pulsado en aceptar
        if (action.get() == ButtonType.OK) {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText(null);
            alert2.setContentText("Quiz finalizado!");
            alert2.showAndWait(); 
            navegarAtras(event);
            
            compararResultados();
        } 
    }
    public void navegarAtras (ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionEstudianteQuizzes.fxml"));
        Parent root =(Parent) loader.load();      
        SesionEstudianteQuizzesController sesionQuizzes = loader.<SesionEstudianteQuizzesController>getController();
        sesionQuizzes.setNombreCursoSelected(nombreCursoSelected);
        sesionQuizzes.cargarListaQuizzes(); 
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.setResizable(false);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void setArrayRespuestasCorrectas(int[] arrayRespuestasCorrectas) {
        this.arrayRespuestasCorrectas = arrayRespuestasCorrectas;
    }

    public void setRespuestas(ArrayList<PreguntaRespondida> respuestas) {
        this.respuestas = respuestas;
    }
    
    
}
