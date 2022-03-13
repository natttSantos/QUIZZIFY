/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author nata2
 */
public class RespuestaSeleccion extends Respuesta{
    protected ArrayList<String> opciones; 
    protected ArrayList<String> respuestas_correctas; 
    
    public RespuestaSeleccion(String descripcion){
        super(descripcion); 
         opciones = new ArrayList();
         respuestas_correctas = new ArrayList();
    }

    @Override
    public String obtenerDescricpion() {
        return descripcion; 
    }
}
