/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

import java.util.ArrayList;

/**
 *
 * @author crivi
 */
public class NotaQuizz {
    private String quizz;
    private int nota;
    private int[] respuestas;
    
    public NotaQuizz(String quizz, int nota, int[] respuestas){
        this.quizz = quizz;
        this.nota = nota;
        this.respuestas = respuestas;
    }
    public String getQuizz() {
        return quizz;
    }
    
    public int getNota() {
        return nota;
    }
    
    public int[] getRespuestas() {
        return this.respuestas;
    }
}
