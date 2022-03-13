/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import org.w3c.dom.Document;

/**
 *
 * @author crivi
 */
public class Quiz {
    
    private String nombre;
    private Document [] preguntas;
    
    public Quiz(String nombre, Document [] preguntas) {
        this.nombre = nombre;
        this.preguntas = preguntas;
    }
    
    public void setPreguntas(Document[] preguntas){
        this.preguntas = preguntas;
    }
    
    public Document[] getPreguntas() {
        return preguntas;
    }
    
     public void setNombre(String nombre) {
         this.nombre = nombre;
     }

    public  String getNombre() {
        return nombre;
    }
}
    
