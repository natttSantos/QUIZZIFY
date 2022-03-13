/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import org.bson.Document;

/**
 *
 * @author nata2
 */
public class Pregunta {
    private String text;
    private String dificultad;
    private Document [] respuestas;
    
    
    public Pregunta() {
        
    }
    
    public Pregunta(String text, String dificultad, Document [] respuestas){
        this.text = text;
        this.dificultad = dificultad;
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
}