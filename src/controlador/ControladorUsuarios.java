package Controlador;

import modelo.UsuarioAlumno;
import com.google.gson.Gson;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;

import org.bson.Document;

public class ControladorUsuarios {
    
    MongoCollection usuariosAlumno;
    
    public ControladorUsuarios(MongoCollection collection){
        usuariosAlumno = collection;
    }


    public boolean crearUsuarioAlumno(UsuarioAlumno u) {
        
        try {
            Document d = new Document("nombre",u.getNombre());
            d.append("apelldios", u.getApellidos());
            d.append("email", u.getEmail());
            d.append("contraseña", u.getContraseña());
            d.append("grupo", u.getGrupo());
            d.append("curso", u.getCurso());
            usuariosAlumno.insertOne(d);
            return true;
        }catch(Exception e) {
            System.out.println("ERROR al crear usuario de tipo alumno:  " + e.getMessage());
            return false;
        }
    }
    
    public boolean modificarUsuarioAlumno(UsuarioAlumno u){
         try {
            Document prev = new Document("email", u.getEmail());
            FindIterable<Document> resultDocument = usuariosAlumno.find(prev);
            
            Document d = new Document("nombre",u.getNombre());
            d.append("apellidos", u.getApellidos());
            d.append("email", u.getEmail());
            d.append("contraseña", u.getContraseña());
            d.append("grupo", u.getGrupo());
            d.append("curso", u.getCurso());
            usuariosAlumno.updateOne(resultDocument.first(), d);
            return true;
        }catch(Exception e) {
            System.out.println("ERROR al modificar usuario de tipo alumno:  " + e.getMessage());
            return false;
        }
    }
    
    
    public UsuarioAlumno login(String email, String contraseña) {
        UsuarioAlumno u = null;
        try {
            
            Document findDocument = new Document("email", email);
            findDocument.append("contraseña", contraseña);
            FindIterable<Document> resultDocument = usuariosAlumno.find(findDocument);

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
        MongoCursor<Document> cursor = usuariosAlumno.find().iterator();
        
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
}
