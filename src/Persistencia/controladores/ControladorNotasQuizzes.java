/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.controladores;

import LogicaNegocio.modelo.Curso;
import Persistencia.conexion.Conexion;
import LogicaNegocio.modelo.NotaQuizz;
import LogicaNegocio.modelo.PreguntaRespondida;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.UsuarioAlumno;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.FindIterable;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author crivi
 */
public class ControladorNotasQuizzes {
    MongoCollection notas;
    Conexion con;
    public ControladorNotasQuizzes(MongoCollection collection) {
        this.notas = collection;
    }
    
    public void insertarNota (NotaQuizz nota){
        Document toInsert = nota.obtenerDocument();
        notas.insertOne(toInsert);
    }
    
    public ArrayList<NotaQuizz> obtenerTodasLasNotas() {
        ArrayList<NotaQuizz> lista = new ArrayList();
        MongoCursor<Document> cursor = notas.find().iterator();
        
        try {
            while (cursor.hasNext()) {
              Document otro = (Document) cursor.next();
              String json =  otro.toJson();
              NotaQuizz n = new Gson().fromJson(json, NotaQuizz.class);
              lista.add(n);
            }
        }catch(Exception e){
             System.out.println("ERROR al obtener todas las notas:   " + e.getMessage());
        } finally {
          cursor.close();
        }
        return lista;
    }
    
    public ArrayList<NotaQuizz> obtenerNotasDeQuiz (QuizAbstracto quiz){
        ArrayList<NotaQuizz> listaNotas = obtenerTodasLasNotas();
        ArrayList<NotaQuizz> notasDeQuizz = new ArrayList();
        for (NotaQuizz nota: listaNotas){
            String aux = nota.getQuizz();
            if (quiz.getNombre().equals(aux)){
                notasDeQuizz.add(nota);
            }
        }
        return notasDeQuizz;
    }
    
    public NotaQuizz obtenerRespuestasDeQuizDeAlumno (UsuarioAlumno alumno, QuizAbstracto quiz){
        ArrayList<NotaQuizz> notas = obtenerNotasDeQuiz(quiz);
        for (NotaQuizz nota:notas){
            String aux = nota.getAlumno();
            if (alumno.getEmail().equals(aux)) {
                return nota;    
            }
        }
        return null;
    }
}
