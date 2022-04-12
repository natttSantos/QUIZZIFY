/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

import java.util.ArrayList;

/**
 *
 * @author nata2
 */
public class Curso {
    private String nombreCurso;  
    private ArrayList alumnosEnCurso; 
    private UsuarioInstructor instructorEnCurso;
    private ArrayList <QuizAbstracto> quizzesEnCurso; 

    public Curso(String nombreCurso, ArrayList alumnosEnCurso, UsuarioInstructor instructorEnCurso, ArrayList<QuizAbstracto> quizzesEnCurso) {
        this.nombreCurso = nombreCurso;
        this.alumnosEnCurso = alumnosEnCurso;
        this.instructorEnCurso = instructorEnCurso;
        this.quizzesEnCurso = quizzesEnCurso;
    }
    public Curso(String nombreCurso, ArrayList alumnosEnCurso, UsuarioInstructor instructorEnCurso) {
        this.nombreCurso = nombreCurso;
        this.alumnosEnCurso = alumnosEnCurso;
        this.instructorEnCurso = instructorEnCurso;  
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public ArrayList getAlumnosEnCurso() {
        return alumnosEnCurso;
    }

    public void setAlumnosEnCurso(ArrayList alumnosEnCurso) {
        this.alumnosEnCurso = alumnosEnCurso;
    }

    public UsuarioInstructor getInstructorEnCurso() {
        return instructorEnCurso;
    }

    public void setInstructorEnCurso(UsuarioInstructor instructorEnCurso) {
        this.instructorEnCurso = instructorEnCurso;
    }

    public ArrayList<QuizAbstracto> getQuizzesEnCurso() {
        return quizzesEnCurso;
    }

    public void setQuizzesEnCurso(ArrayList<QuizAbstracto> quizzesEnCurso) {
        this.quizzesEnCurso = quizzesEnCurso;
    }
    
    
    
}
