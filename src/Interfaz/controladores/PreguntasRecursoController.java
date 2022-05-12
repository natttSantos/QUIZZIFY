/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.Calificacion;
import LogicaNegocio.modelo.NotaQuizz;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaRecurso;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.UsuarioInstructor;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author nata2
 */
public class PreguntasRecursoController {

    private String nombreRecurso; 
    private UsuarioInstructor instructorConectado; 
    private ArrayList< PreguntaAbstracta> preguntasRecurso = new ArrayList<>(); 
    @FXML
    private Label instructor;
    @FXML
    private Label labelNombreRecurso;
    @FXML
    private TableColumn<PreguntaRecurso, String> columnaPreguntas;
    @FXML
    private TableColumn<PreguntaRecurso, String> columnaTipo;
    @FXML
    private TableView<PreguntaRecurso> tableView;


    @FXML
    private void pulsarAtras(ActionEvent event) {
    }

    public void cargarPreguntasEnTabla(){
        labelNombreRecurso.setText(nombreRecurso);
//        columnaPreguntas.setCellValueFactory(new PropertyValueFactory("pregunta"));
//        columnaTipo.setCellValueFactory(new PropertyValueFactory("tipo"));

        ObservableList<PreguntaRecurso> datos = FXCollections.observableArrayList(); 
        for(PreguntaAbstracta preg: preguntasRecurso){
                PreguntaRecurso pregRec = new PreguntaRecurso(preg.getText(), preg.getTipo()); 
                datos.add(pregRec); 
        }
        columnaPreguntas.setCellValueFactory(new PropertyValueFactory("pregunta"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tableView.setItems(datos);

    }
    
    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public void setInstructorConectado(UsuarioInstructor instructorConectado) {
        this.instructorConectado = instructorConectado;
    }

    public void setPreguntasRecurso(ArrayList<PreguntaAbstracta> preguntasRecurso) {
        this.preguntasRecurso = preguntasRecurso;
    }
    
    
}
