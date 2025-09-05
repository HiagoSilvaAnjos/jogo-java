package Questoes;

import enums.NivelDificuldade;
import enums.TipoQuestao;

public class QuestaoCompletarCodigo extends Questao {
    private String templateCodigo;
    private String respostaEsperada;
    private String[] palavrasChave;

    public QuestaoCompletarCodigo(String enunciado, NivelDificuldade nivel,
                                  String template, String resposta, String explicacao) {
        super(enunciado, nivel, TipoQuestao.COMPLETAR, explicacao);
        this.templateCodigo = template;
        this.respostaEsperada = resposta;
        this.palavrasChave = resposta.split("\\s+");
    }

//    Falta implementar a lógica de verificação de resposta
    @Override
    public boolean verificarResposta(String resposta) {
        return false;
    }

    @Override
    public String exibirQuestao() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("=".repeat(60)).append("\n");
        sb.append("QUESTÃO [").append(tipo.getDescricao()).append(" - ").append(nivel.getDescricao()).append("]\n");
        sb.append("=".repeat(60)).append("\n");
        sb.append(enunciado).append("\n\n");
        sb.append("CÓDIGO PARA COMPLETAR:\n");
        sb.append("-".repeat(40)).append("\n");
        sb.append(templateCodigo).append("\n");
        sb.append("-".repeat(40));
        return sb.toString();
    }

    public String getTemplateCodigo() {
        return templateCodigo;
    }

    public String getRespostaEsperada() {
        return respostaEsperada;
    }

    @Override
    public String[] getAlternativas() {
        return new String[]{"Digite a palavra/código que completa corretamente"};
    }

    @Override
    public String getCorreta() {
        return respostaEsperada;
    }
}
