/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.tablas;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author crivi
 */
public class PreguntaAciertoFallo {
    private final SimpleStringProperty pregunta;
    private final SimpleDoubleProperty acierto;
    private final SimpleDoubleProperty fallo;
    
    public PreguntaAciertoFallo(String pregunta, double acierto, double fallo){
        this.pregunta = new SimpleStringProperty(pregunta);
        this.acierto = new SimpleDoubleProperty(acierto);
        this.fallo = new SimpleDoubleProperty(fallo);
    }
}
