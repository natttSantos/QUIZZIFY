/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.Recurso;
import LogicaNegocio.modelo.Respuesta;
import LogicaNegocio.modelo.RespuestaAbstracta;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class EditarPreguntaMultipleController implements Initializable {

    @FXML
    private Label instructor;
    @FXML
    private Button botonEditar;
    @FXML
    private TextArea textoPregunta;
    @FXML
    private TextField respuestaText;
    @FXML
    private Button addButton;
    @FXML
    private ComboBox<String> dificultadComboBox;
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
    
    
    ObservableList<String> dificultadesItems = FXCollections.observableArrayList();
    private int numeroDeRespuestas;
    private ArrayList<Respuesta> respuestas;
    private Conexion conexion;
    private PreguntaAbstracta preguntaModificable;
    private CheckBox [] checks;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = Conexion.obtenerConexion();
        respuestas = new ArrayList();
        dificultadesItems.addAll("Baja", "Media", "Alta");
        dificultadComboBox.setItems(dificultadesItems);
        checks = new CheckBox[6];
        checks[0] = r1checkBox;
        checks[1] = r2checkBox;
        checks[2] = r3checkBox;
        checks[3] = r4checkBox;
        checks[4] = r5checkBox;
        checks[5] = r6checkBox;
    }
    
    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException { //no mante datos de pantalla anterior
     
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void editarPreguntaClicked(ActionEvent event) {
         ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void addButtonClicked(ActionEvent event) {
        String text = respuestaText.getText();
        System.out.println(text);
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
                    botonEditar.setDisable(false);
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

    void setPregunta(PreguntaAbstracta p) {
       preguntaModificable = p;
       textoPregunta.setText(preguntaModificable.getText());
       ArrayList<Respuesta> resp = preguntaModificable.getRespuestas();
       numeroDeRespuestas = resp.size();
       
       dificultadComboBox.setValue( preguntaModificable.getDificultad());
       
        for (int i = 0; i < resp.size(); i++) {
           
            checks[i].setText(resp.get(i).getTexto());
            checks[i].setVisible(true);
            System.out.println(i + " - " + resp.get(i).getEsCorrecta());
            /*if(resp.get(i).getEsCorrecta()).equals("true")) {
                checks[i].isSelected();
            }*/
        }
      // menuRecursos.set((Recurso)preguntaModificable.getRecurso());
     
    }
    
}
