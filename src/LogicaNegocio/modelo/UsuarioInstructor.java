/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

import java.util.ArrayList;

/**
 *
 * @author margr
 */
public class UsuarioInstructor extends Usuario {
    private String tipo;
    private int quizzesDisponibles;
    
    public UsuarioInstructor(String nombre, String apellidos, String email, String contraseña, String tipo, int quizzesDisponibles) {
        super(nombre, apellidos, email, contraseña);
        this.tipo = tipo;
        this.quizzesDisponibles = quizzesDisponibles;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQuizzesDisponibles() {
        return quizzesDisponibles;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setQuizzesDisponibles(int quizzesDisponibles) {
        this.quizzesDisponibles = quizzesDisponibles;
    }
    
    
    @Override
    public String toString() {
        return "UsuarioInstructor{" + super.toString() +  ", tipo=" + tipo + ", quizzesDisponibles=" + quizzesDisponibles + '}';
    }

  
    
}
