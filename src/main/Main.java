/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


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
        // Este código pone un instructor de ejemplo en base de dato
        
//        Conexion c = Conexion.obtenerConexion(); 
//        if(c != null) {
//
//            UsuarioInstructor instructor;
//            String[] grupos = {"A", "B"};
//            instructor = new UsuarioInstructor("Jose", "Arcadio Morales", "email@email.com", "Password", grupos);
//            boolean exito = c.crearUsuarioInstructor(instructor);
//
//        }
    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
    }
    
}
