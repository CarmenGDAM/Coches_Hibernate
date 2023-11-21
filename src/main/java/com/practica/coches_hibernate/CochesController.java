package com.practica.coches_hibernate;

import com.practica.coches_hibernate.dao.CocheDao;
import com.practica.coches_hibernate.dao.CocheDaoImpl;
import com.practica.coches_hibernate.model.Coches;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CochesController implements Initializable {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btEliminar;

    @FXML
    private Button btModificar;

    @FXML
    private Button btNuevo;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private ListView<Coches> lvCoches;

    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfMatricula;

    @FXML
    private TextField tfModelo;
    private CocheDao cocheDI = new CocheDaoImpl();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Coches> coches = cocheDI.getAllCoches();
        lvCoches.setItems(FXCollections.observableList(coches));

        String[] tipos = new String[]{"<Selecciona tipo>", "Familiar", "Monovolumen", "Deportivo", "SUV"};
        cbTipo.setItems(FXCollections.observableArrayList(tipos));

    }

    @FXML
    void anyadirCoche(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void eliminarCoche(ActionEvent event) {

    }

    @FXML
    void modificarCoche(ActionEvent event) {

    }

    @FXML
    void seleccionarCoche(MouseEvent event) {

    }

}
