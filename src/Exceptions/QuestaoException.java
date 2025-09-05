package Exceptions;

public class QuestaoException extends Exception {
    public QuestaoException(String mensagem) {
        super(mensagem);
    }

    public QuestaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}