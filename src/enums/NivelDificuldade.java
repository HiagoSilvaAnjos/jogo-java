package enums;

public enum NivelDificuldade {
    FACIL(1, "Fácil"),
    MEDIO(2, "Médio"),
    DIFICIL(3, "Difícil");

    private final int valor;
    private final String descricao;

    NivelDificuldade(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}

