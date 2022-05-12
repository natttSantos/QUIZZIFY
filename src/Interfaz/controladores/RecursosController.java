package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.Recurso;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class RecursosController implements Initializable {
    private UsuarioInstructor instructorConectado; 
    private Curso curso; 
    @FXML
    private Label instructor;
    private Conexion con; 
    @FXML
    private ListView<String> listaRecursos;
    @FXML
    private Label sinRecursos;
    private TextField nuevoRecurso;

    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        con = Conexion.obtenerConexion();
    }  

    public void setInstructorConectado(UsuarioInstructor instructorConectado) {
        this.instructorConectado = instructorConectado;
    }

    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionInstructor.fxml"));
        Parent root =(Parent) loader.load();      
        SesionInstructorController sesionInstructor = loader.<SesionInstructorController>getController();
        sesionInstructor.setUsuario(instructorConectado);
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        ((Node) event.getSource()).getScene().getWindow().hide();
        stage.show();  
        
    }


    @FXML
    private void pulsarAbrirRecurso(ActionEvent event) throws IOException {
        String recursoSelected = listaRecursos.getSelectionModel().getSelectedItem();
        ArrayList<PreguntaAbstracta> preguntasRecurso = con.obtenerTodasPreguntasDeRecurso(recursoSelected, instructorConectado); 
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/PreguntasRecurso.fxml"));
        Parent root =(Parent) loader.load();      
        PreguntasRecursoController controlador = loader.<PreguntasRecursoController>getController();
        controlador.setInstructorConectado(instructorConectado);
        controlador.setNombreRecurso(recursoSelected);
        controlador.setPreguntasRecurso(preguntasRecurso); 
        controlador.cargarPreguntasEnTabla();
        
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        ((Node) event.getSource()).getScene().getWindow().hide();
        stage.show();  
        
    }

    @FXML
    private void pulsarCrearRecurso(ActionEvent event) {
        TextInputDialog dialogoTextual = new TextInputDialog(); 
        dialogoTextual.setHeaderText("Escriba el tema del nuevo recurso");
        dialogoTextual.setContentText("Tema:");
        dialogoTextual.initStyle(StageStyle.UTILITY);
        Optional<String> respuesta = dialogoTextual.showAndWait(); 
        respuesta.ifPresent((temaNuevo) -> addNuevoTema(temaNuevo));
    }
    
    public void addNuevoTema (String temaNuevo){
         listaRecursos.getItems().add(temaNuevo); 
         con.insertarRecurso(new Recurso(temaNuevo, instructorConectado));
         sinRecursos.setVisible(false);
    }
    
    public void cargarRecursosInstructor(){
        ArrayList<Recurso> recursosInstructor = con.obtenerRecursosDeInstructor(instructorConectado); 
        if(recursosInstructor.size() > 0){
            for(Recurso rec: recursosInstructor){
                String nombreRecurso = rec.getNombreRecurso(); 
                listaRecursos.getItems().add(nombreRecurso); 
            }
            sinRecursos.setVisible(false);
        }
    }
}
