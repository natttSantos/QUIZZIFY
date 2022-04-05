package LogicaNegocio.modelo;

import java.util.ArrayList;
import org.bson.Document;


public class PreguntaSeleccionMultiple extends PreguntaAbstracta {

    
    public PreguntaSeleccionMultiple(String text, String dificultad, String tema, ArrayList respuestasSeleccion){
        super(text, dificultad, tema, respuestasSeleccion);
    }
    
    
    @Override
    public RespuestaAbstracta crearRespuesta(String descripcion, ArrayList<OpcionRespuestaSeleccion> opciones) {
        RespuestaAbstracta respuesta = new RespuestaSeleccion(descripcion, opciones);
        return respuesta;
    }
  
}
