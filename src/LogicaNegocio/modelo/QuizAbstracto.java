package LogicaNegocio.modelo;


import java.util.ArrayList;


public abstract class QuizAbstracto {
    protected String nombre;
    protected ArrayList preguntas;
    
public QuizAbstracto(String nombre, ArrayList<PreguntaAbstracta> preguntas){
    this.nombre = nombre;
    this.preguntas = preguntas;
}    

public String getNombre() {
    return nombre;
}

public ArrayList getPreguntas() {
    return preguntas;
}


public boolean añadirPregunta(PreguntaAbstracta pregunta){
    return preguntas.add(pregunta);
    
}

public boolean eliminarPregunta(PreguntaAbstracta pregunta){
    return preguntas.remove(pregunta);
}

public abstract PreguntaAbstracta crearPregunta(String text, String dificultad, String tema, ArrayList respuestas);
}
