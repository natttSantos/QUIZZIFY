package Persistencia.controladores;

import LogicaNegocio.modelo.OpcionRespuestaSeleccion;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.PreguntaVF;
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
     
    public PreguntaAbstracta obtenerPregunta(String key, String valor) {
        Document findDocument = new Document(key, valor);
        FindIterable<Document> resultDocument = preguntas.find(findDocument);
        String json =  resultDocument.first().toJson();
        System.out.println(json);
        PreguntaAbstracta p = new Gson().fromJson(json, PreguntaSeleccionMultiple.class);
        return p;
    }
    
    public void insertPregunta(PreguntaAbstracta preg) {
        
        Document toInsert = preg.obtenerDocument();
        preguntas.insertOne(toInsert);

    }
    
    
    public ArrayList<PreguntaSeleccionMultiple> obtenerTodasPreguntas() {
        ArrayList<PreguntaSeleccionMultiple> lista = new ArrayList();
        MongoCursor<Document> cursor = preguntas.find().iterator();
        
        try {
            while (cursor.hasNext()) {
              Document otro = (Document) cursor.next();
              String json =  otro.toJson();
              PreguntaSeleccionMultiple pregunta = new Gson().fromJson(json, PreguntaSeleccionMultiple.class);
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
