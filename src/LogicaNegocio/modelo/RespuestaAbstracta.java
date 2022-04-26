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
     boolean correcta;
    
    public RespuestaAbstracta(String text, boolean correcta) {
        this.text = text;
        this.correcta = correcta;
    }
    
    public RespuestaAbstracta(String text) {
        this.text = text;
        this.correcta = false;
    }
    
    public String getTexto() {
        return text;
    }
    
    public boolean getEsCorrecta() {
        return correcta;
    }
    
    public void setEsCorrecta(boolean correcta) {
        this.correcta = correcta;
    }
    
    public abstract String obtenerText(); 
    public abstract boolean esCorrecta(int index);
    
    
}
