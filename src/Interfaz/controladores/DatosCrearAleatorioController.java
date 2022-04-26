/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import java.io.IOException;
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
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author margr
 */
public class DatosCrearAleatorioController implements Initializable {

    @FXML
    private TextField numeroTextField;
    private CheckBox checkbox;
    private Label temaLabel;
    private ChoiceBox<?> temaChoiceBox;
    @FXML
    private Button aceptarButton;
    @FXML
    private TextField nombreTextField;
    
    private String nombre, tema;
    private int numero;
    private boolean anulado, temaConcreto;
    private ArrayList<PreguntaSeleccionMultiple> listaPreguntas;
    private Conexion con;
    @FXML
    private MenuButton menuCurso;
    
    private UsuarioInstructor instructorConectado; 
    @FXML
    private Label instructor;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Conexion.obtenerConexion();
        anulado = true; // si usuario cierra ventana con "X" accion es anulada por defecto
    }    

    @FXML
    private void numeroTextFieldClicked(ActionEvent event) {
    }

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
    private void aceptarButtonClicked(ActionEvent event) {
        nombre = nombreTextField.getText();
        numero = Integer.parseInt(numeroTextField.getText());
        
//        if (checkbox.isSelected()) {
//            // tema concreto
//            temaConcreto = true;
//            // TODO
//        } else {
//            tema = null;
//            temaConcreto = false;
//        }
        
        if (comprobarNumero()) {
            anulado = false;
            crearQuizAleatorio(numero, nombre, listaPreguntas);
            ((Node) event.getSource()).getScene().getWindow().hide();
        } else {
            JOptionPane.showMessageDialog(null, "No hay tantas preguntas en la bateria!","Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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
        con.insertarQuiz(nombre, obtenerCursoSelected(), preguntas);
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
        con = con;
    }
    
    public void addCursosToMenu(){
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                menuCurso.setText(((MenuItem)e.getSource()).getText()); 
            }
        };
        
        ArrayList <Curso> cursosInstructor = con.obtenerCursosDeInstructor(instructorConectado); 
        for(int i = 0; i < cursosInstructor.size() && cursosInstructor.size() > 0; i++){
           Curso curso = cursosInstructor.get(i); 
           MenuItem menuItem = new MenuItem(curso.getNombreCurso());
           menuCurso.getItems().add(menuItem); 
           menuItem.setOnAction(event);
       }
    }
    
    public Document obtenerCursoSelected(){
        String nombreCurso = menuCurso.getText(); 
        Curso curso = con.obtenerCurso("nombreCurso", nombreCurso); 
        Document dcurso = curso.obtenerDocument(); 
        return dcurso; 
    }
    public void setUsuario(UsuarioInstructor i){
        this.instructorConectado = i;
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
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
}
