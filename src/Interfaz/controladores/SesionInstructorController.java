/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Interfaz.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/InicioSesion.fxml"));
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
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/DatosCrearAleatorio.fxml"));
        Parent root = miCargador.load();
        
        DatosCrearAleatorioController controlador = miCargador.getController();
        
        Scene scene = new Scene(root, 400, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Crear un quiz aleatorio");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    

    }    
