package Questoes;

import enums.NivelDificuldade;
import enums.TipoQuestao;

public abstract class Questao {
    protected String enunciado;
    protected NivelDificuldade nivel;
    protected TipoQuestao tipo;
    protected String explicacao;
    protected String topicoRelacionado;

    public Questao(String enunciado, NivelDificuldade nivel, TipoQuestao tipo, String explicacao) {
        this.enunciado = enunciado;
        this.nivel = nivel;
        this.tipo = tipo;
        this.explicacao = explicacao;
    }

    // Getters
    public String getEnunciado() { return enunciado; }
    public NivelDificuldade getNivel() { return nivel; }
    public TipoQuestao getTipo() { return tipo; }
    public String getExplicacao() { return explicacao; }
    public String getTopicoRelacionado() { return topicoRelacionado; }

    // Métodos abstratos que devem ser implementados pelas subclasses
    public abstract boolean verificarResposta(String resposta);
    public abstract String exibirQuestao();
    public abstract String[] getAlternativas();
    public abstract String getCorreta();
}