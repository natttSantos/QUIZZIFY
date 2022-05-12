/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import org.bson.Document;

/**
 *
 * @author nata2
 */
public class Recurso {
    private String nombreRecurso;  
    private UsuarioInstructor instructorEnRecurso;

    public Recurso(String nombreRecurso, UsuarioInstructor instructorEnRecurso) {
        this.nombreRecurso = nombreRecurso;
        this.instructorEnRecurso = instructorEnRecurso;  
    }

     public Document obtenerDocument() {
        Document d = new Document(); 
          d.append("nombreRecurso",this.nombreRecurso);
          d.append("instructorEnRecurso", crearDocument_InstructorCurso());
        return d;
    }
     

    public Document crearDocument_InstructorCurso(){ 
            Document d = new Document(); 
            d.append("nombre",instructorEnRecurso.getNombre());
            d.append("apellidos", instructorEnRecurso.getApellidos());
            d.append("email", instructorEnRecurso.getEmail());
            d.append("contraseña", instructorEnRecurso.getContraseña());
            d.append("tipo", "Instructor");
            d.append("quizzesDisponibles", 20);

        return d; 
    }
    
    
    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public UsuarioInstructor getInstructorEnRecurso() {
        return instructorEnRecurso;
    }

    public void setInstructorEnRecurso(UsuarioInstructor instructorEnRecurso) {
        this.instructorEnRecurso = instructorEnRecurso;
    }

    
    
    
}
