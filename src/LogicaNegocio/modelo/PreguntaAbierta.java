/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio.modelo;

import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author nata2
 */
public class PreguntaAbierta extends PreguntaAbstracta {

    public PreguntaAbierta(String text, String dificultad, String tema) {
        super(text, dificultad, tema);
        this.tipo = "abierta";   // verdadero/falso
    }
    
    public PreguntaAbierta(String text, String dificultad) {
        super(text, dificultad);
        this.tipo = "abierta";
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
        p.append("tema", this.tema);
        return p;
    }

    @Override
    public RespuestaAbstracta crearRespuesta(String descripcion, ArrayList<OpcionRespuestaSeleccion> opciones) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    
    }



