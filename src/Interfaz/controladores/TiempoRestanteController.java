/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import java.time.LocalTime;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author nata2
 */
public class TiempoRestanteController {

    @FXML
    private Label tiempo;
    private LocalTime horaFinal; 

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }
    
    public void calculoTiempo (int hora , int min, int seg){
       
        tiempo.setText( hora + " : " + min + " : 0"+ seg);

          
        
    }
    
    
    
    public void cargarTiempoCallBack (){
        int hora = horaFinal.getHour(); 
        int min = horaFinal.getMinute(); 
        int seg = horaFinal.getSecond(); 
        
        int horaAct = LocalTime.now().getHour(); 
        int minAct = LocalTime.now().getMinute();  
        int segAct = LocalTime.now().getSecond(); 
        
        calculoTiempo(hora - horaAct, min - minAct, 0);
        
    }
    
}
