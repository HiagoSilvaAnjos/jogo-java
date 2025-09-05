package Questoes;

import enums.NivelDificuldade;
import enums.TipoQuestao;

public class QuestaoIdentificarErro extends Questao {
    private String codigoComErro;
    private String justificativaCorreta;
    private String[] alternativasErro;

    public QuestaoIdentificarErro(String enunciado, NivelDificuldade nivel,
                                  String codigo, String[] alternativas, String justificativa, String explicacao) {
        super(enunciado, nivel, TipoQuestao.IDENTIFICAR_ERRO, explicacao);
        this.codigoComErro = codigo;
        this.alternativasErro = alternativas.clone();
        this.justificativaCorreta = justificativa;
    }

    @Override
    public boolean verificarResposta(String resposta) {
        return resposta != null && resposta.trim().equalsIgnoreCase(justificativaCorreta.trim());
    }

    @Override
    public String exibirQuestao() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("=".repeat(60)).append("\n");
        sb.append("QUESTÃO [").append(tipo.getDescricao()).append(" - ").append(nivel.getDescricao()).append("]\n");
        sb.append("=".repeat(60)).append("\n");
        sb.append(enunciado).append("\n\n");
        sb.append("CÓDIGO COM ERRO:\n");
        sb.append("-".repeat(40)).append("\n");
        sb.append(codigoComErro).append("\n");
        sb.append("-".repeat(40)).append("\n\n");

        char opcao = 'A';
        for (String alternativa : alternativasErro) {
            sb.append(opcao).append(") ").append(alternativa).append("\n");
            opcao++;
        }
        sb.append("\n").append("-".repeat(60));
        return sb.toString();
    }

    public String getCodigoComErro() {
        return codigoComErro;
    }

    public String getJustificativaCorreta() {
        return justificativaCorreta;
    }

    @Override
    public String[] getAlternativas() {
        return alternativasErro.clone();
    }

    @Override
    public String getCorreta() {
        return justificativaCorreta;
    }
}