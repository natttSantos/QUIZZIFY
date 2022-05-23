/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.tablas;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author nata2
 */
public class PreguntaRecurso {
    private final SimpleStringProperty pregunta; 
    private final SimpleStringProperty  tipo; 
    private final SimpleStringProperty  dificultad; 

    public PreguntaRecurso(String pregunta, String tipo, String dificultad) {
        this.pregunta = new SimpleStringProperty (pregunta);
        this.tipo =  new SimpleStringProperty (tipo);
        this.dificultad = new SimpleStringProperty(dificultad); 
    }

    public SimpleStringProperty getPregunta() {
        return pregunta;
    }

    public SimpleStringProperty getDificultad () {
        return dificultad;
    }
    public SimpleStringProperty getTipo() {
        return tipo;
    }

    public StringProperty preguntaProperty() {
        return pregunta;
    }
    public StringProperty tipoProperty() {
        return tipo;
    }

    public StringProperty dificultadProperty() {
        return dificultad;
    }
    
    
    
}
