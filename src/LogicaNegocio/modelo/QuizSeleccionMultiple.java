
package LogicaNegocio.modelo;

import java.util.ArrayList;

public class QuizSeleccionMultiple extends QuizAbstracto {
    
    public QuizSeleccionMultiple(String nombre, ArrayList<PreguntaAbstracta> preguntas){
        super(nombre, preguntas);
    }

    @Override
    public PreguntaAbstracta crearPregunta(String text, String dificultad, String tema, ArrayList respuestas) {
        PreguntaAbstracta pregunta = new PreguntaSeleccionMultiple (text, dificultad, tema, respuestas);
        return pregunta;
    }
    
}
