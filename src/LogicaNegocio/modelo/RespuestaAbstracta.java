/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio.modelo;

/**
 *
 * @author crivi
 */
public abstract class RespuestaAbstracta {
    protected String descripcion;
    
    public RespuestaAbstracta(String descripcion){
        this.descripcion = descripcion; 
    }
    public abstract String obtenerDescricpion(); 
    public abstract boolean esCorrecta(int index);
}
