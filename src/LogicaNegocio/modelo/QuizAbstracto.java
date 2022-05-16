package LogicaNegocio.modelo;


import java.util.ArrayList;


public class QuizAbstracto {
    protected String nombre;
    protected String estado;
    protected ArrayList preguntas;
    protected Curso curso; 
    
public QuizAbstracto(String nombre, Curso curso, String estado, ArrayList<PreguntaAbstracta> preguntas){
    this.nombre = nombre;
    this.curso = curso; 
    this.preguntas = preguntas;
    this.estado = estado;
}    

public String getNombre() {
    return nombre;
}

public String getEstado() {
    return estado;
}

public void setEstado(String estado){
    this.estado = estado;
}

public ArrayList getPreguntas() {
    return preguntas;
}

 public Curso getCurso() {
      return curso;
 }

 public void setNombre(String nombre) {
     this.nombre = nombre;
 }

public boolean añadirPregunta(PreguntaAbstracta pregunta){
    return preguntas.add(pregunta);
    
}

public boolean eliminarPregunta(PreguntaAbstracta pregunta){
    return preguntas.remove(pregunta);
}

public void setPreguntas(ArrayList newPreguntas) {
    this.preguntas = newPreguntas;
}


//public abstract PreguntaAbstracta crearPregunta(String text, String dificultad, String tema, ArrayList respuestas);


}