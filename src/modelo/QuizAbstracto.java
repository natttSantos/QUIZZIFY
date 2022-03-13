/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jaime
 */
public abstract class QuizAbstracto {
    protected String nombre;
    protected ArrayList preguntas;
    
public QuizAbstracto(String nombre){
    this.nombre = nombre;
    preguntas = new ArrayList();
}    


public boolean añadirPregunta(PreguntaAbstracta pregunta){
    return preguntas.add(pregunta);
    
}

public boolean eliminarPregunta(PreguntaAbstracta pregunta){
    return preguntas.remove(pregunta);
}

public abstract PreguntaAbstracta crearPregunta(String enunciado, String instrucciones, float puntuacion) throws IOException;
}
