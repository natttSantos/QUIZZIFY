/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio.modelo;

/**
 *
 * @author crivi
 */
public abstract class RespuestaAbstracta {
     String text;
    
    
    public RespuestaAbstracta(String text) {
        this.text = text;
    }
   
    
    public abstract String obtenerText(); 
    public abstract boolean esCorrecta(int index);
    
    
}
