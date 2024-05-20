package controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Escuela;
import model.Estudiante;

public class EstudianteController {

    @FXML
    private TableColumn<Estudiante, String> colApellidos;

    @FXML
    private TableColumn<Estudiante, String> colMatricula;

    @FXML
    private TableColumn<Estudiante, String> colNombre;

    @FXML
    private TableColumn<Estudiante, String> colTelefono;

    @FXML
    private TableView<Estudiante> tblEstudiante;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;


    private Escuela escuela;


    @FXML
    void actualizarEstudiante(ActionEvent event) {

        Estudiante estudiante = new Estudiante();
        estudiante.setNumeroMatricula(txtMatricula.getText());
        estudiante.setNombre(txtNombre.getText());
        estudiante.setApellido(txtApellidos.getText());
        estudiante.setTelefono(txtTelefono.getText());

        escuela.actualizarEstudiante(estudiante);

        limpiarCampos();
        actualizarTabla();

    }



    @FXML
    void eliminarEstudiante(ActionEvent event) {

        eliminarEstudiante();

    }

    public void eliminarEstudiante() {
        Estudiante estudiante = tblEstudiante.getSelectionModel().getSelectedItem();

        escuela.eliminarEstudiante(estudiante);

        actualizarTabla();
    }

    @FXML
    void guardarEstudiantes(ActionEvent event) {
        guardarEstudiantes();
    }

    public void guardarEstudiantes() {

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(txtNombre.getText());
        estudiante.setApellido(txtApellidos.getText());
        estudiante.setTelefono(txtTelefono.getText());
        estudiante.setNumeroMatricula(txtMatricula.getText());

        escuela.agregarEstudiante(estudiante);


        actualizarTabla();


    }

    private void actualizarTabla() {

        tblEstudiante.getItems().clear();
        tblEstudiante.getItems().addAll(escuela.getListaEstudiantes());
        tblEstudiante.refresh();


    }

    private void limpiarCampos() {
        txtApellidos.setText("");
        txtMatricula.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");

        txtMatricula.setEditable(true);
    }

    @FXML
    public void initialize() {
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("numeroMatricula"));

        tblEstudiante.setOnMouseClicked( mouseEvent -> {

            if (tblEstudiante.getSelectionModel().getSelectedItem() != null) cargarCampos();

        });

    }

    private void cargarCampos() {

        Estudiante estudiante = tblEstudiante.getSelectionModel().getSelectedItem();

        txtNombre.setText(estudiante.getNombre());
        txtApellidos.setText(estudiante.getApellido());
        txtTelefono.setText(estudiante.getTelefono());
        txtMatricula.setText(estudiante.getNumeroMatricula());

        txtMatricula.setEditable(false);

    }

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
        actualizarTabla();
    }
}