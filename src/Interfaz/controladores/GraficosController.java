/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author nata2
 */
public class GraficosController {
    private ObservableList<Data> data2d = FXCollections.observableArrayList();
    private UsuarioAlumno estudianteConectado; 
    private String nombreCursoSelected; 
    ArrayList<QuizAbstracto> quizzesCurso = new ArrayList<>(); 

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

    @FXML
    private void pulsarEstadisticas(ActionEvent event) {
    }
    
     /**
     * Crea escena fx
     */
    public void createScene(){
        Platform.runLater(() -> {            
            pieChartAprobados.setTitle("Aprobados vs Suspendidos");//titulo del grafico
            pieChartAprobados.setLegendSide(Side.LEFT);//Posicion de leyenda            
            pieChartAprobados.setData(getChartData());            
            updateColors();            
            //jfxPanel.setScene(new Scene(pieChartAprobados));
        });
    }
        
    /**
     * devuelve los valores para el grafico 2d
     */
    public ObservableList<Data> getChartData() {        
        data2d.addAll(new PieChart.Data("Aprobados", 55),
                      new PieChart.Data("Suspendidos", 87));          
        return data2d;
    }
    
    /**
     * Actualiza colores de la torta y su leyenda
     */
    public void updateColors(){    
        //colores para cada seccion de la torta
        Color[] colors = { Color.web("#04B404"), Color.web("#FF8000") };
        
        int i = 0;
        //cambia colores de cada seccion de la torta
        for (PieChart.Data data : data2d) {
            String hex = String.format( "#%02X%02X%02X",
                        (int)( colors[i].getRed() * 255 ),
                        (int)( colors[i].getGreen() * 255 ),
                        (int)( colors[i].getBlue() * 255 ) );
              data.getNode().setStyle( "-fx-pie-color: "+hex+";");
              i++;
        }
        //cambia colores de la leyenda
        Set<Node> items;items = pieChartAprobados.lookupAll("Label.chart-legend-item");
//        i = 0;            
//        for (Node item : items) {
//            Label label = (Label) item;
//            final Rectangle rectangle = new Rectangle(20, 20, colors[i]);                
//            label.setGraphic(rectangle);
//            i++;
//        }
    }

    
    /**
     * Actualiza valores del gráfico
     * @param java valores para java
     * @param javafx valores para javafx
     */
    public void setChartData(int aprobados, int suspendidos){
        Platform.runLater(() -> {
            data2d.clear();
            data2d.addAll(new PieChart.Data("Aprobados", aprobados),
                         new PieChart.Data("Suspendidos", suspendidos));
            updateColors();
        });
        
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
    
    
}
