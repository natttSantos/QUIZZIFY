/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author margr
 */
public class QuizDeBateria extends QuizAbstracto {
    /**
     * Clase representa el quiz que consiste en preguntas aleatorias vinculados con un recurso común
     * Quiz no guarda lista de preguntas, porque cada estudiante obtendrá preguntas diferentes
     */

    int numero;
    Recurso recurso;
    
    public QuizDeBateria(String nombre, Curso curso, String estado, ArrayList<PreguntaAbstracta> preguntas, int numero, Recurso recurso, LocalDate dateInicio, LocalDate dateFin) {
        super(nombre, curso, estado, preguntas, dateInicio, dateFin);
        this.numero = numero;
        this.recurso = recurso;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public Recurso getRecurso() {
        return recurso;
    }
    
    
    
}
