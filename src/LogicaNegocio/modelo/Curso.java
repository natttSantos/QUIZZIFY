/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import org.bson.Document;

/**
 *
 * @author nata2
 */
public class Curso {
    private String nombreCurso;  
    private ArrayList <UsuarioAlumno> alumnosEnCurso; 
    private UsuarioInstructor instructorEnCurso;
//    private ArrayList <QuizAbstracto> quizzesEnCurso; 
//
//    public Curso(String nombreCurso, ArrayList alumnosEnCurso, UsuarioInstructor instructorEnCurso, ArrayList<QuizAbstracto> quizzesEnCurso) {
//        this.nombreCurso = nombreCurso;
//        this.alumnosEnCurso = alumnosEnCurso;
//        this.instructorEnCurso = instructorEnCurso;
//        this.quizzesEnCurso = quizzesEnCurso;
//    }
    public Curso(String nombreCurso, ArrayList<UsuarioAlumno> alumnosEnCurso, UsuarioInstructor instructorEnCurso) {
        this.nombreCurso = nombreCurso;
        this.alumnosEnCurso = alumnosEnCurso;
        this.instructorEnCurso = instructorEnCurso;  
    }

     public Document obtenerDocument() {
        Document d = new Document(); 
          d.append("nombreCurso",this.nombreCurso);
          d.append("alumnosEnCurso", Arrays.asList(crearArrayDocument_EstudiantesCurso()));
          d.append("instructorEnCurso", crearDocument_InstructorCurso());
        return d;
    }
     
    public Document [] crearArrayDocument_EstudiantesCurso(){ 
        Document [] dUsers = new Document [alumnosEnCurso.size()]; 
        int i = 0; 
        for(UsuarioAlumno user: alumnosEnCurso){
            Document d = new Document(); 
            d.append("nombre", user.getNombre()); 
            d.append("apellidos", user.getApellidos()); 
            d.append("email", user.getEmail()); 
            d.append("contraseña", user.getContraseña()); 
            d.append("tipo", "Alumno");
            d.append("notas", asList(user.getNotas()));
            dUsers[i] = d; 
            i++; 
        }
        return dUsers; 
    }
    public Document crearDocument_InstructorCurso(){ 
            Document d = new Document(); 
            d.append("nombre",instructorEnCurso.getNombre());
            d.append("apellidos", instructorEnCurso.getApellidos());
            d.append("email", instructorEnCurso.getEmail());
            d.append("contraseña", instructorEnCurso.getContraseña());
            d.append("tipo", "Instructor");
            d.append("quizzesDisponibles", 20);

        return d; 
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

//    public ArrayList<QuizAbstracto> getQuizzesEnCurso() {
//        return quizzesEnCurso;
//    }
//
//    public void setQuizzesEnCurso(ArrayList<QuizAbstracto> quizzesEnCurso) {
//        this.quizzesEnCurso = quizzesEnCurso;
//    }
    
    
    
}
