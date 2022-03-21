/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.controladores;

import LogicaNegocio.modelo.Pregunta;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Persistencia.conexion.Conexion;
import LogicaNegocio.modelo.Quiz;
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
    
    
    public void insertarQuiz(String nombre, Document[] preguntas) {
        Document quiz = new Document();
        quiz.append("nombre", nombre)
                .append("preguntas", asList(preguntas));
        
        quizzes.insertOne(quiz);
    }
     public ArrayList<Quiz> obtenerTodosLosQuizzes() {
        ArrayList<Quiz> lista = new ArrayList();
        MongoCursor<Document> cursor = quizzes.find().iterator();
        
        try {
            while (cursor.hasNext()) {
              Document otro = (Document) cursor.next();
              String json =  otro.toJson();
              Quiz quiz = new Gson().fromJson(json, Quiz.class);
              lista.add(quiz);
            }
        }catch(Exception e){
             System.out.println("ERROR al obtener todos los quizzes:   " + e.getMessage());
        } finally {
          cursor.close();
        }
        return lista;
    }
    
}
