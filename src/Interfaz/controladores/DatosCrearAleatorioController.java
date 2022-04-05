/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import Persistencia.conexion.Conexion;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.bson.Document;
import static java.util.Arrays.asList;

/**
 * FXML Controller class
 *
 * @author margr
 */
public class DatosCrearAleatorioController implements Initializable {

    @FXML
    private TextField numeroTextField;
    @FXML
    private CheckBox checkbox;
    @FXML
    private Label temaLabel;
    @FXML
    private ChoiceBox<?> temaChoiceBox;
    @FXML
    private Button aceptarButton;
    @FXML
    private Button anularButton;
    @FXML
    private TextField nombreTextField;
    
    private String nombre, tema;
    private int numero;
    private boolean anulado, temaConcreto;
    private ArrayList<PreguntaSeleccionMultiple> listaPreguntas;
    private Conexion conexion;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anulado = true; // si usuario cierra ventana con "X" accion es anulada por defecto
    }    

    @FXML
    private void numeroTextFieldClicked(ActionEvent event) {
    }

    @FXML
    private void checkboxClicked(ActionEvent event) {
        if (checkbox.isSelected()) {
            temaLabel.setDisable(false);
            temaChoiceBox.setDisable(false);
        } else {
            temaLabel.setDisable(true);
            temaChoiceBox.setDisable(true);
        }
    }

    @FXML
    private void temaChoiceBoxClicked(MouseEvent event) {
    }

    @FXML
    private void aceptarButtonClicked(ActionEvent event) {
        nombre = nombreTextField.getText();
        numero = Integer.parseInt(numeroTextField.getText());
        
        if (checkbox.isSelected()) {
            // tema concreto
            temaConcreto = true;
            // TODO
        } else {
            tema = null;
            temaConcreto = false;
        }
        
        if (comprobarNumero()) {
            anulado = false;
            crearQuizAleatorio(numero, nombre, listaPreguntas);
            ((Node) event.getSource()).getScene().getWindow().hide();
        } else {
            JOptionPane.showMessageDialog(null, "No hay tantas preguntas en la bateria!","Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void anularButtonClickedTest(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void nombreTextFieldClicked(ActionEvent event) {
    }
    
    private boolean comprobarNumero() {
        int numero = Integer.parseInt(numeroTextField.getText());
        int numeroPreguntas = listaPreguntas.size();
        return numero <= numeroPreguntas;
    }
    
    public void crearQuizAleatorio(int num, String nombre, ArrayList<PreguntaSeleccionMultiple> lista) {
        Collections.shuffle(lista);
        Document[] preguntas = new Document[num];
        
        for (int i = 0; i < num; i++) {
            
            PreguntaSeleccionMultiple pregunta = lista.get(i);
            Document d = new Document();
            
            d.append("text", pregunta.getText())
                .append("dificultad", pregunta.getDificultad())
                .append("tema", pregunta.getTema())
                .append("respuestas", asList(pregunta.getRespuestas()));
                preguntas[i] = d;
        }
        conexion.insertarQuiz(nombre, preguntas);
    }
    
    public boolean getAnulado() {
        return anulado;
    }
    public boolean getTemaConcreto() {
        return temaConcreto;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTema() {
        return tema;
    }
    public int getNumero() {
        return numero;
    }
    
    public void setListaPreguntas(ArrayList<PreguntaSeleccionMultiple> lista) {
        listaPreguntas = lista;
    }
    public void setConexion(Conexion con) {
        conexion = con;
    }
}
