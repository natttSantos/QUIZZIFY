/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.PreguntaVF;
import LogicaNegocio.modelo.Recurso;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
    private ArrayList<PreguntaAbstracta> listaPreguntas;
    private Conexion con;
    @FXML
    private MenuButton menuCurso;
    
    private UsuarioInstructor instructorConectado; 
    @FXML
    private Label instructor;
    @FXML
    private MenuButton menuRecurso;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private RadioButton radioButton1;
    @FXML
    private RadioButton radioButton2;


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
    private void aceptarButtonClicked(ActionEvent event) throws IOException {
        nombre = nombreTextField.getText();
        numero = Integer.parseInt(numeroTextField.getText());
        ArrayList <PreguntaAbstracta> preguntasRecurso = con.obtenerTodasPreguntasDeRecurso(menuRecurso.getText(), instructorConectado); 
        
        if (comprobarNumero()) {
            anulado = false;
            if (radioButton1.isSelected()) {    // Sortea pregunta en momento de crear quiz. Cada alumno tiene el mismo quiz al resolver
                crearQuizAleatorio(numero, nombre, preguntasRecurso);
            } else {
                // Crea quiz vacio, solo vinculado con recurso. Preguntas se sortean justo antes de resolver - cada alumno tiene preguntas diferentes
                Recurso recurso = con.obtenerRecurso("nombreRecurso", menuRecurso.getText());
                crearQuizDeBateria(numero, nombre, recurso);
            }
            
            ((Node) event.getSource()).getScene().getWindow().hide();
            navegarFormInstructor(event);
            
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
    
    public void crearQuizAleatorio(int num, String nombre, ArrayList<PreguntaAbstracta> lista) {
        Collections.shuffle(lista);
        Document[] preguntas = new Document[num];
        
        for (int i = 0; i < num; i++) {
            
            PreguntaAbstracta pregunta = lista.get(i);
            Document d = pregunta.obtenerDocument(); 
            preguntas[i] = d;
        }
        con.insertarQuiz(nombre, obtenerCursoSelected(), "En preparacion" ,preguntas);
        instructorConectado.setQuizzesDisponibles(instructorConectado.getQuizzesDisponibles() - 1);
        con.reducirCantQuizzesDisponibles(instructorConectado.getEmail(), instructorConectado.getQuizzesDisponibles());
        
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
    
    public void setListaPreguntas(ArrayList<PreguntaAbstracta> lista) {
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
       public void navegarFormInstructor (ActionEvent event) throws IOException{
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
       public void addRecursosToMenu(){
        ArrayList <Recurso> recursosInstructor = con.obtenerRecursosDeInstructor(instructorConectado); 
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                menuRecurso.setText(((MenuItem)e.getSource()).getText()); 
            }
        };
        for(int i = 0; i < recursosInstructor.size(); i++){
            Recurso rec = recursosInstructor.get(i); 
            String email_alum = rec.getNombreRecurso(); 
            MenuItem menuItem = new MenuItem(email_alum);
            menuRecurso.getItems().add(menuItem);
            menuItem.setOnAction(event);
        }
    }

    private void crearQuizDeBateria(int numero, String nombre, Recurso recurso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        con.insertarQuiz( obtenerCursoSelected(), estado, recurso);
    }
}
