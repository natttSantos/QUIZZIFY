package LogicaNegocio.modelo;

import java.util.ArrayList;


public class RespuestaSeleccion extends RespuestaAbstracta{
   
    protected ArrayList<OpcionRespuestaSeleccion> opciones;
    
    public RespuestaSeleccion(String text, ArrayList<OpcionRespuestaSeleccion> opciones){
        super(text); 
        this.opciones = opciones;
    }

    public ArrayList<OpcionRespuestaSeleccion> getOpciones() {
        return opciones;
    }
  
    public String obtenerText() {
        return text; 
    }

   
    public void setOpciones(ArrayList<OpcionRespuestaSeleccion> opciones) {
        this.opciones = opciones;
    }

//    @Override
//    public String toString() {
//        return "RespuestaSeleccion{" + "correcta=" + opciones + '}';
//    }

    
    public boolean esCorrecta(int index) {
        return opciones.get(index).isCorrecta();
    }
}
