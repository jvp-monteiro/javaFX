package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class MainController {

    @FXML
    private void btnEnviarAction() {

        BikeDTO bike = new BikeDTO();

        bike.setMarca(txtMarca.getText());
        bike.setModelo(txtModelo.getText());
        bike.setTipo(txtTipo.getText()); // trocar para txtTipo futuramente
        bike.setPreco(Double.parseDouble(txtPreco.getText()));

        BikeDAO dao = new BikeDAO();
        dao.cadastrarBike(bike);

        cadastrarBikes();
    }

    @FXML
    private void btnDeletarAction() {

        BikeDTO bikeSelecionada = tblBike.getSelectionModel().getSelectedItem();

        if (bikeSelecionada != null) {

            BikeDAO dao = new BikeDAO();
            dao.excluirBike(bikeSelecionada.getId());

            cadastrarBikes();
        }
    }

    @FXML
    private void btnLimparAction() {

        txtMarca.clear();
        txtModelo.clear();
        txtTipo.clear();
        txtPreco.clear();
    }
    @FXML

    private void btnSalvarAction() {

        BikeDTO bikeSelecionada = tblBike.getSelectionModel().getSelectedItem();

        if (bikeSelecionada != null) {

            bikeSelecionada.setMarca(txtMarca.getText());
            bikeSelecionada.setModelo(txtModelo.getText());
            bikeSelecionada.setTipo(txtTipo.getText());
            bikeSelecionada.setPreco(Double.parseDouble(txtPreco.getText()));

            BikeDAO dao = new BikeDAO();
            dao.alterarBike(bikeSelecionada);

            cadastrarBikes();
        }
    }
    private void cadastrarBikes() {

        BikeDAO dao = new BikeDAO();

        tblBike.setId(
                FXCollections.observableArrayList(
                        dao.listaBikes()
                ).toString()
        );
    }
    @FXML
    private void btnCadastrarAction(ActionEvent event) {

        BikeDTO objBikeDTO = new BikeDTO();

        objBikeDTO.setMarca(txtMarca.getText());
        objBikeDTO.setModelo(txtModelo.getText());

        BikeDAO objBikeDAO = new BikeDAO();
        objBikeDAO.cadastrarBike(objBikeDTO);

        cadastrarBikes();
    }

    @FXML
    private void selecionarBike() {

        BikeDTO bike = tblBike.getSelectionModel().getSelectedItem();

        if (bike != null) {

            txtMarca.setText(bike.getMarca());
            txtModelo.setText(bike.getModelo());
            txtTipo.setText(bike.getTipo());
            txtPreco.setText(String.valueOf(bike.getPreco()));
        }
    }
    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtPreco;

    @FXML
    private TableView<BikeDTO> tblBike;

    @FXML
    private TableColumn<BikeDTO, String> colMarca;

    @FXML
    private TableColumn<BikeDTO, String> colModelo;

    @FXML
    private TableColumn<BikeDTO, String> colAno;

}
