package LogicaNegocio.modelo;

import java.util.ArrayList;
import org.bson.Document;


public abstract class PreguntaAbstracta {
    String text;
    String dificultad;
    Recurso recurso; 
    String tipo;    // abierta, vf, multiple
    ArrayList <Respuesta> respuestas;
    Double puntos; 
    
    public PreguntaAbstracta(String text, String dificultad, Recurso recurso, ArrayList respuestas, Double puntos){
        this.text = text;
        this.dificultad = dificultad;
        this.recurso = recurso; 
        this.respuestas = respuestas;
        this.puntos = puntos; 
    }
    
    public PreguntaAbstracta(String text, String dificultad, Recurso recurso, Double puntos) {
        this.text = text;
        this.dificultad = dificultad;
        this.recurso = recurso; 
        this.puntos = puntos; 
    }
    
    public PreguntaAbstracta(String text, String dificultad) {
        this.text = text;
        this.dificultad = dificultad;
    }
    
    public abstract Document obtenerDocument();
    
    
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

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTipo() {
        return this.tipo;
    }

    public void setPuntos(Double puntos) {
        this.puntos = puntos;
    }

    public Double getPuntos() {
        return puntos;
    }
    

    @Override
    public String toString() {
        return "Pregunta{" + "text=" + text + ", dificultad=" + dificultad + ", recurso=" + recurso + ", respuestas=" + respuestas + '}';
    }
}
