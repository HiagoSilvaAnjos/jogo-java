package Core;

import Exceptions.NavegacaoException;

public interface Navegavel {
    void avancar() throws NavegacaoException;
    void voltar() throws NavegacaoException;
    void irParaMenu() throws NavegacaoException;
}