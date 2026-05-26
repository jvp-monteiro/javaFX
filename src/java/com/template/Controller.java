package com.template;

public class Controller {

    private BikeDAO bicicletaDAO = new BikeDAO();

    public void cadastrar(BikeDTO bicicleta) {
        bicicletaDAO.cadastrarBike(bicicleta);
    }

    public List<BikeDTO> listar() {
        return bicicletaDAO.listarBikes();
    }

    public void alterar(BikeDTO bicicleta) {
        bicicletaDAO.alterarBike(bicicleta);
    }

    public void excluir(int idBicicleta) {
        bicicletaDAO.excluirBike(idBicicleta);
    }
}
