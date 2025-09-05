package enums;

public enum TipoQuestao {
    MULTIPLA("Múltipla Escolha"),
    COMPLETAR("Completar Código"),
    IDENTIFICAR_ERRO("Identificar Erro");

    private final String descricao;

    TipoQuestao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
