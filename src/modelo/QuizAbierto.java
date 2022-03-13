/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.IOException;

/**
 *
 * @author jaime
 */
public class QuizAbierto extends QuizAbstracto {
    
    public QuizAbierto(String nombre){
        super(nombre); 
    }

    /**
     *
     * @param enunciado
     * @param instrucciones
     * @param puntuacion
     * @return
     * @throws IOException
     */
    @Override
    public PreguntaAbstracta crearPregunta(String enunciado, String instrucciones, float puntuacion) throws IOException {
        PreguntaAbstracta pregunta = new PreguntaAbierta (enunciado, instrucciones, puntuacion);
        return pregunta;        
    }
    
}
