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
import LogicaNegocio.modelo.Recurso;
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
public class ControladorRecursos {
    
     MongoCollection recursos;
    
    public ControladorRecursos(MongoCollection collection){
       recursos = collection;
    }
    public void insertarRecurso (Recurso recurso) {
        Document toInsert = recurso.obtenerDocument();
        recursos.insertOne(toInsert);
    }

    public ArrayList<Recurso> obtenerTodosLosRecursos() {
        ArrayList<Recurso> lista = new ArrayList();
        MongoCursor<Document> cursor = recursos.find().iterator();
        
        try {
            while (cursor.hasNext()) {
              Document otro = (Document) cursor.next();
              String json =  otro.toJson();
              Recurso c = new Gson().fromJson(json, Recurso.class);
              lista.add(c);
            }
        }catch(Exception e){
             System.out.println("ERROR al obtener todas los cursos:   " + e.getMessage());
        } finally {
          cursor.close();
        }
        return lista;
    }
    
    public ArrayList <Recurso> obtenerRecursosDeInstructor(UsuarioInstructor instructorConectado){
       ArrayList <Recurso> listCursos = obtenerTodosLosRecursos(); 
       ArrayList <Recurso> cursosInstructor = new ArrayList<>(); 
       for(Recurso recurso: listCursos){
           UsuarioInstructor instructorCurso = recurso.getInstructorEnRecurso(); 
           if(instructorConectado.getEmail().equals(instructorCurso.getEmail())){
               cursosInstructor.add(recurso); 
           }
       }
       return cursosInstructor; 
    }
    
    
    public Recurso obtenerRecurso(String key, String valor) {
        Document findDocument = new Document(key, valor);
        FindIterable<Document> resultDocument = recursos.find(findDocument);
        String json =  resultDocument.first().toJson();
        Recurso recurso = new Gson().fromJson(json, Recurso.class);
        return recurso;
    }
}
