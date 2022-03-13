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
public class QuizSeleccionMultiple extends QuizAbstracto {
    
    public QuizSeleccionMultiple(String nombre){
        super(nombre);
    }

    @Override
    public PreguntaAbstracta crearPregunta(String enunciado, String instrucciones, float puntuacion) throws IOException {
        PreguntaAbstracta pregunta = new PreguntaSeleccionMultiple (enunciado, instrucciones, puntuacion);
        return pregunta;
    }
    
}
