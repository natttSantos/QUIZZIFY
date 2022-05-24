package Persistencia.controladores;

import LogicaNegocio.modelo.OpcionRespuestaSeleccion;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.PreguntaVF;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.Recurso;
import LogicaNegocio.modelo.RespuestaAbstracta;
import LogicaNegocio.modelo.RespuestaSeleccion;
import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
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
        PreguntaAbstracta p = null; 
        FindIterable<Document> resultDocument = preguntas.find(findDocument);
        String json =  resultDocument.first().toJson();
        p = new Gson().fromJson(json, PreguntaAbstracta.class);
        return p;
    }
    
    public void insertarPregunta(PreguntaAbstracta preg) {
        
        Document toInsert = preg.obtenerDocument();
        preguntas.insertOne(toInsert);

    }
    
    public PreguntaAbstracta obtenerPreguntaSegunTipo(String text){
        Gson gson = new Gson(); 
        ArrayList<PreguntaAbstracta> todasPreguntas = obtenerTodasPreguntas(); 
        PreguntaAbstracta p = null; 
        for(PreguntaAbstracta preg: todasPreguntas){
            if(preg.getText().equals(text)){
                String json =  gson.toJson(preg);
                switch (preg.getTipo()){
                    case "multiple": 
                        p = new Gson().fromJson(json, PreguntaSeleccionMultiple.class);
                        break; 
                    case "vf": 
                         p = new Gson().fromJson(json, PreguntaVF.class);  
                        break; 
                }
            }
           
        }
      return p; 
    }
    
    
     public ArrayList <PreguntaSeleccionMultiple> obtenerPreguntasQuiz_Multiples (QuizAbstracto quiz){    
        Gson gson = new Gson(); 

        String tipoPregunta = null; 
        ArrayList <PreguntaAbstracta> preguntasQuizSelected = quiz.getPreguntas();
        ArrayList <PreguntaSeleccionMultiple> preguntasMultiples = new ArrayList(); 
        
        int i; 
        String json; 
        for(i = 0; i < preguntasQuizSelected.size(); i++ ){
            json = gson.toJson(preguntasQuizSelected.get(i));
            PreguntaSeleccionMultiple preguntaMul = new Gson().fromJson(json, PreguntaSeleccionMultiple.class);
            if(preguntaMul.getTipo().equals("multiple")){
               preguntasMultiples.add(preguntaMul);  
            } 
        }
        return preguntasMultiples; 
    }
    public ArrayList <PreguntaVF> obtenerPreguntasQuiz_VF (QuizAbstracto quiz){    
        Gson gson = new Gson(); 

        String tipoPregunta = null; 
        ArrayList <PreguntaAbstracta> preguntasQuizSelected = quiz.getPreguntas();
        ArrayList <PreguntaVF> preguntasVF = new ArrayList(); 
        
        int i; 
        String json; 
        for(i = 0; i < preguntasQuizSelected.size(); i++ ){
            json = gson.toJson(preguntasQuizSelected.get(i));
            PreguntaSeleccionMultiple preguntaMul = new Gson().fromJson(json, PreguntaSeleccionMultiple.class);
            if(preguntaMul.getTipo().equals("vf")){
               PreguntaVF preguntaVF = new Gson().fromJson(json, PreguntaVF.class);
               preguntasVF.add(preguntaVF); 
            } 
        }
        return preguntasVF; 
    }
    
    
    public ArrayList<PreguntaAbstracta> obtenerTodasPreguntas() {
        ArrayList<PreguntaAbstracta> lista = new ArrayList();
        MongoCursor<Document> cursor = preguntas.find().iterator();
        
        try {
            while (cursor.hasNext()) {
              Document otro = (Document) cursor.next();
              String tipoPregunta = otro.getString("tipo"); 
              String json =  otro.toJson();
              if (tipoPregunta.equals("multiple")){
                  PreguntaSeleccionMultiple preguntaSel = new Gson().fromJson(json, PreguntaSeleccionMultiple.class);
                  lista.add(preguntaSel);
              }
              if (tipoPregunta.equals("vf")){
                  PreguntaVF preguntaVF= new Gson().fromJson(json, PreguntaVF.class);
                  lista.add(preguntaVF);
              }
           }
        }catch(Exception e){
             System.out.println("ERROR al obtener todas las preguntas:   " + e.getMessage());
        } finally {
          cursor.close();
        }
        return lista;
    }

    public ArrayList<PreguntaAbstracta> obtenerTodasPreguntasDeRecurso(String nombreRecurso, UsuarioInstructor instructorConectado){
         ArrayList<PreguntaAbstracta> listaPreguntas = obtenerTodasPreguntas(); 
         ArrayList<PreguntaAbstracta> preguntasDeRecurso  = new ArrayList<>(); 
         for (PreguntaAbstracta preg: listaPreguntas){
             if (preg.getRecurso().getNombreRecurso().equals(nombreRecurso)
                     && preg.getRecurso().getInstructorEnRecurso().getEmail().equals(instructorConectado.getEmail())){
                 preguntasDeRecurso.add(preg); 
             }
         }
         return preguntasDeRecurso; 
     }
    
    public boolean modificarDificultad(String dificultad, PreguntaAbstracta pregunta){
         try {
            pregunta.setDificultad(dificultad);
            Document preguntaAux = pregunta.obtenerDocument();
            Document query = new Document().append("text",pregunta.getText());
            
            preguntas.replaceOne(query, preguntaAux);
            return true;
         } catch(Exception e){
             System.out.println("ERROR en login  " + e.getMessage());
            return false;
         }
     }
    
    public boolean modificarPuntuacion(Double puntuacion, PreguntaAbstracta pregunta){
         try {
            pregunta.setPuntos(puntuacion);
            Document preguntaAux = pregunta.obtenerDocument();
            Document query = new Document().append("text",pregunta.getText());
            
            preguntas.replaceOne(query, preguntaAux);
            return true;
         } catch(Exception e){
             System.out.println("ERROR en login  " + e.getMessage());
            return false;
         }
     }

}
