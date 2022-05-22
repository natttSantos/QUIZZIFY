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
    private int minutoFinal; 

    public void setMinutoFinal(int minutoFinal) {
        this.minutoFinal = minutoFinal;
    }
    
    
    
    public void cargarTiempo (){
        int tiempoRestante = minutoFinal - (LocalTime.now().getMinute()); 
        if (tiempoRestante < 10){
             tiempo.setText("00 : 0" + tiempoRestante + ": 00" );
        }
        else {
             tiempo.setText("00 : " + tiempoRestante + ": 00" );
        }
    }
    
}
