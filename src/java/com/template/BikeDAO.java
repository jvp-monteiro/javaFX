package com.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BikeDAO {

    public void cadastrarBike(BikeDTO bicicleta) {

        String sql = "INSERT INTO bicicleta (marca, modelo, tipo, preco) VALUES (?, ?, ?, ?)";

        try (Connection conexao = new ConexaoBD().conectar();
             PreparedStatement comandoSQL = conexao.prepareStatement(sql)) {

            comandoSQL.setString(1, bicicleta.getMarca());
            comandoSQL.setString(2, bicicleta.getModelo());
            comandoSQL.setString(3, bicicleta.getTipo());
            comandoSQL.setDouble(4, bicicleta.getPreco());

            comandoSQL.execute();

            System.out.println("Sucesso: Bike cadastrada!");

        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    public List<BikeDTO> listaBikes() {

        List<BikeDTO> listaBicicletas = new ArrayList<>();

        String sql = "SELECT * FROM bicicleta";

        try (Connection conexao = new ConexaoBD().conectar();
             PreparedStatement comandoSQL = conexao.prepareStatement(sql);
             ResultSet resultadoConsulta = comandoSQL.executeQuery()) {

            while (resultadoConsulta.next()) {

                BikeDTO bicicleta = new BikeDTO();

                bicicleta.setId(resultadoConsulta.getInt("id"));
                bicicleta.setMarca(resultadoConsulta.getString("marca"));
                bicicleta.setModelo(resultadoConsulta.getString("modelo"));
                bicicleta.setTipo(resultadoConsulta.getString("tipo"));
                bicicleta.setPreco(resultadoConsulta.getDouble("preco"));

                listaBicicletas.add(bicicleta);
            }

        } catch (SQLException erro) {
            erro.printStackTrace();
        }

        return listaBicicletas;
    }

    public void alterarBike(BikeDTO bicicleta) {

        String sql = "UPDATE bicicleta SET marca=?, modelo=?, tipo=?, preco=? WHERE id=?";

        try (Connection conexao = new ConexaoBD().conectar();
             PreparedStatement comandoSQL = conexao.prepareStatement(sql)) {

            comandoSQL.setString(1, bicicleta.getMarca());
            comandoSQL.setString(2, bicicleta.getModelo());
            comandoSQL.setString(3, bicicleta.getTipo());
            comandoSQL.setDouble(4, bicicleta.getPreco());
            comandoSQL.setInt(5, bicicleta.getId());

            comandoSQL.execute();

            System.out.println("Sucesso: Bike atualizada!");

        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    public void excluirBike(int idBicicleta) {

        String sql = "DELETE FROM bicicleta WHERE id=?";

        try (Connection conexao = new ConexaoBD().conectar();
             PreparedStatement comandoSQL = conexao.prepareStatement(sql)) {

            comandoSQL.setInt(1, idBicicleta);
            comandoSQL.execute();

            System.out.println("Sucesso: Bike removida!");

        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }
}