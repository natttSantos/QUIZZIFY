/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author nata2
 */
public class PreguntaAbierta extends PreguntaAbstracta {
    
    public PreguntaAbierta(String enunciado, String instrucciones, float puntuacion){
        super(enunciado, instrucciones, puntuacion); 
    }
    

    public Respuesta crearRespuesta(String descripcion){
        Respuesta respuesta = new RespuestaSeleccion(descripcion); 
        return respuesta; 
        }
    
    }



