/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.OpcionRespuestaSeleccion;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.QuizSeleccionMultiple;
import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
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
    public void insertarCurso (Curso curso) {
        Document toInsert = curso.obtenerDocument();
        cursos.insertOne(toInsert);
    }

    public ArrayList<Curso> obtenerTodosLosCursos() {
        ArrayList<Curso> lista = new ArrayList();
        MongoCursor<Document> cursor = cursos.find().iterator();
        
        try {
            while (cursor.hasNext()) {
              Document otro = (Document) cursor.next();
              String json =  otro.toJson();
              Curso c = new Gson().fromJson(json, Curso.class);
              lista.add(c);
            }
        }catch(Exception e){
             System.out.println("ERROR al obtener todas los cursos:   " + e.getMessage());
        } finally {
          cursor.close();
        }
        return lista;
    }
    
    public ArrayList <Curso> obtenerCursosDeInstructor(UsuarioInstructor instructorConectado){
       ArrayList <Curso> listCursos = obtenerTodosLosCursos(); 
       ArrayList <Curso> cursosInstructor = new ArrayList<>(); 
       for(Curso curso: listCursos){
           UsuarioInstructor instructorCurso = curso.getInstructorEnCurso(); 
           if(instructorConectado.getEmail().equals(instructorCurso.getEmail())){
               cursosInstructor.add(curso); 
           }
       }
       return cursosInstructor; 
    }
    
    public ArrayList <Curso> obtenerCursosDeEstudiante(UsuarioAlumno estudianteConectado){
       ArrayList <Curso> listCursos = obtenerTodosLosCursos(); 
       ArrayList <Curso> cursosEstudiante = new ArrayList<>(); 
       for(Curso curso: listCursos){
           ArrayList <UsuarioAlumno> estudiantesCurso = curso.getAlumnosEnCurso(); 
           for(UsuarioAlumno estudianteCurso : estudiantesCurso){
                if(estudianteConectado.getEmail().equals(estudianteCurso.getEmail())){
                    cursosEstudiante.add(curso); 
                }
           
            }
       }
       return cursosEstudiante; 
    }
    
    public Curso obtenerCurso(String key, String valor) {
        Document findDocument = new Document(key, valor);
        FindIterable<Document> resultDocument = cursos.find(findDocument);
        String json =  resultDocument.first().toJson();
        System.out.println(json);
        Curso curso = new Gson().fromJson(json, Curso.class);
        return curso;
    }
}
