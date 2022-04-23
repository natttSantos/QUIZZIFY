package LogicaNegocio.modelo;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import org.bson.Document;


public class PreguntaSeleccionMultiple extends PreguntaAbstracta {

    private ArrayList<Respuesta> respuestas;
    
    public PreguntaSeleccionMultiple(String text, String dificultad, String tema, ArrayList<Respuesta> respuestas){
        super(text, dificultad, tema);
        this.tipo = "multiple";
        this.respuestas = respuestas;
    }
    
    public PreguntaSeleccionMultiple(String text, String dificultad, ArrayList<Respuesta> respuestas) {
        super(text, dificultad);
        this.tipo = "multiple";
        this.respuestas = respuestas;
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
        
        Document [] resp  = new Document[respuestas.size()]; 
      
        for(int i = 0; i< respuestas.size(); i++){
            resp[i] = new Document("text",((respuestas.get(i)).getTexto()))
                       .append("correcta",((respuestas.get(i)).getEsCorrecta()));
           }
           
        p.append("respuestas", asList(resp));
        return p;
    }
    
    @Override
    public RespuestaAbstracta crearRespuesta(String descripcion, ArrayList<OpcionRespuestaSeleccion> opciones) {
        RespuestaAbstracta respuesta = new RespuestaSeleccion(descripcion, opciones);
        return respuesta;
    }
  
}
