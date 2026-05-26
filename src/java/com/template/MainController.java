package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class MainController
{
    // button
    @FXML private Button btnMarcaEnviar;
    @FXML private Button btnModeloEnviar;
    @FXML private Button btnAnoEnviar;

    // text field
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtAno;

    //tabel view
    @FXML private TableView<BikeDTO> tblUsuario;
    @FXML private TableColumn<BikeDTO, String> colMarca;
    @FXML private TableColumn<BikeDTO, String> colModelo;
    @FXML private TableColumn<BikeDTO, String> colAno;

    private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }
}

