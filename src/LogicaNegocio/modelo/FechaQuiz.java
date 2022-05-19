/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.modelo;

import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import org.bson.Document;

/**
 *
 * @author nata2
 */
public class FechaQuiz {
    private int year; 
    private int month; 
    private int day; 

    public FechaQuiz(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

     public Document obtenerDocument() {
        Document d = new Document(); 
          d.append("year",this.year);
          d.append("month", this.month);
          d.append("day", this.day);
        return d;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    
}
