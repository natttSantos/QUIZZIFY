/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.Pregunta;
import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nata2
 */
public class SesionInstructorController implements Initializable {

    private String nombreUsuario; 
    @FXML
    private Label instructor;
    
    private Conexion conexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        instructor.setText(nombreUsuario);
    }
    
    public void setConexion(Conexion con) {
        conexion = con;
    }

    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/InicioSesion.fxml"));
        Parent root =(Parent) loader.load();      
        InicioSesionController inicio = loader.<InicioSesionController>getController();
        inicio.setTipoUsuario("Instructor");
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void quizAleatorioPulsar(ActionEvent event) throws IOException {
        
        ArrayList<Pregunta> listaPreguntas = conexion.obtenerTodasPreguntas();
        
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/DatosCrearAleatorio.fxml"));
        Parent root = miCargador.load();
        
        DatosCrearAleatorioController controlador = miCargador.getController();
        
        controlador.setListaPreguntas(listaPreguntas);
        
        Scene scene = new Scene(root, 400, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Crear un quiz aleatorio");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        if (controlador.getAnulado()) {
            return;  //  si creación de quiz es anulado, no ejecuta codigo abajo
        }
        
        String nombre = controlador.getNombre();
        int numero = controlador.getNumero();
        boolean temaConcreto = controlador.getTemaConcreto();
        
        if (temaConcreto) {
            String tema = controlador.getTema();
        }
        
        
        // TODO
        // [done] validar numero de preguntas (no puede ser mayor de numero de preguntas en la base
        // crear quiz con preguntas aleatorias
        // subir el quiz a la base de datos
        
        
    }

    @FXML
    private void crearPreguntaPulsar(ActionEvent event) throws IOException{
    
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/CrearPregunta.fxml"));
        Parent root = miCargador.load();
        CrearPreguntaController controlador = miCargador.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setTitle("Crear un quiz aleatorio");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void crearQuiz(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/GenerarQuizNoAleatorio.fxml"));
            Parent root = loader.load();
        GenerarQuizNoAleatorioController controlador = loader.getController();
        Scene scene = new Scene(root, 400, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setTitle("Crear un quiz");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}    
