package Interfaz.controladores;


import LogicaNegocio.modelo.Curso;
import LogicaNegocio.modelo.PreguntaAbstracta;
import LogicaNegocio.modelo.PreguntaSeleccionMultiple;
import LogicaNegocio.modelo.QuizAbstracto;
import LogicaNegocio.modelo.UsuarioAlumno;
import LogicaNegocio.modelo.UsuarioInstructor;
import Persistencia.conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.bson.Document;

public class GenerarQuizNoAleatorioController implements Initializable {

    @FXML
    private TextField nombreTextField;
    @FXML
    private Button aceptarButton;
    @FXML
    private ListView<String> listView;
    
    private Conexion con;
    @FXML
    private ListView<String> listView2;
    
    private UsuarioInstructor instructorConectado; 
    private ArrayList<PreguntaAbstracta> preguntas;
    @FXML
    private MenuButton menuCurso;
    @FXML
    private Label instructor;
    @FXML
    private TextField textoBuscar;
    @FXML
    private Button crearPreguntaButton;
    @FXML
    private Button añadirAExamenButton1;
    @FXML
    private CheckBox goBackButton;
    @FXML
    private CheckBox restaButton;
    @FXML
    private Button fechaYTiempobutton;
    private boolean back=false;
    private boolean penalizacion=false;
    private LocalDate newFechaIni, newFechaFin;
    private int newMins;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Conexion.obtenerConexion();
        preguntas = con.obtenerTodasPreguntas();
        cargarLista();
    }    


    public void setUsuario(UsuarioInstructor i){
        this.instructorConectado = i;
    }
    
    @FXML
    private void crearPreguntaButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/CrearPreguntaController.fxml"));
        Parent root = loader.load();
        CrearPreguntaController controlador = loader.getController();
        Scene scene = new Scene(root, 400, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setTitle("Crear pregunta");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void aceptarButtonClicked(ActionEvent event) {
        ObservableList<String> lista = listView2.getItems();
        Document[] preguntas = new Document[lista.size()];
        int i = 0;
        for (String text:lista){
            System.out.println("item " + text);
            
            PreguntaAbstracta pregunta = con.obtenerPreguntaSegunTipo(text);
            Document d = pregunta.obtenerDocument(); 
            preguntas[i] = d;
            i++; 
            
        }
        try {
            if(!nombreTextField.getText().equals("")) {
                con.insertarQuiz(nombreTextField.getText(), obtenerCursoSelected(), "En preparación" ,preguntas);
                instructorConectado.setQuizzesDisponibles(instructorConectado.getQuizzesDisponibles() - 1);
                con.reducirCantQuizzesDisponibles( instructorConectado.getEmail(),instructorConectado.getQuizzesDisponibles());
                enviarAlerta("Creado","Quizz creado correctamente!");
              
            } else {
                enviarAlerta("ERROR","Escriba un texto descriptivo para  crear el Quizz!");
            }
            
        }catch(Exception e){
            enviarAlerta("ERROR","Ha ocurrido un error en la creación del Quizz! : "+ e.getMessage() );
        }
       
    }
    
    private ArrayList<PreguntaAbstracta> insertarPreguntasEnQuizz() {
        ArrayList<PreguntaAbstracta> preguntas = new ArrayList();
        ObservableList<String> lista = listView2.getItems();
        int i = 0;
        for (String text:lista){
            
            //PreguntaAbstracta pregunta = con.obtenerPregunta("text", text);
            /*Document d = new Document();
            d.append("text", pregunta.getText())
                .append("dificultad", pregunta.getDificultad())
                .append("tema", pregunta.getTema()) 
                .append("respuestas", asList(pregunta.getRespuestas()));
            //preguntas[i] = d;
            i++; */           
        }
        return preguntas;
    }

    private void anularButtonClickedTest(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    @FXML
    private void añadirAExamenButtonClicked(ActionEvent event) {
        QuizAbstracto quiz = null;
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            if (!listView2.getItems().contains(selectedItem)) {
                quiz = con.obtenerQuiz("preguntas.text", selectedItem);
                
                if(quiz != null){
                   
                    int reply = JOptionPane.showConfirmDialog(null, "La pregunta que va a insertar en el Quiz ya está en otro quizz!. Quieres añadirla de todas formas?", "Pregunta ya en uso!", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        listView2.getItems().add(selectedItem);
                    }
                } else {
                    listView2.getItems().add(selectedItem);
                }
            } else { 
                enviarAlerta("ERROR", "La pregunta seleccionada ya ha sido añadida al examen");
            }
        }else {
            enviarAlerta("ERROR", "Debe seleccionar una pregunta para añadirla al examen!");
        }
       
    }
    
    public void cargarLista(){
          for (PreguntaAbstracta pregunta:preguntas ){
                listView.getItems().add(pregunta.getText());
        }
    }
      private void enviarAlerta(String header,String text) {
        Alert dialogoAlerta;
        if(header.equals("ERROR")){
           dialogoAlerta = new Alert(Alert.AlertType.ERROR); 
        }else {
            dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION); 
        }
        dialogoAlerta.setTitle(null);
        dialogoAlerta.setHeaderText(header);
        dialogoAlerta.setContentText(text);
        java.awt.Toolkit.getDefaultToolkit().beep();
        dialogoAlerta.showAndWait(); 
    }

    @FXML
    private void pulsarAtras(ActionEvent event) throws IOException {
        //Refactoring
        navegarFormInstructor(event);
    }
    
    public void addCursosToMenu(){
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                menuCurso.setText(((MenuItem)e.getSource()).getText()); 
            }
        };
        
        ArrayList <Curso> cursosInstructor = con.obtenerCursosDeInstructor(instructorConectado); 
        for(int i = 0; i < cursosInstructor.size() && cursosInstructor.size() > 0; i++){
           Curso curso = cursosInstructor.get(i); 
           MenuItem menuItem = new MenuItem(curso.getNombreCurso());
           menuCurso.getItems().add(menuItem); 
           menuItem.setOnAction(event);
       }
    }
    
    public Document obtenerCursoSelected(){
        String nombreCurso = menuCurso.getText(); 
        Curso curso = con.obtenerCurso("nombreCurso", nombreCurso); 
        Document dcurso = curso.obtenerDocument(); 
        return dcurso; 
    }
    @FXML
    private void pulsarBuscar(ActionEvent event) {
        String infoBuscar = textoBuscar.getText(); 
        for (PreguntaAbstracta pregunta:preguntas ){
            boolean contieneInfo = pregunta.getText().contains(infoBuscar); 
            if(!contieneInfo){
               listView.getItems().remove(pregunta.getText()); 
            } 
        }
    }

    @FXML
    private void pulsarX(ActionEvent event) { //Limpiar registro
        ObservableList <String> list = listView.getItems(); 
        list.remove(0, list.size()); 
        listView.setItems(list);
        cargarLista(); 
    }
    
    public void navegarFormInstructor (ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/vista/sesionInstructor.fxml"));
        Parent root =(Parent) loader.load();      
        SesionInstructorController sesionInstructor = loader.<SesionInstructorController>getController();
        sesionInstructor.setUsuario(instructorConectado);
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void PermitirBack(ActionEvent event) {
        back=true;
    }

    @FXML
    private void ActivarResta(ActionEvent event) {
        penalizacion=true;
    }

    @FXML
    private void GoFechaYTiempo(ActionEvent event) throws IOException {
         FXMLLoader cargador = new FXMLLoader(getClass().getResource("/Interfaz/vista/FechaYTiempoQuiz.fxml"));
        Parent root = cargador.load();
        FechaYTiempoQuizController FechaController = cargador.getController();
        Scene scene = new Scene(root, 700, 700);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ver datos persona");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        if (!FechaController.getCancelar()) {
          newFechaIni = FechaController.getfechaIni();
          newFechaFin = FechaController.getfechaFIn();
          newMins = FechaController.getMins();
        
    }
    }

    
}
