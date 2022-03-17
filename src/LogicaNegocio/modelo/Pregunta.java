/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio.modelo;
import org.bson.Document;

/**
 *
 * @author nata2
 */
public class Pregunta {
    private String text;
    private String dificultad;
    private String tema;
    private Document [] respuestas;
    
    
    public Pregunta() {
        
    }
    
    public Pregunta(String text, String dificultad,String tema, Document [] respuestas){
        this.text = text;
        this.dificultad = dificultad;
        this.tema = tema;
        this.respuestas = respuestas;
    }

    public void setRespuestas(Document[] respuestas) {
        this.respuestas = respuestas;
    }

    public Document[] getRespuestas() {
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
    
}