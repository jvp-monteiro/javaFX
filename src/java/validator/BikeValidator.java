package validator;

public class BikeValidator {

    public static void validarCampos(String marca, String modelo, String tipo, String preco) throws IllegalArgumentException {
        if (marca == null || marca.trim().isEmpty() ||
                modelo == null || modelo.trim().isEmpty() ||
                tipo == null || tipo.trim().isEmpty() ||
                preco == null || preco.trim().isEmpty()) {

            throw new IllegalArgumentException("Preencha todos os campos.");
        }

        try {
            Double.parseDouble(preco);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O preço deve ser um número válido.");
        }
        public class Validador {

            public static boolean validarPreco(String preco) {
                try {
                    Double.parseDouble(preco);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
    }
}