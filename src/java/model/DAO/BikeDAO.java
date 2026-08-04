package model.DAO;

// Importações necessárias para conexão com banco de dados
import model.ConexaoBD;
import model.DTO.BikeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Importações para trabalhar com listas
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BikeDAO {
    private static final Logger logger = Logger.getLogger (BikeDAO.class.getName());

    // Método responsável por cadastrar uma bicicleta no banco.
    public void cadastrarBike(BikeDTO bicicleta) {
        // Comando SQL de inserção
        String sql = "INSERT INTO bicicleta (marca, modelo, tipo, preco) VALUES (?, ?, ?, ?)";

        try (Connection conexao = new ConexaoBD().conectar();
             PreparedStatement comandoSQL = conexao.prepareStatement(sql)) {

            // Preenche os parâmetros da consulta
            comandoSQL.setString(1, bicicleta.getMarca());
            comandoSQL.setString(2, bicicleta.getModelo());
            comandoSQL.setString(3, bicicleta.getTipo());
            comandoSQL.setDouble(4, bicicleta.getPreco());

            // Executa o INSERT
            comandoSQL.execute();

            System.out.println("Sucesso: Bike cadastrada!");


        } catch (SQLException e) {
            logger.log (Level. SEVERE, "Erro ao listar usuário", e);
            showError("Erro ao ao listar usuário.");
        }
    }

    private void showError(String s) {
    }

    // Método responsável por listar todas as bicicletas cadastradas
    public List<BikeDTO> listaBikes() {

        // Cria uma lista vazia para armazenar os resultados
        List<BikeDTO> listaBicicletas = new ArrayList<>();

        // Comando SQL de consulta
        String sql = "SELECT * FROM bicicleta";

        try (Connection conexao = new ConexaoBD().conectar();
             PreparedStatement comandoSQL = conexao.prepareStatement(sql);
             ResultSet resultadoConsulta = comandoSQL.executeQuery()) {

            // Percorre todos os registros retornados
            while (resultadoConsulta.next()) {

                // Cria um objeto BikeDTO
                BikeDTO bicicleta = new BikeDTO();

                // Preenche os atributos com os dados do banco
                bicicleta.setId(resultadoConsulta.getInt("id"));
                bicicleta.setMarca(resultadoConsulta.getString("marca"));
                bicicleta.setModelo(resultadoConsulta.getString("modelo"));
                bicicleta.setTipo(resultadoConsulta.getString("tipo"));
                bicicleta.setPreco(resultadoConsulta.getDouble("preco"));

                // Adiciona o objeto na lista
                listaBicicletas.add(bicicleta);
            }

        } catch (SQLException erro) {
            erro.printStackTrace();
        }

        // Retorna a lista preenchida
        return listaBicicletas;
    }

    // Método responsável por atualizar uma bicicleta existente
    public void alterarBike(BikeDTO bicicleta) {

        // Comando SQL de atualização
        String sql = "UPDATE bicicleta SET marca=?, modelo=?, tipo=?, preco=? WHERE id=?";

        try (Connection conexao = new ConexaoBD().conectar();
             PreparedStatement comandoSQL = conexao.prepareStatement(sql)) {

            // Define os novos valores
            comandoSQL.setString(1, bicicleta.getMarca());
            comandoSQL.setString(2, bicicleta.getModelo());
            comandoSQL.setString(3, bicicleta.getTipo());
            comandoSQL.setDouble(4, bicicleta.getPreco());

            // Define qual registro será alterado
            comandoSQL.setInt(5, bicicleta.getId());

            // Executa o UPDATE
            comandoSQL.execute();

            System.out.println("Sucesso: Bike atualizada!");

        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    // Método responsável por excluir uma bicicleta pelo ID
    public void excluirBike(int idBicicleta) {

        // Comando SQL de exclusão
        String sql = "DELETE FROM bicicleta WHERE id=?";

        try (Connection conexao = new ConexaoBD().conectar();
             PreparedStatement comandoSQL = conexao.prepareStatement(sql)) {

            // Define o ID da bicicleta que será removida
            comandoSQL.setInt(1, idBicicleta);

            // Executa o DELETE
            comandoSQL.execute();

            System.out.println("Sucesso: Bike removida!");

        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }
}