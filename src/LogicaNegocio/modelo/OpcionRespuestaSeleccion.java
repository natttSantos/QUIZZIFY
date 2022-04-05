/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class OpcionRespuestaSeleccion {
    protected String descripcion; 
    protected boolean correcta;
    
    public OpcionRespuestaSeleccion(String descripcion, Boolean correcta){
        this.descripcion = descripcion;
        this.correcta = correcta;
    }
    
    public boolean isCorrecta() {
        return correcta;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

    @Override
    public String toString() {
        return "OpcionRespuestaSeleccion{" + "descripcion=" + descripcion + ", correcta=" + correcta + '}';
    }   
}

