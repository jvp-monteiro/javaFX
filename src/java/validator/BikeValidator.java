
package validator;

import static util.Alert.mostrarErro;

public class BikeValidator {

    public boolean validarCampos(String marca,
                                 String modelo,
                                 String tipo,
                                 String preco) {

        if (marca == null || marca.trim().isEmpty()) {
            mostrarErro("Informe a marca.");
            return false;
        }

        if (modelo == null || modelo.trim().isEmpty()) {
            mostrarErro("Informe o modelo.");
            return false;
        }

        if (tipo == null || tipo.trim().isEmpty()) {
            mostrarErro("Informe o tipo.");
            return false;
        }

        if (preco == null || preco.trim().isEmpty()) {
            mostrarErro("Informe o preço.");
            return false;
        }

        try {
            double valor = Double.parseDouble(preco.replace(",", "."));

            if (valor <= 0) {
                mostrarErro("O preço deve ser maior que zero.");
                return false;
            }

        } catch (NumberFormatException e) {
            mostrarErro("Preço inválido.");
            return false;
        }

        return true;
    }
}