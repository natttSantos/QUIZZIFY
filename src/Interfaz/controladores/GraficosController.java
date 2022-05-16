/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.NotaQuizz;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.UsuarioAlumno;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author nata2
 */
public class GraficosController {
    //private ObservableList<Data> data2d = FXCollections.observableArrayList();
    private UsuarioAlumno estudianteConectado; 
    private String nombreCursoSelected; 
    ArrayList<QuizAbstracto> quizzesCurso = new ArrayList<>(); 
    ArrayList <NotaQuizz> aprobados = new ArrayList<>(); 
    ArrayList <NotaQuizz> suspendidos = new ArrayList<>(); 

    @FXML
    private Label instructor;
    @FXML
    private PieChart pieChartAprobados;

    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/CalificacionesEstudiante.fxml"));
        Parent root =(Parent) loader.load();
        CalificacionesEstudianteController calif = loader.<CalificacionesEstudianteController>getController(); 
        calif.setEstudianteConectado(estudianteConectado);
        calif.setNombreCursoSelected(nombreCursoSelected);
        calif.setQuizzesCurso(quizzesCurso);
        calif.cargarNotas();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.setResizable(false);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }



    public void setEstudianteConectado(UsuarioAlumno estudianteConectado) {
        this.estudianteConectado = estudianteConectado;
    }

    public void setNombreCursoSelected(String nombreCursoSelected) {
        this.nombreCursoSelected = nombreCursoSelected;
    }

    public void setQuizzesCurso(ArrayList<QuizAbstracto> quizzesCurso) {
        this.quizzesCurso = quizzesCurso;
    }

    public void setAprobados(ArrayList<NotaQuizz> aprobados) {
        this.aprobados = aprobados;
    }

    public void setSuspendidos(ArrayList<NotaQuizz> suspendidos) {
        this.suspendidos = suspendidos;
    }
    
    
    public void cargarData (){
        double numQuizzesRealizados = aprobados.size() + suspendidos.size(); 
        double porcentajeAprobados = (aprobados.size()/numQuizzesRealizados)* 100; 
        double porcentajeSuspendidos = (suspendidos.size()/numQuizzesRealizados)* 100; 
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Aprobados", porcentajeAprobados), 
                new PieChart.Data("Suspendidos", porcentajeSuspendidos)
        );
        pieChartAprobados.setData(data);
        pieChartAprobados.setLegendSide(Side.LEFT);
        pieChartAprobados.setTitleSide(Side.BOTTOM);
        pieChartAprobados.setTitle("Aprobados vs Suspendidos");
        pieChartAprobados.setTitleSide(Side.TOP);

    }
    
    
    
}
