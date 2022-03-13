/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author nata2
 */
public class RespuestaAbierta extends Respuesta{
    
    protected int longitud_maxima; 
    public RespuestaAbierta(String descripcion, int longitud_maxima ){
        super(descripcion); 
        this.longitud_maxima = longitud_maxima; 
    }

    @Override
    public String obtenerDescricpion() {
       return descripcion; 
    }
    
    
}
