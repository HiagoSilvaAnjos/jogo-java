// =============================================
// Classe Estatisticas
// Gerencia os dados de desempenho do usuário durante a sessão
// Armazena acertos, erros, pulos, tópicos estudados e tempo de sessão
// =============================================
package Core;

import java.util.ArrayList;

public class Estatisticas {
    private long inicioSessao;
    private long fimSessao;
    private ArrayList<String> topicosEstudados;
    private int acertos;
    private int erros;
    private int pulos;
    private int questoesRespondidas;
    private String topicoAtual;

    public Estatisticas() {
        this.topicosEstudados = new ArrayList<>();
        this.acertos = 0;
        this.erros = 0;
        this.pulos = 0;
        this.questoesRespondidas = 0;
    }

    // Inicia a contagem do tempo de sessão
    public void iniciar() {
    }

    // Finaliza a contagem do tempo de sessão
    public void finalizar() {
    }

    // Registra um acerto e incrementa o total de questões respondidas
    public void registrarAcerto() {
    }

    // Registra um erro e incrementa o total de questões respondidas
    public void registrarErro() {
    }

    // Registra um pulo de questão
    public void registrarPulo() {
    }

    // Adiciona um tópico estudado à lista, evitando duplicidade
    public void adicionarTopicoEstudado(String topico) {
    }

    public double calcularPercentualAcertos() {
        return 0;
    }

    // Calcula o tempo total da sessão em segundos
    public long calcularTempoSessao() {
        return 0;
    }

    // Gera um resumo textual das estatísticas da sessão
    public String getResumo() {
//       Utiliza StringBuilder para construir o resumo de forma eficiente, esse método formata as estatísticas
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("=".repeat(50)).append("\n");
        sb.append("         ESTATÍSTICAS DA SESSÃO\n");
        sb.append("=".repeat(50)).append("\n");
        sb.append("Questões respondidas: ").append(questoesRespondidas).append("\n");
        sb.append("Acertos: ").append(acertos).append("\n");
        sb.append("Erros: ").append(erros).append("\n");
        sb.append("Pulos: ").append(pulos).append("\n");
        sb.append("Percentual de acertos: ").append(String.format("%.1f", calcularPercentualAcertos())).append("%\n");
        sb.append("Tempo de sessão: ").append(calcularTempoSessao()).append(" segundos\n");
        sb.append("Tópicos estudados: ").append(topicosEstudados.size()).append("/5\n");
        if (!topicosEstudados.isEmpty()) {
            sb.append("Tópicos: ").append(String.join(", ", topicosEstudados)).append("\n");
        }
        sb.append("=".repeat(50));
        return sb.toString();
    }

    // Reseta todos os contadores e limpa os tópicos estudados
    public void resetarEstatisticas() {
        this.acertos = 0;
        this.erros = 0;
        this.pulos = 0;
        this.questoesRespondidas = 0;
        this.topicosEstudados.clear();
    }

    // Calcula o progresso geral do usuário em porcentagem
    public double getProgressoGeral() {
        return 0;
    }

    // Getters para acesso aos dados
    public int getAcertos() { return acertos; }
    public int getErros() { return erros; }
    public int getPulos() { return pulos; }
    public int getQuestoesRespondidas() { return questoesRespondidas; }
    public ArrayList<String> getTopicosEstudados() { return new ArrayList<>(topicosEstudados); }
    public String getTopicoAtual() { return topicoAtual; }
}
