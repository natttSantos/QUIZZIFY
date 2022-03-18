package Persistencia.controladores;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import LogicaNegocio.modelo.Pregunta;
import LogicaNegocio.modelo.RespuestaSeleccion;
import LogicaNegocio.modelo.UsuarioAlumno;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import org.bson.Document;

public class ControladorPreguntas {
    MongoCollection preguntas;
    
    public ControladorPreguntas(MongoCollection collection){
       preguntas = collection;
    }
     
    public Pregunta obtenerPregunta(String key, String valor) {
        Document findDocument = new Document(key, valor);
        FindIterable<Document> resultDocument = preguntas.find(findDocument);
        String json =  resultDocument.first().toJson();
        System.out.println(json);
        Pregunta p = new Gson().fromJson(json, Pregunta.class);
        return p;
    }
     
   /* public void insertPregunta(String text, String dificultad, String [] respuestas) {
         
       Document [] d  = new Document[respuestas.length]; 
      
       for(int i = 0; i< respuestas.length;i++){
           if(respuestas[i] != null){
               d[i] = new Document("resp",respuestas[i]);
              
           }
       }
       Document p = new Document();
          p.append("text", text)
                 .append("dificultad", dificultad)
                 .append("respuestas", asList(d));
        preguntas.insertOne(p);
    }*/
    
    
    public void insertPregunta(String text, String dificultad,String tema,  RespuestaSeleccion respuesta) {
         
        Document [] d  = new Document[respuesta.getOpciones().size()]; 
      
        for(int i = 0; i< respuesta.getOpciones().size();i++){
           if(respuesta.getOpciones().get(i) != null){
               d[i] = new Document("text",respuesta.getOpciones().get(i))
                       .append("correcta",respuesta.getOpciones_correctas().get(i));
             
              
           }
        }
      
        Document p = new Document();
            p.append("text", text)
            .append("dificultad", dificultad)
            .append("tema", tema) 
            .append("respuestas", asList(d));
          
        preguntas.insertOne(p);
    }
    
    public ArrayList<Pregunta> obtenerTodasPreguntas() {
        ArrayList<Pregunta> lista = new ArrayList();
        MongoCursor<Document> cursor = preguntas.find().iterator();
        
        try {
            while (cursor.hasNext()) {
              Document otro = (Document) cursor.next();
              String json =  otro.toJson();
              Pregunta pregunta = new Gson().fromJson(json, Pregunta.class);
              lista.add(pregunta);
            }
        }catch(Exception e){
             System.out.println("ERROR al obtener todas las preguntas:   " + e.getMessage());
        } finally {
          cursor.close();
        }
        return lista;
    }

}
