/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.controladores;

import LogicaNegocio.modelo.Pregunta;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Persistencia.conexion.Conexion;
import LogicaNegocio.modelo.Quiz;
import java.util.ArrayList;
import java.util.Collections;

public class ControladorQuizzes {
    MongoDatabase db;
    MongoCollection quizzes;
    
    Conexion con;
    public ControladorQuizzes(MongoDatabase db){
        this.db = db;
        this.quizzes = db.getCollection("Quizzes");
    }
    
    public void barajarListaPreguntas(ArrayList<Pregunta> lista) {
        // shuffle ArrayList
	Collections.shuffle(lista);
    }
    
    public void crearQuizAleatorio(int num, String nombre, ArrayList<Pregunta> lista) {
        Collections.shuffle(lista);
        ArrayList<Pregunta> nuevaLista = new ArrayList<Pregunta>();
        
        for(int i = 0; i< num; i++)
            nuevaLista.add(lista.get(i));
        
        // TODO:
        // crear quiz con preguntas de nuevaLista
        // subir a base de dato
    }
     
    
}
