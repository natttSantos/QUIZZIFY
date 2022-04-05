/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio.modelo;

/**
 *
 * @author nata2
 */
public class RespuestaAbierta extends RespuestaAbstracta{
    
    protected int longitud_maxima; 
    public RespuestaAbierta(String descripcion, int longitud_maxima ){
        super(descripcion); 
        this.longitud_maxima = longitud_maxima; 
    }

    @Override
    public String obtenerDescricpion() {
       return descripcion; 
    }

    @Override
    public boolean esCorrecta(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
