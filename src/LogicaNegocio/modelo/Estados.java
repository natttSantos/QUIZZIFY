/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

import java.time.LocalDate;

/**
 *
 * @author nata2
 */
public class Estados {
    
    private String tipoEstado = ""; 

    public Estados() {
        
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }
    
    public String gestionarEstados (LocalDate dateInicio, LocalDate dateFin){ //Ya he salido lanzado
        LocalDate presentDate = LocalDate.now(); 
        
        if ((presentDate.isBefore(dateInicio))){ 
            tipoEstado = "Publicado-Inactivo"; 
        }
        if (!(presentDate.isBefore(dateInicio)) && !(presentDate.isAfter(dateFin))){ 
            tipoEstado = "Publicado-Activo"; 
        }
        if (presentDate.isAfter(dateFin)){
            tipoEstado = "Finalizado"; 
        }
        return tipoEstado; 
    }
    
}
