/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author crivi
 */
public abstract class Respuesta {
    protected String descripcion;
    
    public Respuesta(String descripcion){
        this.descripcion = descripcion; 
    }
     public abstract String obtenerDescricpion(); 
}
