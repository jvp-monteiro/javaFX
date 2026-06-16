package com.template;

// Importações necessárias do JavaFX
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    // Botões da interface
    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnLimpar;

    // Campos de texto para cadastro
    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtPreco;

    // Tabela de bicicletas
    @FXML
    private TableView<BikeDTO> tblBike;

    // Colunas da tabela
    @FXML
    private TableColumn<BikeDTO, Integer> colId;

    @FXML
    private TableColumn<BikeDTO, String> colMarca;

    @FXML
    private TableColumn<BikeDTO, String> colModelo;

    @FXML
    private TableColumn<BikeDTO, String> colTipo;

    @FXML
    private TableColumn<BikeDTO, Double> colPreco;

    // Método executado automaticamente ao abrir a tela
    @FXML
    public void initialize() {

        // Vincula cada coluna da tabela aos atributos da classe BikeDTO
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        // Define tamanho da fonte da tabela
        tblBike.setStyle("-fx-font-size: 14px;");

        // Inicialmente apenas cadastrar fica habilitado
        btnCadastrar.setDisable(false);
        btnExcluir.setDisable(true);

        // Evento executado ao selecionar uma bicicleta na tabela
        tblBike.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, bike) -> {

                    if (bike != null) {

                        // Habilita exclusão
                        btnCadastrar.setDisable(false);
                        btnExcluir.setDisable(false);

                        // Carrega dados nos campos
                        txtMarca.setText(bike.getMarca());
                        txtModelo.setText(bike.getModelo());
                        txtTipo.setText(bike.getTipo());
                        txtPreco.setText(String.valueOf(bike.getPreco()));
                    }
                });

        // Destaca a linha selecionada
        tblBike.setRowFactory(tv -> {
            TableRow<BikeDTO> row = new TableRow<>();

            row.selectedProperty().addListener((obs, oldVal, selected) -> {
                if (selected) {
                    row.setStyle("-fx-background-color: lightblue;");
                } else {
                    row.setStyle("");
                }
            });

            return row;
        });

        // Carrega os dados na tabela
        carregarTabela();
    }

    // Evento do botão Cadastrar
    @FXML
    private void btnCadastrarAction(ActionEvent event) {

        // Verifica se todos os campos foram preenchidos
        if (!validarCampos()) {
            return;
        }

        try {

            // Cria um objeto BikeDTO
            BikeDTO bike = new BikeDTO();

            // Recebe os dados digitados
            bike.setMarca(txtMarca.getText());
            bike.setModelo(txtModelo.getText());
            bike.setTipo(txtTipo.getText());
            bike.setPreco(Double.parseDouble(txtPreco.getText()));

            // Salva no banco
            BikeDAO dao = new BikeDAO();
            dao.cadastrarBike(bike);

            // Atualiza tela
            limparCampos();
            carregarTabela();

            mostrarSucesso("Bike cadastrada com sucesso!");

        } catch (NumberFormatException e) {
            mostrarErro("Preço inválido.");
        }

    }

    // Evento do botão Alterar
    @FXML
    private void btnAlterarAction(ActionEvent event) {

        if (!validarCampos()) {
            return;
        }

        // Obtém bicicleta selecionada
        BikeDTO bike = tblBike.getSelectionModel().getSelectedItem();

        if (bike != null) {

            // Atualiza os dados
            bike.setMarca(txtMarca.getText());
            bike.setModelo(txtModelo.getText());
            bike.setTipo(txtTipo.getText());
            bike.setPreco(Double.parseDouble(txtPreco.getText()));

            // Salva alteração no banco
            BikeDAO dao = new BikeDAO();
            dao.alterarBike(bike);

            carregarTabela();

            mostrarSucesso("Bike alterada com sucesso!");
        }
    }

    // Evento do botão Excluir
    @FXML
    private void btnExcluirAction(ActionEvent event) {

        // Obtém bicicleta selecionada
        BikeDTO bike = tblBike.getSelectionModel().getSelectedItem();

        if (bike != null) {

            // Cria janela de confirmação
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

            alerta.setTitle("Confirmação");
            alerta.setHeaderText(null);
            alerta.setContentText("Deseja realmente excluir esta bicicleta?");

            // Se usuário confirmar
            if (alerta.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {

                BikeDAO dao = new BikeDAO();

                // Exclui do banco
                dao.excluirBike(bike.getId());

                limparCampos();
                carregarTabela();

                mostrarSucesso("Bike excluída com sucesso!");
            }
        } else {
            mostrarErro("Selecione uma bicicleta para excluir.");
        }
    }

    // Evento do botão Limpar
    @FXML
    private void btnLimparAction(ActionEvent event) {
        limparCampos();
    }

    // Quando uma bike for selecionada na tabela
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

    // Limpa os campos da tela
    private void limparCampos() {

        txtMarca.clear();
        txtModelo.clear();
        txtTipo.clear();
        txtPreco.clear();

        // Remove seleção da tabela
        tblBike.getSelectionModel().clearSelection();

        btnExcluir.setDisable(true);

        // Coloca cursor no primeiro campo
        txtMarca.requestFocus();
    }

    // Carrega os registros do banco para a tabela
    private void carregarTabela() {

        BikeDAO dao = new BikeDAO();

        tblBike.setItems(
                FXCollections.observableArrayList(
                        dao.listaBikes()
                )
        );
    }

    // Exibe mensagem de sucesso
    private void mostrarSucesso(String mensagem) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        alert.showAndWait();
    }

    // Exibe mensagem de erro
    private void mostrarErro(String mensagem) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        alert.showAndWait();
    }

    // Validação dos campos obrigatórios
    private boolean validarCampos() {

        if (txtMarca.getText().trim().isEmpty()) {
            mostrarErro("Informe a marca.");
            return false;
        }

        if (txtModelo.getText().trim().isEmpty()) {
            mostrarErro("Informe o modelo.");
            return false;
        }

        if (txtTipo.getText().trim().isEmpty()) {
            mostrarErro("Informe o tipo.");
            return false;
        }

        if (txtPreco.getText().trim().isEmpty()) {
            mostrarErro("Informe o preço.");
            return false;
        }

        return true;
    }
}