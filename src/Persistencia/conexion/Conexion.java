package Persistencia.conexion;
import LogicaNegocio.modelo.Curso;
import Persistencia.controladores.ControladorUsuarios;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.JOptionPane;

import Persistencia.controladores.ControladorPreguntas;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.RespuestaSeleccion;
import LogicaNegocio.modelo.Usuario;
import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.controladores.ControladorCursos;
import Persistencia.controladores.ControladorQuizzes;
import java.util.ArrayList;
import org.bson.Document;

public class Conexion {
    private static Conexion conexion = null; 
    private static MongoClient instanciaMongo = null;
    private static MongoDatabase db = null;
    //private static final String URL = "mongodb://quizzifyAdmin:admin123@cluster0.auadv.mongodb.net/test?authSource=admin&replicaSet=atlas-v54e0w-shard-0&readPreference=primary&appname=MongoDB%20Compass&ssl=true";
    private final static String URL ="localhost:27017";
    private final String DB = "Quizzify";
    
    ControladorPreguntas cp;
    ControladorUsuarios cu;
    ControladorQuizzes cq;
    ControladorCursos cc; 
    
    MongoCollection preguntas;
    MongoCollection usuarios;
    MongoCollection quizzes;
    MongoCollection cursos;
        
    private Conexion() {
        try {
            instanciaMongo = new MongoClient(URL);
            db = instanciaMongo.getDatabase(DB);
            preguntas = db.getCollection("Preguntas");
            usuarios = db.getCollection("Usuarios");
            quizzes = db.getCollection("Quizzes");
            cursos = db.getCollection("Cursos");
             
        }catch(MongoException e) {
            JOptionPane.showMessageDialog(null, "Error en conexión a MONGODB " + e.toString());
        }
        cp = new ControladorPreguntas(preguntas);
        cu = new ControladorUsuarios(usuarios);
        cq = new ControladorQuizzes(quizzes);
        cc = new ControladorCursos(cursos);
    }
    
    public static Conexion obtenerConexion() {
        if(conexion == null) {
            conexion = new Conexion();
        }
        return conexion;
    }
    
    public PreguntaAbstracta obtenerPregunta(String key, String valor){
       return cp.obtenerPregunta(key, valor);
    }
    
    public void insertarPregunta(PreguntaAbstracta p) {
        cp.insertPregunta(p);
    }
    
    public Document crearUsuarioAlumno(UsuarioAlumno u) { 
        return cu.crearUsuarioAlumno(u);
    }
    
    public Document crearUsuarioInstructor(UsuarioInstructor u) {
        return cu.crearUsuarioInstructor(u);
    }
    
    public UsuarioAlumno login(String email, String contraseña) {
        return cu.loginAlumno(email, contraseña);
    }
     public UsuarioInstructor loginInstructor(String email, String contraseña) {
        return cu.loginInstructor(email, contraseña);
    }
    
    public ArrayList<PreguntaSeleccionMultiple> obtenerTodasPreguntas() {
        return cp.obtenerTodasPreguntas();
    }
    
    public void insertarQuiz(String text, Document [] preguntas){
        cq.insertarQuiz(text, preguntas);
    }
    
    public QuizAbstracto obtenerQuiz(String key, String valor){
       return cq.obtenerQuiz(key, valor);
    }
    public ArrayList<QuizAbstracto> obtenerTodosQuizzes() {
        return cq.obtenerTodosLosQuizzes();
    }
    
    public int reducirCantQuizzesDisponibles(String email) {
        return cu.reducirCantQuizzesDisponibles(email);

    }
 
    public ArrayList<UsuarioAlumno> obtenerTodosUsuariosAlumno() {
        return cu.obtenerTodosUsuariosAlumno();
    }
    public ArrayList<UsuarioInstructor> obtenerTodosUsuariosInstructores() {
        return cu.obtenerTodosUsuariosInstructores();
    }
    public UsuarioAlumno obtenerUsuarioAlumno(String key, String valor){
       return cu.obtenerUsuarioAlumno(key, valor);
    }
     public boolean crearCurso(Curso c) { 
        return cc.crearCurso(c);
    }
    public boolean crearCursosSinQuiz(String nombre, Document[] estudiantes, Document instructor) {
         return cc.crearCursosSinQuiz(nombre, estudiantes, instructor);
    }
     
    
}
