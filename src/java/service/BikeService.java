package service;

import model.DAO.BikeDAO;
import model.DTO.BikeDTO;
import validator.BikeValidator;
import java.util.List;

public class BikeService {

    private final BikeDAO dao;

    public BikeService() {
        this.dao = new BikeDAO();
    }

    public void cadastrar(String marca, String modelo, String tipo, String precoStr) throws IllegalArgumentException {
        BikeValidator.validarCampos(marca, modelo, tipo, precoStr);

        BikeDTO bike = new BikeDTO();
        bike.setMarca(marca);
        bike.setModelo(modelo);
        bike.setTipo(tipo);
        bike.setPreco(Double.parseDouble(precoStr));

        dao.cadastrarBike(bike);
    }

    public void alterar(BikeDTO bikeSelecionada, String marca, String modelo, String tipo, String precoStr) throws IllegalArgumentException {
        if (bikeSelecionada == null) {
            throw new IllegalArgumentException("Selecione uma bicicleta para alterar.");
        }

        BikeValidator.validarCampos(marca, modelo, tipo, precoStr);

        bikeSelecionada.setMarca(marca);
        bikeSelecionada.setModelo(modelo);
        bikeSelecionada.setTipo(tipo);
        bikeSelecionada.setPreco(Double.parseDouble(precoStr));

        dao.alterarBike(bikeSelecionada);
    }

    public void excluir(BikeDTO bikeSelecionada) throws IllegalArgumentException {
        if (bikeSelecionada == null) {
            throw new IllegalArgumentException("Selecione uma bicicleta para excluir.");
        }

        dao.excluirBike(bikeSelecionada.getId());
    }

    public List<BikeDTO> listarTodas() {
        return dao.listaBikes();
    }
}