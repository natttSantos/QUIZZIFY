/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Jaime
 */
public class CrearPreguntaController implements Initializable {

    @FXML
    private TextArea textoPregunta;
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
    private Button salirBoton;
    /**
     * Initializes the controller class.
     */
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
       ArrayList<String> opciones = new ArrayList();
       opciones.add(Res1.getText());
       opciones.add(Res2.getText());
       ArrayList<Boolean> correctas = new ArrayList();
       switch(respuestaCorrecta.getValue()){
           case "Respuesta 1": correctas.add(true);
                               correctas.add(false);
                               correctas.add(false);
                               correctas.add(false);
           break;
           case "Respuesta 2": correctas.add(false);
                               correctas.add(true);
                               correctas.add(false);
                               correctas.add(false);
           break;
           case "Respuesta 3":correctas.add(false);
                              correctas.add(false);
                              correctas.add(true);
                              correctas.add(false);
                              break;
           case "Respuesta 4":correctas.add(false);
                              correctas.add(false);
                              correctas.add(false);
                              correctas.add(true);
       }    
       RespuestaSeleccion r = new RespuestaSeleccion("RespuestaSeleccion1", opciones, correctas);
       c.insertarPregunta(textoPregunta.getText(), dificultadPregunta.getValue(), temaPregunta.getText(), r);
       JOptionPane.showMessageDialog(null,"¡Pregunta creada con éxito!");
       
    }

    @FXML
    private void actualizarComboBox(ActionEvent event) {
        
    }

    @FXML
    private void salir(ActionEvent event) throws IOException{
        Conexion c = Conexion.obtenerConexion();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionInstructor.fxml"));
                root =(Parent) loader.load();
                SesionInstructorController inicio = loader.<SesionInstructorController>getController();
    }
    
}
