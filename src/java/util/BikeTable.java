package util;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DTO.BikeDTO;

public class BikeTable {

    public static void configurarColunas(
            TableColumn<BikeDTO, Integer> colId,
            TableColumn<BikeDTO, String> colMarca,
            TableColumn<BikeDTO, String> colModelo,
            TableColumn<BikeDTO, String> colTipo,
            TableColumn<BikeDTO, Double> colPreco) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
    }

    public static void configurarEventos(
            TableView<BikeDTO> tblBike,
            TextField txtMarca, TextField txtModelo, TextField txtTipo, TextField txtPreco,
            Button btnExcluir, Button btnAlterar) {

        // Seleção da tabela
        tblBike.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if (novo != null) {
                txtMarca.setText(novo.getMarca());
                txtModelo.setText(novo.getModelo());
                txtTipo.setText(novo.getTipo());
                txtPreco.setText(String.valueOf(novo.getPreco()));

                btnExcluir.setDisable(false);
                btnAlterar.setDisable(false);
            }
        });

        // Cor da linha selecionada
        tblBike.setRowFactory(tv -> {
            TableRow<BikeDTO> row = new TableRow<>();
            row.selectedProperty().addListener((obs, antigo, selecionado) -> {
                if (selecionado) {
                    row.setStyle("-fx-background-color:#90caf9;");
                } else {
                    row.setStyle("");
                }
            });
            return row;
        });
    }
}