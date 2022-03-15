package main;

import LogicaNegocio.modelo.UsuarioAlumno;
import Persistencia.conexion.Conexion;
import LogicaNegocio.modelo.Pregunta;
import Persistencia.controladores.ControladorPreguntas;
import com.mongodb.MongoClient;
import java.util.ArrayList;

import org.bson.Document;

public class NewMain {

    static ControladorPreguntas controlador;
    public static void main(String[] args) {
        
        Conexion c = Conexion.obtenerConexion(); 
        if(c != null) {
            
            
            //UsuarioAlumno u = new UsuarioAlumno("test","aa","email@email.com","contraseña","grupo","clase");
           // System.out.println(c.crearUsuarioAlumno(u));
            
            
           //Pregunta p = c.obtenerPregunta("text", "pregunta 2");
           //System.out.println(p.getDificultad());
           
           
           
           String email ="ervino@alumno.upv.es";
           String pass = "123";
           
           UsuarioAlumno u = c.login(email, pass);
           if(u != null) {
               System.out.println(u.toString());
           } else {
               System.out.println("Usuario Y/O CONTRASEÑA INCORRECTOS");
           }
           
           
           //ArrayList a = c.obtenerTodosUsuariosAlumno();
           //System.out.println(a.get(0).toString());
           /*Document [] d = p.getRespuestas();
           System.out.println("a" + d.length);
           System.out.println(d[0]);
           System.out.println(d[1]);*/
           
           //String [] respe = {"Verdadero","Falso"};
           //controlador.insertPregunta("Responda si la afirmacion siguiente es cierta: Hola que tal", "alta", respe);
        }
    }

}
