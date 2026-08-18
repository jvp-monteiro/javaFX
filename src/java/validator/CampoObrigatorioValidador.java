package validator;

public class CampoObrigatorioValidador implements Validator<String>{
    private final String nomeCampo;
    private final String valor;
    private final String ;

    public CampoObrigatorioValidador(String nomeCampo, String valor) {
        this.nomeCampo = nomeCampo;
        this.valor = valor;
    }

    @Override
    public boolean validar(String valor) {
        return this.valor != null && this.valor.trim().isEmpty();
    }

    @Override
    public String getmensagemErro() {
        return "o campo"+ nomeCampo +" deve ser preenchido";
    }

    @Override
    public String getvalor() {
        return "";
    }
}
