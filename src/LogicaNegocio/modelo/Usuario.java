package LogicaNegocio.modelo;

import java.util.ArrayList;



public abstract class Usuario {
    
    private String nombre;
    private String apellidos;
    private String email;
    String contraseña;
    
    public Usuario(String nombre, String apellidos, String email, String contraseña) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.contraseña = contraseña;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }


    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", contrase\u00f1a=" + contraseña + '}';
    }
    
    
}
