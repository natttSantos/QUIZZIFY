/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author margr
 */
public class PreguntaVF extends PreguntaAbstracta {
    
    private boolean esVerdadera;

    public PreguntaVF(String text, String dificultad, Recurso recurso, boolean verdadera) {
        super(text, dificultad, recurso);
        this.esVerdadera = verdadera;
        this.tipo = "vf";   // verdadero/falso
    }
    
    public PreguntaVF(String text, String dificultad, boolean verdadera) {
        super(text, dificultad);
        this.esVerdadera = verdadera;
        this.tipo = "vf";
    }
    
    @Override
    public Document obtenerDocument() {
        /**
         * Documento en este formato esta listo para meter en la base de datos
         */
        
        Document p = new Document();
        p.append("text", this.text);
        p.append("tipo", this.tipo);
        p.append("dificultad", this.dificultad);
        p.append("recurso", this.recurso.obtenerDocument());
        p.append("esVerdadera", this.esVerdadera);
        return p;
    }

    public boolean isRespuestaVerdadera() {
        return esVerdadera;
    }

    public void setRespuestaVerdadera(boolean respuestaVerdadera) {
        this.esVerdadera = respuestaVerdadera;
    }
    
    @Override
    public RespuestaAbstracta crearRespuesta(String descripcion, ArrayList<OpcionRespuestaSeleccion> opciones) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
