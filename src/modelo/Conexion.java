package modelo;
import Controlador.ControladorUsuarios;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.JOptionPane;

import controlador.ControladorPreguntas;

public class Conexion {
    private static Conexion conexion = null; 
    private static MongoClient instanciaMongo = null;
    private static MongoDatabase db = null;
    //private static final String URL = "mongodb://quizzifyAdmin:admin123@cluster0.auadv.mongodb.net/test?authSource=admin&replicaSet=atlas-v54e0w-shard-0&readPreference=primary&appname=MongoDB%20Compass&ssl=true";
    private final static String URL ="localhost:27017";
    private final String DB = "Quizzify";
    
    ControladorPreguntas cp;
    ControladorUsuarios cu;
    
    MongoCollection preguntas;
    MongoCollection usuarios;
        
    private Conexion() {
        try {
            instanciaMongo = new MongoClient(URL);
            db = instanciaMongo.getDatabase(DB);
            preguntas = db.getCollection("Preguntas");
             preguntas = db.getCollection("Usuarios");
        }catch(MongoException e) {
            JOptionPane.showMessageDialog(null, "Error en conexión a MONGODB " + e.toString());
        }
        cp = new ControladorPreguntas(preguntas);
        cu = new ControladorUsuarios(usuarios);
    }
    
    public static Conexion obtenerConexion() {
        if(conexion == null) {
            conexion = new Conexion();
        }
        return conexion;
    }
    
    public  Pregunta obtenerPregunta(String key, String valor){
       return cp.obtenerPregunta(key, valor);
    }
    
    public void insertarPregunta(String text, String dificultad, String [] respuestas) {
        cp.insertPregunta(text, dificultad, respuestas);
    }
    
    public boolean crearUsuarioAlumno(UsuarioAlumno u) { 
        return cu.crearUsuarioAlumno(u);
    }
    
     public UsuarioAlumno login(String email, String contraseña) {
         return cu.login(email, contraseña);
     }
     
}
