/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
import com.mongodb.client.MongoCollection;
import static java.util.Arrays.asList;
import org.bson.Document;

/**
 *
 * @author nata2
 */
public class ControladorCursos {
    
     MongoCollection cursos;
    
    public ControladorCursos(MongoCollection collection){
       cursos = collection;
    }
    public boolean crearCurso(Curso c) {
        try{
          Document d = new Document("nombre",c.getNombreCurso());
          d.append("alumnos", c.getAlumnosEnCurso());
          d.append("instructor", c.getInstructorEnCurso());
          d.append("quizzes", c.getQuizzesEnCurso()); 
          cursos.insertOne(d);  
          return true; 
           }catch(Exception e) {
            System.out.println("ERROR al crear curso:  " + e.getMessage());
            return false;
        }
    }
    public boolean crearCursosSinQuiz(String nombre, Document[] estudiantes, Document instructor) {
        try{
          Document d = new Document(); 
          d.append("nombre",nombre);
          d.append("alumnos", asList(estudiantes));
          d.append("instructor", instructor);
          cursos.insertOne(d);  
          return true; 
           }catch(Exception e) {
            System.out.println("ERROR al crear curso:  " + e.getMessage());
            return false;
        }
    }
    
}
