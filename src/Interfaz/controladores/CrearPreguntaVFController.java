/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.PreguntaVF;
import LogicaNegocio.modelo.Recurso;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author margr
 */
public class CrearPreguntaVFController implements Initializable {

    @FXML
    private Label instructor;
    @FXML
    private Button botonCrear;
    @FXML
    private TextArea textoPregunta;
    @FXML
    private RadioButton verdadRadioButton;
    @FXML
    private RadioButton falsoRadioButton;
    
    private Conexion conexion;
    private boolean respuestaVerdad;
    @FXML
    private ComboBox<String> dificultadComboBox;
    private UsuarioInstructor instructorConectado; 
    
    ObservableList<String> dificultadesItems = FXCollections.observableArrayList();
    @FXML
    private MenuButton menuRecursos;
    @FXML
    private TextField puntos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = Conexion.obtenerConexion();
        
        ToggleGroup toggleGroup = new ToggleGroup();
        verdadRadioButton.setToggleGroup(toggleGroup);
        falsoRadioButton.setToggleGroup(toggleGroup);
        
        dificultadesItems.addAll("Baja", "Media", "Alta");
        dificultadComboBox.setItems(dificultadesItems);
        
        resetVentana();
        
        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal == verdadRadioButton) {
                respuestaVerdad = true;
            } else {
                respuestaVerdad = false;
            }
        });
        
        textoPregunta.textProperty().addListener((ObservableValue<? extends String> obs, String oldValue, String newValue) -> {
            if (newValue.equals("")){
                botonCrear.setDisable(true);
            } else {
                botonCrear.setDisable(false);
            }
        });
        
    }  
    
    public void resetVentana() {
        verdadRadioButton.setSelected(true);
        respuestaVerdad = true;
        
        botonCrear.setDisable(true);
        
        dificultadComboBox.getSelectionModel().selectFirst();
        
        textoPregunta.setText("");
    }

    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionInstructor.fxml"));
        Parent root =(Parent) loader.load();      
        SesionInstructorController sesionInstructor = loader.<SesionInstructorController>getController();
        sesionInstructor.setUsuario(instructorConectado);
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.setResizable(false);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void crearPregunta(ActionEvent event) {
        String texto = textoPregunta.getText();
        String dificultad = dificultadComboBox.getValue();

        PreguntaVF pregunta = new PreguntaVF(texto, dificultad, respuestaVerdad, Double.parseDouble(puntos.getText()));
        if(menuRecursos.getText().equals("")){
            enviarAlerta("Error", "Debe seleccionar un recurso.");
        } else{
            pregunta.setRecurso(new Recurso(menuRecursos.getText(), instructorConectado));
            conexion.insertarPregunta(pregunta);
            enviarAlerta("Confirmación","Pregunta creada correctamente!");
            resetVentana();
        }
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
        dialogoAlerta.showAndWait(); 
    }

    public void setInstructorConectado(UsuarioInstructor instructorConectado) {
        this.instructorConectado = instructorConectado;
    }
     public void addRecursosToMenu(){
        ArrayList <Recurso> recursosInstructor = conexion.obtenerRecursosDeInstructor(instructorConectado); 
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                menuRecursos.setText(((MenuItem)e.getSource()).getText()); 
            }
        };
        for(int i = 0; i < recursosInstructor.size(); i++){
            Recurso rec = recursosInstructor.get(i); 
            String email_alum = rec.getNombreRecurso(); 
            MenuItem menuItem = new MenuItem(email_alum);
            menuRecursos.getItems().add(menuItem);
            menuItem.setOnAction(event);
        }
    }
}
