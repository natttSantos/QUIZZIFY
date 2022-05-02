/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.Respuesta;
import LogicaNegocio.modelo.RespuestaSeleccion;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author margr
 */
public class CrearPreguntaMultipleController implements Initializable {

    @FXML
    private Label instructor;
    @FXML
    private Button botonCrear;
    @FXML
    private TextArea textoPregunta;
    @FXML
    private Button addButton;
    @FXML
    private CheckBox r1checkBox;
    @FXML
    private CheckBox r2checkBox;
    @FXML
    private CheckBox r3checkBox;
    @FXML
    private CheckBox r4checkBox;
    @FXML
    private CheckBox r5checkBox;
    @FXML
    private CheckBox r6checkBox;
    @FXML
    private TextField respuestaText;
    @FXML
    private ComboBox<String> dificultadComboBox;
    
    private UsuarioInstructor instructorConectado; 
    
    private int numeroDeRespuestas;
    private ArrayList<Respuesta> respuestas;
    private Conexion conexion;
    ObservableList<String> dificultadesItems = FXCollections.observableArrayList();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = Conexion.obtenerConexion();
        
        dificultadesItems.addAll("Baja", "Media", "Alta");
        dificultadComboBox.setItems(dificultadesItems);
        
        resetVentana();
        
        respuestaText.textProperty().addListener((ObservableValue<? extends String> obs, String oldValue, String newValue) -> {
            if (newValue.equals("") || numeroDeRespuestas == 6){
                addButton.setDisable(true);
            } else {
                addButton.setDisable(false);
            }
        });
        
        textoPregunta.textProperty().addListener((ObservableValue<? extends String> obs, String oldValue, String newValue) -> {
            if (newValue.equals("") || numeroDeRespuestas < 2){
                botonCrear.setDisable(true);
            } else {
                botonCrear.setDisable(false);
            }
        });
        
    }

    public void resetVentana() {
        respuestas = new ArrayList<Respuesta>();
        numeroDeRespuestas = 0;
        
        botonCrear.setDisable(true);
        addButton.setDisable(true);
        botonCrear.setDisable(true);

        dificultadComboBox.getSelectionModel().selectFirst();
        textoPregunta.setText("");
        
        CheckBox[] checkBoxes = {r1checkBox, r2checkBox, r3checkBox, r4checkBox, r5checkBox, r6checkBox};
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setVisible(false);
            checkBox.setText("");
            checkBox.setSelected(false);
        }
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
        
        CheckBox[] checkBoxes = {r1checkBox, r2checkBox, r3checkBox, r4checkBox, r5checkBox, r6checkBox};

        for (int i=0; i<numeroDeRespuestas; i++) {
            respuestas.get(i).setEsCorrecta(checkBoxes[i].isSelected());
        }
        
        PreguntaSeleccionMultiple pregunta = new PreguntaSeleccionMultiple(texto, dificultad, respuestas);
        conexion.insertarPregunta(pregunta);
        
        enviarAlerta("Confirmación","Creado pregunta correctamente!");
        resetVentana();
        
    }

    @FXML
    private void addButtonClicked(ActionEvent event) {
        String text = respuestaText.getText();
        respuestas.add(new Respuesta(text));
        respuestaText.setText("");
        numeroDeRespuestas++;
        
        switch (numeroDeRespuestas) {
            case 1:
                r1checkBox.setText(text);
                r1checkBox.setVisible(true);
                break;
            case 2:
                r2checkBox.setText(text);
                r2checkBox.setVisible(true);
                
                if (!textoPregunta.getText().equals("")) {  // 2 respuestas es minimo para crear pregunta
                    botonCrear.setDisable(false);
                }
                
                break;
            case 3:
                r3checkBox.setText(text);
                r3checkBox.setVisible(true);
                break;
            case 4:
                r4checkBox.setText(text);
                r4checkBox.setVisible(true);
                break;
            case 5:
                r5checkBox.setText(text);
                r5checkBox.setVisible(true);
                break;
            case 6:
                r6checkBox.setText(text);
                r6checkBox.setVisible(true);
                addButton.setDisable(true);
                break;
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

    
    
}
