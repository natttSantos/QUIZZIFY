package LogicaNegocio.modelo;

import java.util.ArrayList;
import org.bson.Document;


public abstract class PreguntaAbstracta {
    String text;
    String dificultad;
    String tema;
    String tipo;    // abierta, vf, multiple
    ArrayList <Respuesta> respuestas;
    
    public PreguntaAbstracta(String text, String dificultad, String tema, ArrayList respuestas){
        this.text = text;
        this.dificultad = dificultad;
        this.tema = tema;
        this.respuestas = respuestas;
    }
    
    public PreguntaAbstracta(String text, String dificultad, String tema) {
        this.text = text;
        this.dificultad = dificultad;
        this.tema = tema;
    }
    
    public PreguntaAbstracta(String text, String dificultad) {
        this.text = text;
        this.dificultad = dificultad;
    }
    
    public abstract Document obtenerDocument();
    
    
    public abstract RespuestaAbstracta crearRespuesta(String descripcion, ArrayList<OpcionRespuestaSeleccion> opciones); 

    public void setRespuestas(ArrayList <Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public ArrayList <Respuesta> getRespuestas() {
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
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTipo() {
        return this.tipo;
    }

    @Override
    public String toString() {
        return "Pregunta{" + "text=" + text + ", dificultad=" + dificultad + ", tema=" + tema + ", respuestas=" + respuestas + '}';
    }
}
