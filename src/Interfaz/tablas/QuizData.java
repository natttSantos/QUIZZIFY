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
public class QuizData {
    private final SimpleStringProperty nombre; 
    private final SimpleStringProperty  estado; 

    public QuizData(String nombre, String estado) {
        this.nombre = new SimpleStringProperty (nombre);
        this.estado =  new SimpleStringProperty (estado);
    }

    public SimpleStringProperty getNombre() {
        return nombre;
    }

    public SimpleStringProperty getEstado() {
        return estado;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }
    public StringProperty estadoProperty() {
        return estado;
    }

    
    
    
    
}
