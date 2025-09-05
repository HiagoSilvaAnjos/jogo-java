package Questoes;

import Exceptions.QuestaoException;
import Questoes.Questao;
import Questoes.QuestaoCompletarCodigo;
import Questoes.QuestaoIdentificarErro;
import Questoes.QuestaoMultiplaEscolha;
import enums.NivelDificuldade;
import enums.TipoQuestao;

import java.util.ArrayList;

public class QuestaoFactory {

    public static Questao criarQuestao(TipoQuestao tipo, NivelDificuldade nivel,
                                       String enunciado, String[] dados, String explicacao)
            throws QuestaoException {
        if (!validarDados(tipo, dados)) {
            throw new QuestaoException("Dados inválidos para criação da questão do tipo: " + tipo);
        }

        switch (tipo) {
            case MULTIPLA:
                return new QuestaoMultiplaEscolha(enunciado, nivel,
                        new String[]{dados[0], dados[1], dados[2], dados[3]}, dados[4], explicacao);

            case COMPLETAR:
                return new QuestaoCompletarCodigo(enunciado, nivel, dados[0], dados[1], explicacao);

            case IDENTIFICAR_ERRO:
                return new QuestaoIdentificarErro(enunciado, nivel, dados[0],
                        new String[]{dados[1], dados[2], dados[3], dados[4]}, dados[5], explicacao);

            default:
                throw new QuestaoException("Tipo de questão não suportado: " + tipo);
        }
    }

    public static ArrayList<Questao> criarQuestoesPorTopico(String topico) throws QuestaoException {
        ArrayList<Questao> questoes = new ArrayList<>();

        switch (topico.toLowerCase()) {
            case "encapsulamento":
                questoes.addAll(criarQuestoesEncapsulamento());
                break;
            case "herança":
                questoes.addAll(criarQuestoesHeranca());
                break;
            case "interface":
                questoes.addAll(criarQuestoesInterface());
                break;
            case "polimorfismo":
                questoes.addAll(criarQuestoesPolimorfismo());
                break;
            case "abstração":
                questoes.addAll(criarQuestoesAbstracao());
                break;
            default:
                throw new QuestaoException("Tópico não encontrado: " + topico);
        }

        return questoes;
    }

    public static boolean validarDados(TipoQuestao tipo, String[] dados) {
        if (dados == null) return false;

        switch (tipo) {
            case MULTIPLA:
                return dados.length == 5; // 4 alternativas + resposta correta
            case COMPLETAR:
                return dados.length == 2; // template + resposta
            case IDENTIFICAR_ERRO:
                return dados.length == 6; // código + 4 alternativas + justificativa
            default:
                return false;
        }
    }

    // Métodos privados para criar questões específicas por tópico
    private static ArrayList<Questao> criarQuestoesEncapsulamento() throws QuestaoException {
        ArrayList<Questao> questoes = new ArrayList<>();

        // 6 questões FÁCEIS
        for (int i = 0; i < 6; i++) {
            questoes.add(criarQuestaoEncapsulamento(NivelDificuldade.FACIL, i));
        }

        // 5 questões MÉDIAS
        for (int i = 0; i < 5; i++) {
            questoes.add(criarQuestaoEncapsulamento(NivelDificuldade.MEDIO, i));
        }

        // 4 questões DIFÍCEIS
        for (int i = 0; i < 4; i++) {
            questoes.add(criarQuestaoEncapsulamento(NivelDificuldade.DIFICIL, i));
        }

        return questoes;
    }

