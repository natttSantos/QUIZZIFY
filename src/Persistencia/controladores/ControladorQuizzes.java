/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.controladores;

import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.FechaQuiz;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.PreguntaVF;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Persistencia.conexion.Conexion;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.QuizDeBateria;
import LogicaNegocio.modelo.Recurso;
import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collections;
import org.bson.Document;
import org.bson.conversions.Bson;

public class ControladorQuizzes {
    MongoCollection quizzes;
    
    Conexion con;
    public ControladorQuizzes(MongoCollection collection){
        this.quizzes = collection;
    }
    
    
    public void insertarQuiz(String nombre, Document curso, Document [] preguntas,  LocalDate dateInicio, LocalDate dateFin, int tiempoLimite) {
        Document quiz = new Document();
        FechaQuiz fechaInicioQuiz = new FechaQuiz(dateInicio.getYear(), dateInicio.getMonthValue(), dateInicio.getDayOfMonth()); 
        FechaQuiz fechaFinQuiz = new FechaQuiz(dateFin.getYear(), dateFin.getMonthValue(), dateFin.getDayOfMonth()); 
        quiz.append("nombre", nombre)
            .append ("curso", curso)
            .append("preguntas", asList(preguntas))
            .append("fechaInicio", fechaInicioQuiz.obtenerDocument()) 
            .append("fechaFin", fechaFinQuiz.obtenerDocument())
            .append("tiempoLimite", tiempoLimite); 

        quizzes.insertOne(quiz);
    }
    
    public void insertarQuizDeBateria(String nombre, int numero, Document curso, String estado, Recurso recurso, LocalDate dateInicio, LocalDate dateFin, int tiempoLimite) {
        /**
         * Insertar quiz sin preguntas, vinculado con recurso. 
         * Preguntas de quiz de este tipo se sortea justo antes resolver para cada alumno
         * Argumentos
         * nombre : String
         *      nombre de quiz
         * numero : int
         *      numero de preguntas que se elegirán aleatoriamente al responder quiz
         * curso : Documento
         *      documento con info sobre el curso
         * estado : String
         *      estado de quiz (por ejemplo "en preparacion")
         * recurso : Recurso
         *      recurso con que este quiz es vinculado
         */
        Document quiz = new Document();
        FechaQuiz fechaInicioQuiz = new FechaQuiz(dateInicio.getYear(), dateInicio.getMonthValue(), dateInicio.getDayOfMonth()); 
        FechaQuiz fechaFinQuiz = new FechaQuiz(dateFin.getYear(), dateFin.getMonthValue(), dateFin.getDayOfMonth());
        
        quiz.append("nombre", nombre);
        quiz.append("numero", numero);
        quiz.append ("curso", curso);
        quiz.append("recurso", recurso.obtenerDocument());
        quiz.append("fechaInicio", fechaInicioQuiz.obtenerDocument()) ;
        quiz.append("fechaFin", fechaFinQuiz.obtenerDocument());
        quiz.append("tiempoLimite", tiempoLimite);
        
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
    
    public QuizDeBateria obtenerQuizDeBateria(String key, String valor) {
        QuizDeBateria quiz = null;
        Document findDocument = new Document(key, valor);
        FindIterable<Document> resultDocument = quizzes.find(findDocument);
        
        MongoCursor<Document> cursor = resultDocument.iterator();
        if (cursor.hasNext()) {
            String json =  resultDocument.first().toJson();
            quiz = new Gson().fromJson(json, QuizDeBateria.class);
        }
        return quiz;      
    }
    
    public boolean comprobarTipoDeQuiz(String key, String valor) {
        /**
         * Devuelve true si este quiz es quiz de bateria, es decir no contiene preguntas
         * Preguntas de este quiz se elige aleatoriamente en el momento de responder
         */
        Document findDocument = new Document(key, valor);
        FindIterable<Document> resultDocument = quizzes.find(findDocument);
        return resultDocument.first().containsKey("recurso");
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
    //areglar estados
//    public void cambiarEstado(String nombreQuiz,String estado) {
//        QuizAbstracto quiz = obtenerQuiz("nombre",nombreQuiz);
//        ArrayList<PreguntaAbstracta> lista = quiz.getPreguntas();
//            Document[] preguntas = new Document[lista.size()];
//            int i = 0;
//            for (PreguntaAbstracta pregunta:lista){
//                Document d = new Document();
//                d.append("text", pregunta.getText())
//                    .append("dificultad", pregunta.getDificultad())
//                    .append("recurso", pregunta.getRecurso()) 
//                    .append("respuestas", asList(pregunta.getRespuestas()));
//                preguntas[i] = d;
//                i++;
//        }        
//        insertarQuiz(quiz.getNombre(), quiz.getCurso().obtenerDocument(), estado, preguntas);
//        Document quizDocument = new Document();
//        quizDocument.append("nombre", quiz.getNombre())
//            .append ("curso", quiz.getCurso().obtenerDocument())
//            .append("estado", quiz.getEstado())
//            .append("preguntas", asList(preguntas));
//        quizzes.deleteOne(quizDocument);
//    }
    
    public boolean anularPregunta(QuizAbstracto quiz, Document[] preguntas){           
        try {
            Document prev = new Document("nombre", quiz.getNombre());
            FindIterable<Document> resultDocument = quizzes.find(prev);
            Document query = new Document().append("nombre", quiz.getNombre());
            Document quizAux = new Document();
            quizAux.append("nombre", quiz.getNombre())
                .append ("curso", quiz.getCurso().obtenerDocument())
                .append("preguntas", asList(preguntas))
                .append("fechaInicio", quiz.getFechaInicio())
                .append("fechaFin", quiz.getFechaFin());
            quizzes.replaceOne(query,quizAux);
            return true;
        }catch(Exception e){
            System.out.println("ERROR en login  " + e.getMessage());
            return false;
        }
    }
}
