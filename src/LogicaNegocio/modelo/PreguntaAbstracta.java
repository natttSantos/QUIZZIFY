package LogicaNegocio.modelo;

import java.util.ArrayList;
import org.bson.Document;


public abstract class PreguntaAbstracta {
    private String text;
    private String dificultad;
    private String tema;
    private ArrayList respuestas;
    
    public PreguntaAbstracta(String text, String dificultad, String tema, ArrayList respuestas){
        this.text = text;
        this.dificultad = dificultad;
        this.tema = tema;
        this.respuestas = respuestas;
    }
    
    
    public abstract RespuestaAbstracta crearRespuesta(String descripcion, ArrayList<OpcionRespuestaSeleccion> opciones); 

    public void setRespuestas(ArrayList respuestas) {
        this.respuestas = respuestas;
    }

    public ArrayList getRespuestas() {
        return respuestas;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getText() {
        return text;
    }

    public String getDificultad() {
        return dificultad;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Override
    public String toString() {
        return "Pregunta{" + "text=" + text + ", dificultad=" + dificultad + ", tema=" + tema + ", respuestas=" + respuestas + '}';
    }

    //anyadir_respuesta()
    //eliminar_respuesta()
    //obtener_descripcion()
    
}
