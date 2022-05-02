/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.PreguntaAbstracta;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Persistencia.conexion.Conexion;
import LogicaNegocio.modelo.QuizAbstracto;
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
            while (cursor.hasNext()) {
              Document otro = (Document) cursor.next();
              String json =  otro.toJson();
              QuizAbstracto quiz = new Gson().fromJson(json, QuizAbstracto.class);
              lista.add(quiz);
            }
        return lista;
    }
    public QuizAbstracto obtenerQuiz(String key, String valor) {
        QuizAbstracto quiz = null;
        Document findDocument = new Document(key, valor);
        System.out.println(findDocument);
        FindIterable<Document> resultDocument = quizzes.find(findDocument);
        
        MongoCursor<Document> cursor = resultDocument.iterator();
        if (cursor.hasNext()) {
            String json =  resultDocument.first().toJson();
            System.out.println(json);
            quiz = new Gson().fromJson(json, QuizAbstracto.class);
            System.out.println(quiz.getNombre());
        }
        return quiz;
    }
    
    public ArrayList<QuizAbstracto> obtenerQuizzesDeCurso(Curso curso){
        ArrayList<QuizAbstracto> quizzes = obtenerTodosLosQuizzes();
        ArrayList<QuizAbstracto> quizzesCurso = new ArrayList();
        for (QuizAbstracto quiz:quizzes) {
            if (quiz.getCurso().getNombreCurso().equals(curso.getNombreCurso())) {
                quizzesCurso.add(quiz);
            }
        }
        return quizzesCurso;
    }
    
}
