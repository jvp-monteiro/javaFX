package validator;

public interface Validator<T> {
    boolean validar(T valor);
    String getmensagemErro();
    T getvalor();
}
