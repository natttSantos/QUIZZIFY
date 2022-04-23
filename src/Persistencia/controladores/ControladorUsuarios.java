package Persistencia.controladores;

import LogicaNegocio.modelo.NotaQuizz;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.QuizSeleccionMultiple;
import LogicaNegocio.modelo.Usuario;
import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
import LogicaNegocio.modelo.NotaQuizz;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import static java.util.Arrays.asList;

import org.bson.Document;

public class ControladorUsuarios {
    
   private MongoCollection usuarios;
    
    public ControladorUsuarios(MongoCollection collection){
        usuarios = collection;
    }


    public Document crearUsuarioAlumno(UsuarioAlumno u) {
        Document d = new Document(); 
        ArrayList<NotaQuizz> aux = u.getNotas();
        Document [] notas = new Document[u.getNotas().size()];
        int i = 0;
        
        for (NotaQuizz nota:aux){
            Document daux = new Document();
            daux.append("quizz", nota.getQuizz())
                    .append("nota", nota.getNota());
            notas[i] = daux;
            i++;
        }
        try {
            d.append("nombre",u.getNombre());
            d.append("apelldios", u.getApellidos());
            d.append("email", u.getEmail());
            d.append("contraseña", u.getContraseña());
            d.append("tipo", "Alumno");
            d.append("notas", asList(notas));
            
            usuarios.insertOne(d);
      
        }catch(Exception e) {
            System.out.println("ERROR al crear usuario de tipo alumno:  " + e.getMessage());
            
        }
       return d; 
    }
     public Document crearUsuarioInstructor(UsuarioInstructor u) {
         Document d = new Document(); 
        try {
            d.append("nombre",u.getNombre());
            d.append("apellidos", u.getApellidos());
            d.append("email", u.getEmail());
            d.append("contraseña", u.getContraseña());
            d.append("tipo", "Instructor");
            d.append("quizzesDisponibles", 20);
            usuarios.insertOne(d);
            
        } catch(Exception e) {
            System.out.println("ERROR al crear usuario de tipo instructor:  " + e.getMessage());
        }
        return d; 
    }
    public boolean modificarUsuarioAlumno(UsuarioAlumno u){
         try {
            Document prev = new Document("email", u.getEmail());
            FindIterable<Document> resultDocument = usuarios.find(prev);
            
            Document d = new Document("nombre",u.getNombre());
            d.append("apellidos", u.getApellidos());
            d.append("email", u.getEmail());
            d.append("contraseña", u.getContraseña());
            d.append("notas", u.getNotas());
            usuarios.updateOne(resultDocument.first(), d);
            return true;
        }catch(Exception e) {
            System.out.println("ERROR al modificar usuario de tipo alumno:  " + e.getMessage());
            return false;
        }
    }
    
    
    public UsuarioAlumno loginAlumno(String email, String contraseña) {
        UsuarioAlumno u = null;
        try {
            
            Document findDocument = new Document("email", email);
            findDocument.append("contraseña", contraseña);
            FindIterable<Document> resultDocument = usuarios.find(findDocument);

            if(resultDocument != null){
                String json =  resultDocument.first().toJson();
                System.out.println(json);
                u = new Gson().fromJson(json, UsuarioAlumno.class);
            }
             
        }catch(Exception e){
            System.out.println("ERROR en login  " + e.getMessage());
        }
       return u; 
    }
    
    public ArrayList<UsuarioAlumno> obtenerTodosUsuariosAlumno() {
        ArrayList<UsuarioAlumno> lista = new ArrayList();
        MongoCursor<Document> cursor = usuarios.find().iterator();
        
        try {
            while (cursor.hasNext()) {
              Document otro = (Document) cursor.next();
              String tipoUser = otro.getString("tipo"); 
              if(tipoUser.equals("Alumno")){
                String json =  otro.toJson();
                UsuarioAlumno u = new Gson().fromJson(json, UsuarioAlumno.class);
                lista.add(u);
              }
            }
        }catch(Exception e){
             System.out.println("ERROR al obtener todos los usuarios alumnos:   " + e.getMessage());
        } finally {
          cursor.close();
        }
        return lista;
    }
    public ArrayList<UsuarioInstructor> obtenerTodosUsuariosInstructores() {
        ArrayList<UsuarioInstructor> lista = new ArrayList();
        MongoCursor<Document> cursor = usuarios.find().iterator();
        
        try {
            while (cursor.hasNext()) {
              Document otro = (Document) cursor.next();
              String tipoUser = otro.getString("tipo"); 
              if(tipoUser.equals("Instructor")){
                String json =  otro.toJson();
                UsuarioInstructor u = new Gson().fromJson(json, UsuarioInstructor.class);
                lista.add(u);
              }
            }
        }catch(Exception e){
             System.out.println("ERROR al obtener todos los usuarios instructores:   " + e.getMessage());
        } finally {
          cursor.close();
        }
        return lista;
    }

    public UsuarioInstructor loginInstructor(String email, String contraseña) {
        UsuarioInstructor u = null;
        try {
            
            Document findDocument = new Document("email", email);
            findDocument.append("contraseña", contraseña);
            FindIterable<Document> resultDocument = usuarios.find(findDocument);

            if(resultDocument != null){
                String json =  resultDocument.first().toJson();
                System.out.println(json);
                u = new Gson().fromJson(json, UsuarioInstructor.class);
            }
             
        }catch(Exception e){
            System.out.println("ERROR en login  " + e.getMessage());
        }
       return u; 
    }
    
      public int reducirCantQuizzesDisponibles(String email) {
        UsuarioInstructor u = null;
        int quizzesDisponibles=0;
        try {
            
            Document findDocument = new Document("email", email);
            FindIterable<Document> resultDocument = usuarios.find(findDocument);

            if(resultDocument != null){
                
                String json =  resultDocument.first().toJson();
                System.out.println(json);
                u = new Gson().fromJson(json, UsuarioInstructor.class);
                
                u.setQuizzesDisponibles(u.getQuizzesDisponibles() - 1);
                

                System.out.println(resultDocument.first().get("_id"));

                Document d = new Document();
                d.append("nombre", u.getNombre());
                d.append("apellidos", u.getApellidos());
                d.append("email", u.getEmail());
                d.append("contraseña", u.getContraseña());
                d.append("tipo", "Instructor");
                d.append("quizzesDisponibles", u.getQuizzesDisponibles() - 1);
                quizzesDisponibles = u.getQuizzesDisponibles() - 1; 
                System.out.println(d);
                System.out.println(resultDocument.first());
                
                usuarios.updateOne(resultDocument.first(), d);
            }
           
        }catch(Exception e){
            System.out.println("ERROR en login  " + e.getMessage());
        }
       return quizzesDisponibles;
    }
      public UsuarioAlumno obtenerUsuarioAlumno(String key, String valor) {
        Document findDocument = new Document(key, valor);
        FindIterable<Document> resultDocument = usuarios.find(findDocument);
        String json =  resultDocument.first().toJson();
        System.out.println(json);
        UsuarioAlumno user = new Gson().fromJson(json, UsuarioAlumno.class);
        return user;
    }
      public void subirNotaQuiz(String quiz, int nota, String usuario, int[] respuestas){
          NotaQuizz notaObjecto = new NotaQuizz(quiz,nota, respuestas);
          UsuarioAlumno alumno = obtenerUsuarioAlumno("nombre",usuario);
          alumno.AddNota(notaObjecto);
          modificarUsuarioAlumno(alumno);        
      }
      
}
