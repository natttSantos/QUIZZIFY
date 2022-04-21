/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

/**
 *
 * @author crivi
 */
public class NotaQuizz {
    private String quizz;
    private int nota;
    public NotaQuizz(String quizz, int nota){
        this.quizz = quizz;
        this.nota = nota;
    }
    public String getQuizz() {
        return quizz;
    }
    
    public int getNota() {
        return nota;
    }
}
