package controlador;

import modelo.Conexion;
import modelo.Pregunta;

/**
 *
 * @author PC
 */
public interface IControladorPreguntas {
    
    
    public Pregunta obtenerPregunta(String key, String valor);
    public void insertPregunta(String text, String dificultad, String [] respuestas);
            
            
}
