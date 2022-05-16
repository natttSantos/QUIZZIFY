package Persistencia.conexion;
import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.NotaQuizz;
import Persistencia.controladores.ControladorUsuarios;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.JOptionPane;

import Persistencia.controladores.ControladorPreguntas;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaRespondida;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.PreguntaVF;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.Recurso;
import LogicaNegocio.modelo.RespuestaSeleccion;
import LogicaNegocio.modelo.Usuario;
import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.controladores.ControladorCursos;
import Persistencia.controladores.ControladorNotasQuizzes;
import Persistencia.controladores.ControladorQuizzes;
import Persistencia.controladores.ControladorRecursos;
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
    ControladorRecursos cr;
    ControladorNotasQuizzes cn;
    
    MongoCollection preguntas;
    MongoCollection usuarios;
    MongoCollection quizzes;
    MongoCollection cursos;
    MongoCollection recursos;
    MongoCollection notas;
        
    private Conexion() {
        try {
            instanciaMongo = new MongoClient(URL);
            db = instanciaMongo.getDatabase(DB);
            preguntas = db.getCollection("Preguntas");
            usuarios = db.getCollection("Usuarios");
            quizzes = db.getCollection("Quizzes");
            cursos = db.getCollection("Cursos");
            recursos = db.getCollection("Recursos");
            notas = db.getCollection("Notas");
             
        }catch(MongoException e) {
            JOptionPane.showMessageDialog(null, "Error en conexión a MONGODB " + e.toString());
        }
        cp = new ControladorPreguntas(preguntas);
        cu = new ControladorUsuarios(usuarios);
        cq = new ControladorQuizzes(quizzes);
        cc = new ControladorCursos(cursos);
        cr = new ControladorRecursos(recursos);
        cn = new ControladorNotasQuizzes(notas);
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
    
    public PreguntaAbstracta obtenerPreguntaSegunTipo(String text){
        return cp.obtenerPreguntaSegunTipo(text); 
    }
    
    public void insertarPregunta(PreguntaAbstracta p) {
        cp.insertarPregunta(p);
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
    
    public ArrayList<PreguntaAbstracta> obtenerTodasPreguntas() {
        return cp.obtenerTodasPreguntas();
    }
    public ArrayList<PreguntaAbstracta> obtenerTodasPreguntasDeRecurso(String nombreRecurso, UsuarioInstructor instructorConectado){
        return cp.obtenerTodasPreguntasDeRecurso(nombreRecurso, instructorConectado);
    }
     public ArrayList <PreguntaVF> obtenerPreguntasQuiz_VF (QuizAbstracto quiz){
         return cp.obtenerPreguntasQuiz_VF(quiz); 
     }
     
        public ArrayList <PreguntaSeleccionMultiple> obtenerPreguntasQuiz_Multiples (QuizAbstracto quiz){
         return cp.obtenerPreguntasQuiz_Multiples (quiz); 
     }
    
    public void insertarQuiz(String text, Document curso, String estado, Document [] preguntas){
        cq.insertarQuiz(text, curso, estado, preguntas);
    }
    
    public void insertarQuizDeBateria(String text, int numero, Document curso, String estado, Recurso recurso) {
        cq.insertarQuizDeBateria(text, numero, curso, estado, recurso);
    }
    
    public QuizAbstracto obtenerQuiz(String key, String valor){
       return cq.obtenerQuiz(key, valor);
    }
    public ArrayList<QuizAbstracto> obtenerTodosQuizzes() {
        return cq.obtenerTodosLosQuizzes();
    }
    public void cambiarEstado(String nombreQuiz,String estado){
        cq.cambiarEstado(nombreQuiz, estado);
    }
    
    public void reducirCantQuizzesDisponibles(String email, int quizzesDisponibles) {
        cu.reducirCantQuizzesDisponibles(email, quizzesDisponibles);

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
    public UsuarioInstructor obtenerUsuarioInstructor(String key, String valor){
       return cu.obtenerUsuarioInstructor(key, valor);
    }

    public void insertarCurso(Curso c) {
        cc.insertarCurso(c);
    }
    public ArrayList<Curso> obtenerTodosLosCursos() {
        return cc.obtenerTodosLosCursos();
    }
    public ArrayList<Curso> obtenerCursosDeInstructor(UsuarioInstructor user) {
        return cc.obtenerCursosDeInstructor(user);
    }
    public ArrayList<Curso> obtenerCursosDeEstudiante(UsuarioAlumno user) {
        return cc.obtenerCursosDeEstudiante(user);
    }
    public Curso obtenerCurso(String key, String valor){
       return cc.obtenerCurso(key, valor);
    }
    public ArrayList<QuizAbstracto> obtenerQuizzesDeCurso(Curso curso){
        return cq.obtenerQuizzesDeCurso(curso);
    }
    
    public void insertarNota(NotaQuizz nota) {
        cn.insertarNota(nota);
    }
    
    public ArrayList<NotaQuizz> obtenerTodasLasNotas() {
        return cn.obtenerTodasLasNotas();
    }
    
    public ArrayList<NotaQuizz> obtenerNotasDeQuiz(QuizAbstracto quiz) {
        return cn.obtenerNotasDeQuiz(quiz);
    }
    
    public NotaQuizz obtenerRespuestasDeQuizDeAlumno (UsuarioAlumno alumno, QuizAbstracto quiz){
        return cn.obtenerRespuestasDeQuizDeAlumno(alumno, quiz);
    }
     public boolean anularPregunta(QuizAbstracto quiz, Document[] preguntas){
        return cq.anularPregunta(quiz, preguntas);
    }
    public void insertarRecurso(Recurso r) {
        cr.insertarRecurso(r);
    }
    public ArrayList<Recurso> obtenerRecursosDeInstructor(UsuarioInstructor user) {
        return cr.obtenerRecursosDeInstructor(user);
    }
    
    public Recurso obtenerRecurso(String key, String valor){
        return cr.obtenerRecurso(key, valor);
    }
    
    public void modificarNota(NotaQuizz respuestas){
        cn.modificarNota(respuestas);
    }
}