    private static Questao criarQuestaoEncapsulamento(NivelDificuldade nivel, int indice) throws QuestaoException {
        switch (nivel) {
            case FACIL:
                switch (indice) {
                    case 0:
                        return criarQuestao(TipoQuestao.MULTIPLA, nivel,
                                "O que é encapsulamento em POO?",
                                new String[]{"Ocultar dados e métodos", "Criar múltiplas classes", "Herdar características", "Implementar interfaces", "A"},
                                "Encapsulamento é o princípio de ocultar os detalhes internos de uma classe.");
                    case 1:
                        return criarQuestao(TipoQuestao.MULTIPLA, nivel,
                                "Qual palavra-chave torna um atributo privado em Java?",
                                new String[]{"public", "private", "protected", "static", "B"},
                                "A palavra-chave 'private' torna o atributo acessível apenas dentro da própria classe.");
                    default:
                        return criarQuestao(TipoQuestao.MULTIPLA, nivel,
                                "Questão de encapsulamento fácil " + (indice + 1),
                                new String[]{"Opção A", "Opção B", "Opção C", "Opção D", "A"},
                                "Explicação da questão fácil " + (indice + 1));
                }
            case MEDIO:
                return criarQuestao(TipoQuestao.COMPLETAR, nivel,
                        "Complete o código para criar um getter: public String getNome() { return ______; }",
                        new String[]{"public String getNome() { return ______; }", "nome"},
                        "O getter retorna o valor do atributo privado.");
            default: // DIFICIL
                return criarQuestao(TipoQuestao.IDENTIFICAR_ERRO, nivel,
                        "Identifique o erro no código de encapsulamento:",
                        new String[]{"public String nome; // Atributo público",
                                "Atributo deveria ser privado", "Falta construtor", "Falta getter", "Falta setter", "A"},
                        "Atributos devem ser privados para garantir encapsulamento adequado.");
        }
    }

    private static ArrayList<Questao> criarQuestoesHeranca() throws QuestaoException {
        ArrayList<Questao> questoes = new ArrayList<>();
        // Implementação similar ao encapsulamento
        for (int i = 0; i < 15; i++) {
            NivelDificuldade nivel = i < 6 ? NivelDificuldade.FACIL :
                    i < 11 ? NivelDificuldade.MEDIO : NivelDificuldade.DIFICIL;
            questoes.add(criarQuestao(TipoQuestao.MULTIPLA, nivel,
                    "Questão sobre Herança " + (i + 1),
                    new String[]{"Opção A", "Opção B", "Opção C", "Opção D", "A"},
                    "Explicação sobre herança " + (i + 1)));
        }
        return questoes;
    }

    private static ArrayList<Questao> criarQuestoesInterface() throws QuestaoException {
        ArrayList<Questao> questoes = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            NivelDificuldade nivel = i < 6 ? NivelDificuldade.FACIL :
                    i < 11 ? NivelDificuldade.MEDIO : NivelDificuldade.DIFICIL;
            questoes.add(criarQuestao(TipoQuestao.MULTIPLA, nivel,
                    "Questão sobre Interface " + (i + 1),
                    new String[]{"Opção A", "Opção B", "Opção C", "Opção D", "A"},
                    "Explicação sobre interface " + (i + 1)));
        }
        return questoes;
    }

    private static ArrayList<Questao> criarQuestoesPolimorfismo() throws QuestaoException {
        ArrayList<Questao> questoes = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            NivelDificuldade nivel = i < 6 ? NivelDificuldade.FACIL :
                    i < 11 ? NivelDificuldade.MEDIO : NivelDificuldade.DIFICIL;
            questoes.add(criarQuestao(TipoQuestao.MULTIPLA, nivel,
                    "Questão sobre Polimorfismo " + (i + 1),
                    new String[]{"Opção A", "Opção B", "Opção C", "Opção D", "A"},
                    "Explicação sobre polimorfismo " + (i + 1)));
        }
        return questoes;
    }

    private static ArrayList<Questao> criarQuestoesAbstracao() throws QuestaoException {
        ArrayList<Questao> questoes = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            NivelDificuldade nivel = i < 6 ? NivelDificuldade.FACIL :
                    i < 11 ? NivelDificuldade.MEDIO : NivelDificuldade.DIFICIL;
            questoes.add(criarQuestao(TipoQuestao.MULTIPLA, nivel,
                    "Questão sobre Abstração " + (i + 1),
                    new String[]{"Opção A", "Opção B", "Opção C", "Opção D", "A"},
                    "Explicação sobre abstração " + (i + 1)));
        }
        return questoes;
    }
}