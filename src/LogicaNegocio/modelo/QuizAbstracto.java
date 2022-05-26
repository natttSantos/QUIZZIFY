package LogicaNegocio.modelo;


import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class QuizAbstracto {
    private final String nombre;
    private final ArrayList preguntas;
    private final Curso curso; 
    private final LocalDate fechaInicio; 
    private final LocalDate fechaFin; 
    private final int tiempoLimite;  //optional
    private final boolean volverAtras; //optional
    
private QuizAbstracto(QuizBuilder builder){
    this.nombre = builder.nombre;
    this.curso = builder.curso; 
    this.preguntas = builder.preguntas;
    this.fechaInicio = builder.fechaInicio; 
    this.fechaFin = builder.fechaFin; 
    this.tiempoLimite = builder.limiteTime; 
    this.volverAtras = builder.retroceder; 
}    


//All getter, and NO setter to provde immutability
    public String getNombre() {
        return nombre;
    }

    public ArrayList getPreguntas() {
        return preguntas;
    }

    public Curso getCurso() {
        return curso;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getTiempoLimite() {
        return tiempoLimite;
    }

    public boolean isVolverAtras() {
        return volverAtras;
    }

    @Override
    public String toString() {
        return "QuizAbstracto{" + "nombre=" + nombre + ", preguntas=" + preguntas + ", curso=" + curso + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", tiempoLimite=" + tiempoLimite + ", volverAtras=" + volverAtras + '}';
    }

    
    
    

public boolean añadirPregunta(PreguntaAbstracta pregunta){
    return preguntas.add(pregunta);
    
}

public boolean eliminarPregunta(PreguntaAbstracta pregunta){
    return preguntas.remove(pregunta);
}

public static class QuizBuilder {
    private final String nombre;
    private final ArrayList preguntas;
    private final Curso curso; 
    private final LocalDate fechaInicio; 
    private final LocalDate fechaFin; 
    private int limiteTime;  //optional
    private boolean retroceder; //optional

    public QuizBuilder(String nombre, Curso curso,  ArrayList<PreguntaAbstracta> preguntas, 
        LocalDate fechaInicio, LocalDate fechaFin){
    this.nombre = nombre;
    this.curso = curso; 
    this.preguntas = preguntas;
    this.fechaInicio = fechaInicio; 
    this.fechaFin = fechaFin; 
    
    }  
   
   public QuizBuilder tiempoLimite (int tiempoLimite){
       this.limiteTime = tiempoLimite; 
       return this; 
   }
   public QuizBuilder volverAtras (boolean volverAtras){
       this.retroceder = volverAtras; 
       return this; 
   }

   
   
   public QuizAbstracto build() {
			QuizAbstracto quiz =  new QuizAbstracto(this);
			validateObligatedFields(quiz); 
			return quiz;
    }
   
   public void validateObligatedFields(QuizAbstracto quiz){
       if (quiz.nombre.isEmpty()){
           JOptionPane.showMessageDialog(null, "Complete el campo nombre!", "", JOptionPane.WARNING_MESSAGE);
       }
       if (quiz.curso == null){
           JOptionPane.showMessageDialog(null, "Seleccione un curso!", "", JOptionPane.WARNING_MESSAGE);
       }
       if (quiz.preguntas.isEmpty()){
           JOptionPane.showMessageDialog(null, "Añada alguna pregunta!", "", JOptionPane.WARNING_MESSAGE);
       }
       if(quiz.fechaInicio == null || quiz.fechaInicio == null || quiz.fechaInicio.isBefore(fechaFin)){
            JOptionPane.showMessageDialog(null, "Error en fechas!", "", JOptionPane.WARNING_MESSAGE);
        } 
   
   }

    
    
   
   }
  




}
