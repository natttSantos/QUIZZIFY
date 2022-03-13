/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author nata2
 */
public abstract class PreguntaAbstracta {
    protected String enunciado; 
    protected float puntuacion; 
    protected String instrucciones;
    
     public PreguntaAbstracta(String enunciado, String instrucciones, float puntuacion){
        this.enunciado = enunciado; 
        this.instrucciones = instrucciones; 
        this.puntuacion = puntuacion; 
    }

    //anyadir_respuesta()
    //eliminar_respuesta()
    //obtener_descripcion()
    public abstract Respuesta crearRespuesta(String descripcion); 
}
