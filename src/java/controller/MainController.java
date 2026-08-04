package controller;

import model.DAO.BikeDAO;
import model.DTO.BikeDTO;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import static util.Alert.mostrarConfirmacao;
import static util.Alert.mostrarErro;
import static util.Alert.mostrarSucesso;


public class MainController {


    // =========================
    // BOTÕES
    // =========================

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnLimpar;



    // =========================
    // CAMPOS
    // =========================

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtPreco;



    // =========================
    // TABELA
    // =========================

    @FXML
    private TableView<BikeDTO> tblBike;


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




    // =========================
    // INICIALIZAÇÃO DA TELA
    // =========================

    @FXML
    public void initialize(){


        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );


        colMarca.setCellValueFactory(
                new PropertyValueFactory<>("marca")
        );


        colModelo.setCellValueFactory(
                new PropertyValueFactory<>("modelo")
        );


        colTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo")
        );


        colPreco.setCellValueFactory(
                new PropertyValueFactory<>("preco")
        );



        btnExcluir.setDisable(true);
        btnAlterar.setDisable(true);



        // Seleção da tabela

        tblBike.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, antigo, novo) -> {


                    if(novo != null){


                        txtMarca.setText(
                                novo.getMarca()
                        );


                        txtModelo.setText(
                                novo.getModelo()
                        );


                        txtTipo.setText(
                                novo.getTipo()
                        );


                        txtPreco.setText(
                                String.valueOf(
                                        novo.getPreco()
                                )
                        );


                        btnExcluir.setDisable(false);

                        btnAlterar.setDisable(false);


                    }


                });



        // Cor da linha selecionada

        tblBike.setRowFactory(tv -> {


            TableRow<BikeDTO> row = new TableRow<>();


            row.selectedProperty()
                    .addListener((obs, antigo, selecionado)->{


                        if(selecionado){

                            row.setStyle(
                                    "-fx-background-color:#90caf9;"
                            );


                        }else{


                            row.setStyle("");

                        }


                    });



            return row;

        });



        carregarTabela();

    }






    // =========================
    // CADASTRAR
    // =========================


    @FXML
    private void btnCadastrarAction(ActionEvent event){


        if(!validarCampos()){

            return;

        }



        try{


            BikeDTO bike = new BikeDTO();


            bike.setMarca(
                    txtMarca.getText()
            );


            bike.setModelo(
                    txtModelo.getText()
            );


            bike.setTipo(
                    txtTipo.getText()
            );


            bike.setPreco(
                    Double.parseDouble(
                            txtPreco.getText()
                    )
            );



            BikeDAO dao = new BikeDAO();


            dao.cadastrarBike(bike);



            carregarTabela();

            limparCampos();



            mostrarSucesso(
                    "Bike cadastrada com sucesso!"
            );



        }catch(NumberFormatException e){


            mostrarErro(
                    "Digite um preço válido."
            );


        }



    }






    // =========================
    // ALTERAR
    // =========================


    @FXML
    private void btnAlterarAction(ActionEvent event){



        BikeDTO bike =
                tblBike.getSelectionModel()
                        .getSelectedItem();



        if(bike == null){


            mostrarErro(
                    "Selecione uma bicicleta."
            );


            return;

        }



        if(!validarCampos()){

            return;

        }




        bike.setMarca(
                txtMarca.getText()
        );


        bike.setModelo(
                txtModelo.getText()
        );


        bike.setTipo(
                txtTipo.getText()
        );


        bike.setPreco(
                Double.parseDouble(
                        txtPreco.getText()
                )
        );




        BikeDAO dao = new BikeDAO();


        dao.alterarBike(bike);



        carregarTabela();

        limparCampos();



        mostrarSucesso(
                "Bike alterada com sucesso!"
        );


    }







    // =========================
    // EXCLUIR
    // =========================


    @FXML
    private void btnExcluirAction(ActionEvent event){



        BikeDTO bike =
                tblBike.getSelectionModel()
                        .getSelectedItem();




        if(bike == null){


            mostrarErro(
                    "Selecione uma bicicleta."
            );


            return;

        }




        if(mostrarConfirmacao(
                "Deseja realmente excluir esta bicicleta?"
        )){


            BikeDAO dao = new BikeDAO();



            dao.excluirBike(
                    bike.getId()
            );



            carregarTabela();

            limparCampos();



            mostrarSucesso(
                    "Bike excluída com sucesso!"
            );


        }



    }






    // =========================
    // LIMPAR
    // =========================


    @FXML
    private void btnLimparAction(ActionEvent event){


        limparCampos();


    }







    // =========================
    // LIMPAR CAMPOS
    // =========================


    private void limparCampos(){


        txtMarca.clear();

        txtModelo.clear();

        txtTipo.clear();

        txtPreco.clear();



        tblBike.getSelectionModel()
                .clearSelection();



        btnExcluir.setDisable(true);

        btnAlterar.setDisable(true);



        txtMarca.requestFocus();


    }







    // =========================
    // CARREGAR TABELA
    // =========================


    private void carregarTabela(){


        BikeDAO dao = new BikeDAO();



        tblBike.setItems(

                FXCollections.observableArrayList(

                        dao.listaBikes()

                )

        );


    }







    // =========================
    // VALIDAÇÃO
    // =========================


    private boolean validarCampos(){



        if(txtMarca.getText().trim().isEmpty()
                ||
                txtModelo.getText().trim().isEmpty()
                ||
                txtTipo.getText().trim().isEmpty()
                ||
                txtPreco.getText().trim().isEmpty()){


            mostrarErro(
                    "Preencha todos os campos."
            );


            return false;

        }




        try{


            Double.parseDouble(
                    txtPreco.getText()
            );


        }catch(NumberFormatException e){



            mostrarErro(
                    "Preço deve ser um número."
            );


            return false;


        }




        return true;


    }



}