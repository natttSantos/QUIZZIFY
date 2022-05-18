/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.tablas;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author nata2
 */
public class Calificacion {
    private final SimpleStringProperty curso; 
    private final SimpleStringProperty  nombreQuiz; 
    private final SimpleDoubleProperty  nota; 
    private final SimpleStringProperty  porcentaje; 

    public Calificacion(String curso, String nombreQuiz, double nota, String porcentaje) {
        this.curso = new SimpleStringProperty (curso);
        this.nombreQuiz =  new SimpleStringProperty (nombreQuiz);;
        this.nota =  new SimpleDoubleProperty (nota);
        this.porcentaje = new SimpleStringProperty(porcentaje); 
    }

    public SimpleStringProperty getCurso() {
        return curso;
    }

    public SimpleStringProperty getNombreQuiz() {
        return nombreQuiz;
    }

    public SimpleDoubleProperty getNota() {
        return nota;
    }
    
    public StringProperty cursoProperty() {
        return curso;
    }
    public StringProperty nombreQuizProperty() {
        return nombreQuiz;
    }
    public SimpleDoubleProperty notaProperty() {
         return nota;
    }

    public SimpleStringProperty getPorcentaje() {
        return porcentaje;
    }
    public SimpleStringProperty porcentajeProperty() {
         return porcentaje;
    }

    
    
    
}
