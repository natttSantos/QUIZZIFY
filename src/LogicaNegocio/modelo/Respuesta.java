/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

/**
 *
 * @author margr
 */
public class Respuesta {
    
    private String text;
    private boolean correcta;
    
    public Respuesta(String text, boolean correcta) {
        this.text = text;
        this.correcta = correcta;
    }
    
    public Respuesta(String text) {
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
    
}
