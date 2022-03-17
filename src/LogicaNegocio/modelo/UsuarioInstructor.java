/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

/**
 *
 * @author margr
 */
public class UsuarioInstructor extends Usuario {
    
    private String[] cursos;
    
    public UsuarioInstructor(String nombre, String apellidos, String email, String contraseña, String[] cursos) {
        
        super(nombre, apellidos, email, contraseña);
        this.cursos = cursos;
    }
    
    public String[] getCursos() {
        return this.cursos;
    }
    
    public void setCursos(String[] cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return super.toString() + "cursos= " + cursos + '}';
    }
    
    
    
}
