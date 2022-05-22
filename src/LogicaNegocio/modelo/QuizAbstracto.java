package LogicaNegocio.modelo;


import java.time.LocalDate;
import java.util.ArrayList;


public class QuizAbstracto {
    protected String nombre;
    protected ArrayList preguntas;
    protected Curso curso; 
    protected LocalDate fechaInicio; 
    protected LocalDate fechaFin; 
    protected int tiempoLimite; 
    
public QuizAbstracto(String nombre, Curso curso,  ArrayList<PreguntaAbstracta> preguntas, LocalDate fechaInicio, LocalDate fechaFin, int tiempoLimite){
    this.nombre = nombre;
    this.curso = curso; 
    this.preguntas = preguntas;
    this.fechaInicio = fechaInicio; 
    this.fechaFin = fechaFin; 
    this.tiempoLimite = tiempoLimite; 
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


    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate dateInicio) {
        this.fechaInicio = dateInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate dateFin) {
        this.fechaFin = dateFin;
    }
    
    

    //public abstract PreguntaAbstracta crearPregunta(String text, String dificultad, String tema, ArrayList respuestas);

    public int getTiempoLimite() {
        return tiempoLimite;
    }

    public void setTiempoLimite(int tiempoLimite) {
        this.tiempoLimite = tiempoLimite;
    }
}