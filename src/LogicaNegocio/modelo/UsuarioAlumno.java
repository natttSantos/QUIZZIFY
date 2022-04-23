package LogicaNegocio.modelo;

import java.util.ArrayList;

public class UsuarioAlumno extends Usuario {
    
    private ArrayList<NotaQuizz> notas;
    
    public UsuarioAlumno(String nombre, String apellidos, String email, String contraseña) {
        super(nombre, apellidos, email, contraseña);
        
    }
    
}
