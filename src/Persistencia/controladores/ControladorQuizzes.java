/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.controladores;

import LogicaNegocio.modelo.PreguntaAbstracta;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Persistencia.conexion.Conexion;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.QuizSeleccionMultiple;
import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collections;
import org.bson.Document;

public class ControladorQuizzes {
    MongoCollection quizzes;
    
    Conexion con;
    public ControladorQuizzes(MongoCollection collection){
        this.quizzes = collection;
    }
    
    
    public void insertarQuiz(String nombre, Document curso, Document [] preguntas) {
        Document quiz = new Document();
        quiz.append("nombre", nombre)
            .append ("curso", curso)
            .append("preguntas", asList(preguntas));
        
        quizzes.insertOne(quiz);
    }
     public ArrayList<QuizAbstracto> obtenerTodosLosQuizzes() {
        ArrayList<QuizAbstracto> lista = new ArrayList();
        MongoCursor<Document> cursor = quizzes.find().iterator();
        
        try {
            while (cursor.hasNext()) {
              Document otro = (Document) cursor.next();
              String json =  otro.toJson();
              QuizAbstracto quiz = new Gson().fromJson(json, QuizSeleccionMultiple.class);
              lista.add(quiz);
            }
        }catch(Exception e){
             System.out.println("ERROR al obtener todos los quizzes:   " + e.getMessage());
        } finally {
          cursor.close();
        }
        return lista;
    }
    public QuizAbstracto obtenerQuiz(String key, String valor) {
        Document findDocument = new Document(key, valor);
        FindIterable<Document> resultDocument = quizzes.find(findDocument);
        String json =  resultDocument.first().toJson();
        System.out.println(json);
        QuizAbstracto quiz = new Gson().fromJson(json, QuizSeleccionMultiple.class);
        return quiz;
    }
    
}
