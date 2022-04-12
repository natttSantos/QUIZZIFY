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
    
    private boolean respuestaVerdadera;

    public PreguntaVF(String text, String dificultad, String tema, boolean verdadera) {
        super(text, dificultad, tema);
        this.tipo = "vf";   // verdadero/falso
    }
    
    public PreguntaVF(String text, String dificultad, boolean verdadera) {
        super(text, dificultad);
        this.respuestaVerdadera = verdadera;
        this.tipo = "vf";
    }
    
    public Document obtenerDocument() {
        /**
         * Documento en este formato esta listo para meter en la base de datos
         */
        
        Document p = new Document();
        p.append("text", this.text);
        p.append("tipo", this.tipo);
        p.append("dificultad", this.dificultad);
        p.append("tema", this.tema);
        p.append("esVerdadera", this.respuestaVerdadera);
        return p;
    }
    
    @Override
    public RespuestaAbstracta crearRespuesta(String descripcion, ArrayList<OpcionRespuestaSeleccion> opciones) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
