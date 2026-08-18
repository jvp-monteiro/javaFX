package controller;

import model.DTO.BikeDTO;
import service.BikeService;
import util.BikeTable;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static util.BikeAlert.*;


public class MainController {

    // BOTÕES
    @FXML private Button btnCadastrar;
    @FXML private Button btnAlterar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;

    // CAMPOS
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtTipo;
    @FXML private TextField txtPreco;

    // TABELA
    @FXML private TableView<BikeDTO> tblBike;
    @FXML private TableColumn<BikeDTO, Integer> colId;
    @FXML private TableColumn<BikeDTO, String> colMarca;
    @FXML private TableColumn<BikeDTO, String> colModelo;
    @FXML private TableColumn<BikeDTO, String> colTipo;
    @FXML private TableColumn<BikeDTO, Double> colPreco;

    // SERVIÇO
    private final BikeService bikeService = new BikeService();

    @FXML
    public void initialize() {
        // Configura visual e eventos da tabela usando a classe auxiliar
        BikeTable.configurarColunas(colId, colMarca, colModelo, colTipo, colPreco);
        BikeTable.configurarEventos(tblBike, txtMarca, txtModelo, txtTipo, txtPreco, btnExcluir, btnAlterar);

        btnExcluir.setDisable(true);
        btnAlterar.setDisable(true);

        carregarTabela();
    }

    @FXML
    private void btnCadastrarAction(ActionEvent event) {
        try {
            bikeService.cadastrar(txtMarca.getText(), txtModelo.getText(), txtTipo.getText(), txtPreco.getText());

            finalizarAcao("Bike cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        }
    }

    @FXML
    private void btnAlterarAction(ActionEvent event) {
        try {
            BikeDTO bikeSelecionada = tblBike.getSelectionModel().getSelectedItem();

            bikeService.alterar(bikeSelecionada, txtMarca.getText(), txtModelo.getText(), txtTipo.getText(), txtPreco.getText());

            finalizarAcao("Bike alterada com sucesso!");
        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        }
    }

    @FXML
    private void btnExcluirAction(ActionEvent event) {
        BikeDTO bikeSelecionada = tblBike.getSelectionModel().getSelectedItem();

        if (bikeSelecionada == null) {
            mostrarErro("Selecione uma bicicleta.");
            return;
        }

        if (mostrarConfirmacao("Deseja realmente excluir esta bicicleta?")) {
            try {
                bikeService.excluir(bikeSelecionada);
                finalizarAcao("Bike excluída com sucesso!");
            } catch (Exception e) {
                mostrarErro("Erro ao excluir: " + e.getMessage());
            }
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        limparCampos();
    }

    // =========================
    // MÉTODOS AUXILIARES (UI)
    // =========================

    private void finalizarAcao(String mensagemSucesso) {
        carregarTabela();
        limparCampos();
        mostrarSucesso(mensagemSucesso);
    }

    private void limparCampos() {
        txtMarca.clear();
        txtModelo.clear();
        txtTipo.clear();
        txtPreco.clear();

        tblBike.getSelectionModel().clearSelection();

        btnExcluir.setDisable(true);
        btnAlterar.setDisable(true);

        txtMarca.requestFocus();
    }

    private void carregarTabela() {
        tblBike.setItems(FXCollections.observableArrayList(bikeService.listarTodas()));
    }
}