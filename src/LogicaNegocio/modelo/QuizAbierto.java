package LogicaNegocio.modelo;

import java.io.IOException;
import java.util.ArrayList;

public class QuizAbierto extends QuizAbstracto {
    
    public QuizAbierto(String nombre, ArrayList<PreguntaAbstracta> preguntas){
        super(nombre, preguntas); 
    }

    @Override
    public PreguntaAbstracta crearPregunta(String text, String dificultad, String tema, ArrayList respuestas) {
        //PreguntaAbstracta pregunta = new PreguntaAbierta (enunciado, instrucciones, puntuacion);
        return null;        
    }
    
}
