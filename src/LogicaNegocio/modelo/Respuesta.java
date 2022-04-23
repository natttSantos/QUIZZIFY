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
    
    private String texto;
    private boolean esCorrecta;
    
    public Respuesta(String texto, boolean esCorrecta) {
        this.texto = texto;
        this.esCorrecta = esCorrecta;
    }
    
    public Respuesta(String texto) {
        this.texto = texto;
        this.esCorrecta = false;
    }
    
    public String getTexto() {
        return texto;
    }
    
    public boolean getEsCorrecta() {
        return esCorrecta;
    }
    
    public void setEsCorrecta(boolean correcta) {
        this.esCorrecta = correcta;
    }
    
}
