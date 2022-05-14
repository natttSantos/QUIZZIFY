package Interfaz.controladores;

import LogicaNegocio.modelo.Calificacion;
import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.NotaQuizz;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.UsuarioAlumno;
import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CalificacionesEstudianteController implements Initializable {

    
    private Conexion con;
    @FXML
    private Label instructor;

    private UsuarioAlumno estudianteConectado; 
    private String nombreCursoSelected; 
    ArrayList<QuizAbstracto> quizzesCurso = new ArrayList<>(); 

    @FXML
    private TableView<Calificacion> tableView;
    @FXML
    private TableColumn<Calificacion, String> cursoColumn;
    @FXML
    private TableColumn<Calificacion, String> nombreQuizColumn;
    @FXML
    private TableColumn<Calificacion, String> notaColumn;
    @FXML
    private Label textoEspera;
    private TableColumn<Calificacion, String> percentilColumna;
    @FXML
    private TableColumn<?, ?> porcentajeColumna;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    con = Conexion.obtenerConexion(); 
    }    
    
    public void cargarNotas(){
       
        cursoColumn.setCellValueFactory(new PropertyValueFactory("curso"));
        nombreQuizColumn.setCellValueFactory(new PropertyValueFactory("nombreQuiz"));
        notaColumn.setCellValueFactory(new PropertyValueFactory("nota"));
        porcentajeColumna.setCellValueFactory(new PropertyValueFactory("porcentaje"));
        

        ObservableList<Calificacion> datos = FXCollections.observableArrayList();
        for(QuizAbstracto quiz: quizzesCurso){
            ArrayList <NotaQuizz> notasQuiz = con.obtenerNotasDeQuiz(quiz); 
            String nameQuiz = quiz.getNombre(); 
            NotaQuizz quizRealizado = con.obtenerRespuestasDeQuizDeAlumno(estudianteConectado, quiz); 
            if(quizRealizado != null){
                Calificacion calif = new Calificacion(nombreCursoSelected, nameQuiz, quizRealizado.getNota(), calcularPorcentajesQuiz(notasQuiz, quizRealizado)); 
                datos.add(calif); 
            }
        }
        
        cursoColumn.setCellValueFactory(new PropertyValueFactory("curso"));
        nombreQuizColumn.setCellValueFactory(new PropertyValueFactory("nombreQuiz"));
        notaColumn.setCellValueFactory(new PropertyValueFactory("nota"));
        porcentajeColumna.setCellValueFactory(new PropertyValueFactory("porcentaje"));
        
        tableView.setItems(datos);
        //tableView.getColumns().addAll(cursoColumn, nombreQuizColumn, notaColumn);
    }
    
    public String calcularPorcentajesQuiz (ArrayList <NotaQuizz> notasQuiz,  NotaQuizz quizRealizado){
        int sumaNotas = 0; 
        double miPosicion = 0; 
        String resultado = ""; 
        ArrayList <Double> notas = new ArrayList<>(); 
        for (NotaQuizz nota: notasQuiz){
            notas.add(nota.getNota()); 
        }
        Collections.sort(notas); //ArrayList ordenado
        
        for(int i = 0; i < notas.size(); i++){
            sumaNotas += notas.get(i); 
            if (quizRealizado.getNota() == notas.get(i)){
                miPosicion = i + 1; 
            }
        }
        miPosicion = (miPosicion/notas.size()) * 100; 
        
        DecimalFormat df = new DecimalFormat("#.00");
        resultado = df.format(miPosicion) + "%"; 
        return resultado; 
    }
    
    
    
    
    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionEstudianteQuizzes.fxml"));
        Parent root =(Parent) loader.load();      
        SesionEstudianteQuizzesController sesionQuizzes = loader.<SesionEstudianteQuizzesController>getController();
        sesionQuizzes.setNombreCursoSelected(nombreCursoSelected);
        sesionQuizzes.setEstudianteConectado(estudianteConectado);
        sesionQuizzes.cargarListaQuizzes(); 
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void setEstudianteConectado(UsuarioAlumno estudianteConectado) {
        this.estudianteConectado = estudianteConectado;
    }

    public void setNombreCursoSelected(String nombreCursoSelected) {
        this.nombreCursoSelected = nombreCursoSelected;
    }

    public void setQuizzesCurso(ArrayList<QuizAbstracto> quizzesCurso) {
        this.quizzesCurso = quizzesCurso;
    }

    @FXML
    private void pulsarEstadisticas(ActionEvent event) {
    }
    
    


    
}
