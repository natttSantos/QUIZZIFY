package Persistencia.controladores;

import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
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


    public boolean crearUsuarioAlumno(UsuarioAlumno u) {
        
        try {
            Document d = new Document("nombre",u.getNombre());
            d.append("apelldios", u.getApellidos());
            d.append("email", u.getEmail());
            d.append("contraseña", u.getContraseña());
            d.append("grupo", u.getGrupo());
            d.append("curso", u.getCurso());
            usuarios.insertOne(d);
            return true;
        }catch(Exception e) {
            System.out.println("ERROR al crear usuario de tipo alumno:  " + e.getMessage());
            return false;
        }
    }
    
    public boolean modificarUsuarioAlumno(UsuarioAlumno u){
         try {
            Document prev = new Document("email", u.getEmail());
            FindIterable<Document> resultDocument = usuarios.find(prev);
            
            Document d = new Document("nombre",u.getNombre());
            d.append("apellidos", u.getApellidos());
            d.append("email", u.getEmail());
            d.append("contraseña", u.getContraseña());
            d.append("grupo", u.getGrupo());
            d.append("curso", u.getCurso());
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
              String json =  otro.toJson();
              UsuarioAlumno u = new Gson().fromJson(json, UsuarioAlumno.class);
              lista.add(u);
            }
        }catch(Exception e){
             System.out.println("ERROR al obtener todos los usuarios alumnos:   " + e.getMessage());
        } finally {
          cursor.close();
        }
        return lista;
    }
    
    public boolean crearUsuarioInstructor(UsuarioInstructor u) {
        
        try {
            Document d = new Document("nombre",u.getNombre());
            d.append("apelldios", u.getApellidos());
            d.append("email", u.getEmail());
            d.append("contraseña", u.getContraseña());
            
            
            String[] cursos = u.getCursos();
            Document [] cursosDocument  = new Document[cursos.length]; 
      
            for(int i = 0; i< cursos.length; i++){
                if(cursos[i] != null){
                    cursosDocument[i] = new Document("cursos",cursos[i]);
                }    
            }
            
            d.append("cursos", asList(cursosDocument));
            usuarios.insertOne(d);
            return true;
            
        } catch(Exception e) {
            System.out.println("ERROR al crear usuario de tipo instructor:  " + e.getMessage());
            return false;
        }
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
}
