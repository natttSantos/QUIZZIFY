/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

/**
 *
 * @author crivi
 */
public class PreguntaRespondida {
    private String pregunta;
    private String respuesta;
    public PreguntaRespondida(String pregunta, String respuesta){
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }
    
    public String getPregunta(){
        return this.pregunta;
    }
    
    public String getRespuesta(){
        return this.respuesta;
    }
}
