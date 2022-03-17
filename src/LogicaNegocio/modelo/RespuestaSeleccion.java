/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio.modelo;

import java.util.ArrayList;

/**
 *
 * @author nata2
 */
public class RespuestaSeleccion extends Respuesta{
    protected ArrayList<String> opciones; 
    protected ArrayList<Boolean> opciones_correctas; 
    
    public RespuestaSeleccion(String descripcion, ArrayList<String>opciones, ArrayList<Boolean>correctas){
        super(descripcion); 
        this.opciones = opciones;
        this.opciones_correctas = correctas;
    }

    public ArrayList<String> getOpciones() {
        return opciones;
    }

    public ArrayList<Boolean> getOpciones_correctas() {
        return opciones_correctas;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    @Override
    public String obtenerDescricpion() {
        return descripcion; 
    }

    @Override
    public String toString() {
        return "RespuestaSeleccion{" + "opciones=" + opciones + ", opciones_correctas=" + opciones_correctas + '}';
    }

    public void setOpciones(ArrayList<String> opciones) {
        this.opciones = opciones;
    }

    public void setOpciones_correctas(ArrayList<Boolean> opciones_correctas) {
        this.opciones_correctas = opciones_correctas;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
