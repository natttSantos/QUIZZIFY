package LogicaNegocio.modelo;


import java.util.ArrayList;


public abstract class QuizAbstracto {
    protected String nombre;
    protected ArrayList preguntas;
    protected Curso curso; 
    
public QuizAbstracto(String nombre, Curso curso, ArrayList<PreguntaAbstracta> preguntas){
    this.nombre = nombre;
    this.curso = curso; 
    this.preguntas = preguntas;
}    

public String getNombre() {
    return nombre;
}

public ArrayList getPreguntas() {
    return preguntas;
}

 public Curso getCurso() {
      return curso;
 }


public boolean añadirPregunta(PreguntaAbstracta pregunta){
    return preguntas.add(pregunta);
    
}

public boolean eliminarPregunta(PreguntaAbstracta pregunta){
    return preguntas.remove(pregunta);
}

public abstract PreguntaAbstracta crearPregunta(String text, String dificultad, String tema, ArrayList respuestas);
}
