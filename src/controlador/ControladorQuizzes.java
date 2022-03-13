/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import modelo.Conexion;
import modelo.Quiz;

public class ControladorQuizzes {
    MongoDatabase db;
    MongoCollection quizzes;
    
    Conexion con;
    public ControladorQuizzes(MongoDatabase db){
        this.db = db;
        this.quizzes = db.getCollection("Quizzes");
    }
    
    
}
