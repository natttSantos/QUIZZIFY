package LogicaNegocio.modelo;

import java.util.ArrayList;


public class RespuestaSeleccion extends RespuestaAbstracta{
   
    protected ArrayList<OpcionRespuestaSeleccion> opciones;
    
    public RespuestaSeleccion(String descripcion, ArrayList<OpcionRespuestaSeleccion> opciones){
        super(descripcion); 
        this.opciones = opciones;
    }

    public ArrayList<OpcionRespuestaSeleccion> getOpciones() {
        return opciones;
    }

    @Override
    public String obtenerDescricpion() {
        return descripcion; 
    }

   
    public void setOpciones(ArrayList<OpcionRespuestaSeleccion> opciones) {
        this.opciones = opciones;
    }

    @Override
    public String toString() {
        return "RespuestaSeleccion{" + "opciones=" + opciones + '}';
    }

    
    public boolean esCorrecta(int index) {
        return opciones.get(index).isCorrecta();
    }
}
