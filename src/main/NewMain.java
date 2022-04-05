package main;

import LogicaNegocio.modelo.UsuarioAlumno;
import Persistencia.conexion.Conexion;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.RespuestaSeleccion;
import Persistencia.controladores.ControladorPreguntas;
import com.mongodb.MongoClient;
import java.util.ArrayList;

import org.bson.Document;

public class NewMain {
    
    public static void main(String[] args) {
         
        Conexion c = Conexion.obtenerConexion(); 
         System.out.println("aaa");
        if(c != null) {
          System.out.println("aaa");
           
            //UsuarioAlumno u = new UsuarioAlumno("prueba","gg","email@email.com","contraseña2","grupo1","clase1");
            //System.out.println(u.toString());
            //System.out.println(c.crearUsuarioAlumno(u));
            
            
           //Pregunta p = c.obtenerPregunta("text", "pregunta 2");
           //System.out.println(p.getDificultad());
           
           
           
           //String email ="ervino@alumno.upv.es";
           //String pass = "123";
           
           //UsuarioAlumno u = c.login(email, pass);
           //if(u != null) {
           //    System.out.println(u.toString());
           //////}
           
           
           //ArrayList a = c.obtenerTodosUsuariosAlumno();
           //System.out.println(a.get(0).toString());
           /*Document [] d = p.getRespuestas();
           System.out.println("a" + d.length);
           System.out.println(d[0]);
           System.out.println(d[1]);*/
           
           //String [] respe = {"Verdadero","Falso"};
           
            ArrayList<String> opciones = new ArrayList();
            opciones.add("una");
            opciones.add("dos");
            opciones.add("tes");
             
            ArrayList<Boolean> correctas = new ArrayList();
            correctas.add(true);
            correctas.add(false);
            correctas.add(false);
           
           //RespuestaSeleccion r = new RespuestaSeleccion("RespuestaSeleccion1",opciones, correctas);
         
           //c.insertarPregunta("abc", "alta","PSW", r);
        }
    }

}
