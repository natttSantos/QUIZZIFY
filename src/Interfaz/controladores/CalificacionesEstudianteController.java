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
import java.util.ArrayList;
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
        

        ObservableList<Calificacion> datos = FXCollections.observableArrayList(); 
        for(QuizAbstracto quiz: quizzesCurso){
            String nameQuiz = quiz.getNombre(); 
            NotaQuizz quizRealizado = con.obtenerRespuestasDeQuizDeAlumno(estudianteConectado, quiz); 
            if(quizRealizado != null){
                Calificacion calif = new Calificacion(nombreCursoSelected, nameQuiz, quizRealizado.getNota()); 
                datos.add(calif); 
            }
        }
        
        cursoColumn.setCellValueFactory(new PropertyValueFactory("curso"));
        nombreQuizColumn.setCellValueFactory(new PropertyValueFactory("nombreQuiz"));
        notaColumn.setCellValueFactory(new PropertyValueFactory("nota"));
        
        tableView.setItems(datos);
        tableView.getColumns().addAll(cursoColumn, nombreQuizColumn, notaColumn);
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
    
    


    
}
