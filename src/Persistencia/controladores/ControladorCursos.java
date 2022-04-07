/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.UsuarioAlumno;
import com.mongodb.client.MongoCollection;
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
    public void crearCurso(Curso c) {
          Document d = new Document("nombre",c.getNombreCurso());
          d.append("instructor", c.getInstructorEnCurso());
          d.append("alumnos", c.getAlumnosEnCurso());
          d.append("quizzes", c.getQuizzesEnCurso()); 
          cursos.insertOne(d);  
    }
    
}
