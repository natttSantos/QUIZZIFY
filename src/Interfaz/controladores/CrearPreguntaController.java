package Interfaz.controladores;

import LogicaNegocio.modelo.OpcionRespuestaSeleccion;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.RespuestaSeleccion;
import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class CrearPreguntaController implements Initializable {

    @FXML
    private TextField Res1;
    @FXML
    private TextField Res2;
    @FXML
    private ComboBox<String> respuestaCorrecta;
    @FXML
    private ComboBox<String> dificultadPregunta;
    @FXML
    private TextField temaPregunta;
    @FXML
    private Button botonCrear;
    @FXML
    private TextField Res3;
    @FXML
    private TextField Res4;
    ObservableList<String> respuestasItems = FXCollections.observableArrayList();
    ObservableList<String> dificultadesItems = FXCollections.observableArrayList();
    @FXML
    private Label instructor;
    @FXML
    private TextArea textoPregunta;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       respuestasItems.addAll("Respuesta 1", "Respuesta 2", "Respuesta 3", "Respuesta 4");
       respuestaCorrecta.setItems(respuestasItems);
       dificultadesItems.addAll("Baja", "Media", "Alta");
       dificultadPregunta.setItems(dificultadesItems);
       
    }    

    @FXML
    private void crearPregunta(ActionEvent event) {
        Conexion c = Conexion.obtenerConexion();
       
        if(!textoPregunta.getText().equals("") && dificultadPregunta.getValue() != null && !temaPregunta.getText().equals("")){
            PreguntaAbstracta p = new PreguntaSeleccionMultiple(textoPregunta.getText(), dificultadPregunta.getValue(), temaPregunta.getText(), crearRespuestasSeleccion());
            c.insertarPregunta(p);
            enviarAlerta("Creado","Pregunta correctamente!");
        } else {
           enviarAlerta("ERROR","Inserta todos los campos necesarios!");
        }
       
    }
    
    
    //Extract Method funcionalidad unica
    private ArrayList<OpcionRespuestaSeleccion> crearRespuestasSeleccion() {
        ArrayList <OpcionRespuestaSeleccion> respuestasSeleccion = new ArrayList();
        if(!Res1.getText().equals("")){
            OpcionRespuestaSeleccion op1 = new OpcionRespuestaSeleccion(Res1.getText(), respuestaCorrecta.getValue().equals("Respuesta 1"));
            respuestasSeleccion.add(op1);
        }
        if(!Res2.getText().equals("")){
            OpcionRespuestaSeleccion op2 = new OpcionRespuestaSeleccion(Res2.getText(), respuestaCorrecta.getValue().equals("Respuesta 2"));
            respuestasSeleccion.add(op2);
        }
        if(!Res3.getText().equals("")){
            OpcionRespuestaSeleccion op3 = new OpcionRespuestaSeleccion(Res3.getText(), respuestaCorrecta.getValue().equals("Respuesta 3"));
            respuestasSeleccion.add(op3);
        }
        if(!Res4.getText().equals("")){
            OpcionRespuestaSeleccion op4 = new OpcionRespuestaSeleccion(Res4.getText(), respuestaCorrecta.getValue().equals("Respuesta 4"));
            respuestasSeleccion.add(op4);
        }
        return respuestasSeleccion;
    }

    @FXML
    private void actualizarComboBox(ActionEvent event) {
        
    }

    private void salir(ActionEvent event) throws IOException{
        Conexion c = Conexion.obtenerConexion();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionInstructor.fxml"));
        root =(Parent) loader.load();
        SesionInstructorController inicio = loader.<SesionInstructorController>getController();
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
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
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
