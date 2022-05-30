/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.controladores;

import LogicaNegocio.modelo.NotaQuizz;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.UsuarioAlumno;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PC
 */
public class CalificacionesEstudianteControllerTest {
    
    public CalificacionesEstudianteControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

 
    @Test
    public void testCalcularPorcentajesQuiz() {
        System.out.println("calcularPorcentajesQuiz");
        
        NotaQuizz nota1 = new NotaQuizz(null, null, 1, null);
        NotaQuizz nota2 = new NotaQuizz(null, null, 2, null);
        NotaQuizz nota3 = new NotaQuizz(null, null, 10, null);
        
        
        ArrayList<NotaQuizz> notasQuiz = new ArrayList();
        notasQuiz.add(nota1);
        notasQuiz.add(nota2);
        notasQuiz.add(nota3);
        
        NotaQuizz quizRealizado = new NotaQuizz(null, null, 8, null);;
        CalificacionesEstudianteController instance = new CalificacionesEstudianteController();
        String expResult = "66,67%";
        String result = instance.calcularPorcentajesQuiz(notasQuiz, quizRealizado);
        assertEquals(expResult, result);
    }   
}
