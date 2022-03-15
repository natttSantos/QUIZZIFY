package Persistencia.controladores;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import LogicaNegocio.modelo.Pregunta;
import org.bson.Document;
import static java.util.Arrays.asList;

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
     
    public void insertPregunta(String text, String dificultad, String [] respuestas) {
         
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
    }

}
