package Exceptions;

public class NavegacaoException extends Exception {
    public NavegacaoException(String mensagem) {
        super(mensagem);
    }

    public NavegacaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}