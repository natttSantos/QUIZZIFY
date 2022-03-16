/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package main;

import com.mongodb.MongoClient;
import Persistencia.controladores.ControladorPreguntas;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Persistencia.conexion.Conexion;
import LogicaNegocio.modelo.Pregunta;

/**
 *
 * @author nata2
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Interfaz/vista/InicioPrograma.fxml"));
            Pane ventana = (Pane) loader.load(); 
        
            Scene scene = new Scene (ventana); 
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        // ========== test ==========
//        Conexion c = Conexion.obtenerConexion(); 
//        if(c != null) {

//           String [] respe = {"Verdadero","Falso"};
//           c.insertarPregunta("Responda si la afirmacion siguiente es cierta: Hola que tal", "alta", respe);
           
        //}
    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
    }
    
}
