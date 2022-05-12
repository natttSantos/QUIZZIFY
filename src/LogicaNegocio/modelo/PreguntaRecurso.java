/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

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

    public PreguntaRecurso(String pregunta, String tipo) {
        this.pregunta = new SimpleStringProperty (pregunta);
        this.tipo =  new SimpleStringProperty (tipo);
    }

    public SimpleStringProperty getPregunta() {
        return pregunta;
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

    
    
    
    
}
