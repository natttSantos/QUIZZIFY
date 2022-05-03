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
 * @author crivi
 */
public class NotaQuizz {
    private String quizz; //nombrequiz
    private String alumno; //nombreAlum
    private double nota;
    private ArrayList<PreguntaRespondida> respuestas;
    
    public NotaQuizz(String quizz, String alumno, double nota, ArrayList<PreguntaRespondida> respuestas){
        this.quizz = quizz;
        this.alumno = alumno;
        this.nota = nota;
        this.respuestas = respuestas;
    }
    
    public Document obtenerDocument(){
        Document d = new Document();
        d.append("quizz", this.quizz);
        d.append("alumno", this.alumno);
        d.append("nota", this.nota);
        d.append("respuestas", asList(crearDocumentRespuestas()));
        return d;
    }
    
    public Document[] crearDocumentRespuestas() {
        Document[] dPreguntasRespondidas = new Document[respuestas.size()];
        int i = 0;
        for (PreguntaRespondida respuesta:respuestas){
            Document d = new Document();
            d.append("pregunta", respuesta.getPregunta());
            d.append("respuesta", respuesta.getRespuesta());
            dPreguntasRespondidas[i]=d;
            i++;
        }
        return dPreguntasRespondidas;
    }
    
    public String getQuizz() {
        return quizz;
    }
    
    public double getNota() {
        return nota;
    }
    
    public String getAlumno() {
        return this.alumno;
    }
    
    public ArrayList<PreguntaRespondida> getRespuestas() {
        return this.respuestas;
    }
}
