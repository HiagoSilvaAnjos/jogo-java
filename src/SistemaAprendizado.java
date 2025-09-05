// =============================================
// Como reescrever este código: Tutorial passo a passo
// Este arquivo mostra como criar um sistema interativo de aprendizado em Java
// Siga os comentários para entender cada etapa e como você pode adaptar para seu projeto
// =============================================
// 1. Importe as classes necessárias para organizar seu projeto
import Core.ExercicioTopico;
import Core.Usuario;
import Exceptions.NavegacaoException;
import Exceptions.QuestaoException;
import Questoes.Questao;
import enums.TipoQuestao;
import java.util.InputMismatchException;
import java.util.Scanner;

// 2. Crie a classe principal do sistema
public class SistemaAprendizado {
    // 3. Defina constantes para facilitar manutenção e configuração
    public static final int QUESTOES_POR_TOPICO = 15; // Altere para mudar o número de questões
    public static final String VERSAO_SISTEMA = "1.0.0"; // Controle de versão

    // 4. Declare os atributos principais do sistema
    private Usuario usuario; // Armazena dados do usuário
    private boolean executando; // Controla o loop principal
    private Scanner scanner; // Permite ler dados do terminal

    // 5. Construa o sistema inicializando variáveis
    public SistemaAprendizado() {
        this.executando = true; // O sistema começa rodando
        this.scanner = new Scanner(System.in); // Scanner para entrada de dados
    }

    // 6. Método principal: inicia a sessão do usuário
    // Aqui você pode personalizar o fluxo inicial do seu sistema
    public void iniciarSessao() {
        exibirCabecalho(); // Exibe informações iniciais

        // Solicite o nome do usuário e valide a entrada
        System.out.print("Digite seu nome para iniciar: ");
        String nome = scanner.nextLine().trim();
        while (nome.isEmpty()) { // Validação simples para evitar nome vazio
            System.out.print("Nome não pode estar vazio. Digite seu nome: ");
            nome = scanner.nextLine().trim();
        }

        // Crie o usuário e inicialize estatísticas
        usuario = new Usuario(nome);
        usuario.getEstatisticas().iniciar();
        System.out.println("\n🎉 Bem-vindo(a), " + nome + "! Vamos aprender Java juntos!");

        // 7. Loop principal: mantém o sistema rodando até o usuário sair
        // Use um loop while para criar sistemas interativos
        while (executando) {
            try {
                mostrarMenuPrincipal(); // Exibe o menu principal
            } catch (Exception e) {
                gerenciarExcecoes(e); // Trata exceções de forma amigável
            }
        }

        // Finalize a sessão e exiba mensagem de despedida
        usuario.getEstatisticas().finalizar();
        System.out.println("\n👋 Obrigado por usar o Sistema de Aprendizado, " + usuario.getNome() + "!");
        System.out.println("Até a próxima sessão de estudos!");
        scanner.close(); // Sempre feche recursos ao final
    }

    // 8. Menu principal: oferece opções ao usuário
    // Use switch para facilitar manutenção e leitura
    public void mostrarMenuPrincipal() throws NavegacaoException {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  SISTEMA DE APRENDIZADO DE PROGRAMAÇÃO v" + VERSAO_SISTEMA);
        System.out.println("  Usuário: " + usuario.getNome() + " | Sessão: " + usuario.getEstatisticas().calcularTempoSessao() + "s");
        System.out.println("=".repeat(60));
        System.out.println("Olá, " + usuario.getNome() + "! O que deseja fazer hoje?");
        System.out.println();
        System.out.println("1. 📚 Aprender Java (POO)");
        System.out.println("2. 📊 Consultar Estatísticas");
        System.out.println("3. 🚪 Sair do Sistema");
        System.out.println();
        System.out.print("Escolha uma opção (1-3): ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            // Use switch para tratar cada opção do menu
            switch (opcao) {
                case 1:
                    mostrarMenuJava(); // Menu de tópicos
                    break;
                case 2:
                    mostrarEstatisticas(); // Estatísticas do usuário
                    break;
                case 3:
                    executando = false; // Encerra o sistema
                    break;
                default:
                    System.out.println("❌ Opção inválida! Escolha entre 1-3.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Digite apenas números!");
            scanner.nextLine(); // Limpar buffer inválido
        }
    }

    // 9. Menu de tópicos: permite escolher o conteúdo para estudar
    // Adicione ou remova tópicos conforme seu projeto
    public void mostrarMenuJava() throws NavegacaoException {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        MÓDULO: APRENDER JAVA (POO)");
        System.out.println("=".repeat(50));
        System.out.println("Escolha o tópico que deseja estudar:");
        System.out.println();
        System.out.println("1. 🔒 Encapsulamento");
        System.out.println("2. 🧬 Herança");
        System.out.println("3. 🔌 Interface");
        System.out.println("4. 🎭 Polimorfismo");
        System.out.println("5. 🎨 Abstração");
        System.out.println("6. ↩️  Voltar ao Menu Principal");
        System.out.println();
        System.out.print("Escolha uma opção (1-6): ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            // Use array para facilitar expansão de tópicos
            String[] topicos = {"", "encapsulamento", "herança", "interface", "polimorfismo", "abstração"};
            if (opcao >= 1 && opcao <= 5) {
                iniciarExercicioTopico(topicos[opcao]); // Inicia exercícios
            } else if (opcao == 6) {
                return; // Volta ao menu principal
            } else {
                System.out.println("❌ Opção inválida! Escolha entre 1-6.");
                mostrarMenuJava();
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Digite apenas números!");
            scanner.nextLine(); // Limpar buffer inválido
            mostrarMenuJava();
        }
    }

    // 10. Inicia exercícios do tópico escolhido
    // Aqui você pode personalizar o fluxo de perguntas e respostas
    private void iniciarExercicioTopico(String topico) {
        try {
            usuario.getEstatisticas().adicionarTopicoEstudado(topico); // Marca o tópico
            ExercicioTopico exercicio = new ExercicioTopico(topico, usuario.getEstatisticas());
            String modoOrdenacao = escolherModoOrdenacao(); // Escolha de ordenação
            exercicio.aplicarOrdenacao(modoOrdenacao);
            exercicio.carregarQuestoes(); // Carrega questões
            executarExercicios(exercicio); // Executa perguntas
        } catch (QuestaoException e) {
            System.out.println("❌ Erro ao carregar questões: " + e.getMessage());
        } catch (NavegacaoException e) {
            System.out.println("❌ Erro de navegação: " + e.getMessage());
        }
    }

    // 11. Permite escolher como as questões serão organizadas
    // Você pode adicionar outros modos de ordenação se quiser
    private String escolherModoOrdenacao() {
        System.out.println("\n📋 Como deseja organizar as questões?");
        System.out.println("1. 🔀 Embaralhadas (ordem aleatória)");
        System.out.println("2. ⬆️  Crescente (fácil → difícil)");
        System.out.println("3. ⬇️  Decrescente (difícil → fácil)");
        System.out.print("Escolha (1-3): ");
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            switch (opcao) {
                case 1: return "embaralhadas";
                case 2: return "crescente";
                case 3: return "decrescente";
                default:
                    System.out.println("⚠️  Opção inválida. Usando ordem aleatória.");
                    return "embaralhadas";
            }
        } catch (InputMismatchException e) {
            System.out.println("⚠️  Entrada inválida. Usando ordem aleatória.");
            scanner.nextLine();
            return "embaralhadas";
        }
    }

    // 12. Executa o fluxo de perguntas e respostas do exercício
    // Use comandos especiais para facilitar navegação do usuário
    private void executarExercicios(ExercicioTopico exercicio) throws NavegacaoException {
        System.out.println("\n🎯 Iniciando exercícios de " + exercicio.getTituloTopico().toUpperCase() + "!");
        System.out.println("Total de questões: " + QUESTOES_POR_TOPICO);

        // Loop para percorrer todas as questões do tópico
        while (exercicio.obterQuestaoAtual() != null) {
            Questao questaoAtual = exercicio.obterQuestaoAtual();

            // Exibe a questão atual
            System.out.println(questaoAtual.exibirQuestao());
            System.out.println("\nProgresso: " + exercicio.exibirBarraProgresso());

            // Mostra comandos especiais para navegação
            System.out.println("\n📝 Digite sua resposta ou escolha uma opção:");
            if (questaoAtual.getTipo() == TipoQuestao.MULTIPLA ||
                    questaoAtual.getTipo() == TipoQuestao.IDENTIFICAR_ERRO) {
                System.out.println("💡 Para múltipla escolha, digite a letra (A, B, C, D)");
            }

            System.out.println("⌨️  Comandos especiais:");
            if (exercicio.temQuestaoAnterior()) {
                System.out.println("   'V' ou 'VOLTAR' - Voltar questão anterior");
            }
            if (exercicio.temProximaQuestao()) {
                System.out.println("   'P' ou 'PULAR' - Pular questão atual");
            }
            System.out.println("   'M' ou 'MENU' - Voltar ao menu");
            System.out.print("\nSua escolha: ");

            String entrada = scanner.nextLine().trim().toUpperCase();

            // Processa comandos de navegação ou resposta
            if (entrada.equals("V") || entrada.equals("VOLTAR")) {
                if (exercicio.temQuestaoAnterior()) {
                    exercicio.voltar();
                    continue;
                } else {
                    System.out.println("❌ Não há questão anterior!");
                    continue;
                }
            } else if (entrada.equals("P") || entrada.equals("PULAR")) {
                exercicio.pularQuestao();
                if (exercicio.temProximaQuestao()) {
                    exercicio.avancar();
                } else {
                    break; // Última questão
                }
                continue;
            } else if (entrada.equals("M") || entrada.equals("MENU")) {
                exercicio.irParaMenu();
                return;
            } else {
                // Processa resposta do usuário
                boolean acertou = exercicio.responderAtual(entrada);

                // Pausa para o usuário ver o resultado
                System.out.print("\nPressione ENTER para continuar...");
                scanner.nextLine();

                // Avança para próxima questão se houver
                if (exercicio.temProximaQuestao()) {
                    exercicio.avancar();
                } else {
                    break; // Última questão concluída
                }
            }
        }

        // Ao final, mostra resumo do desempenho no tópico
        System.out.println(exercicio.gerarResumoDesempenho());
        System.out.println("\n🎉 Parabéns! Você concluiu o tópico: " + exercicio.getTituloTopico().toUpperCase());

        System.out.print("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    // 13. Exibe estatísticas do usuário
    // Mostre progresso geral e dicas visuais
    private void mostrarEstatisticas() {
        System.out.println(usuario.getEstatisticas().getResumo());

        System.out.println("\n📈 PROGRESSO DETALHADO:");
        System.out.println("Progresso geral: " + String.format("%.1f", usuario.getEstatisticas().getProgressoGeral()) + "%");

        // Exibe barra de progresso visual
        int progressoGeral = (int) (usuario.getEstatisticas().getProgressoGeral() / 10);
        System.out.print("Tópicos: [");
        for (int i = 0; i < 10; i++) {
            if (i < progressoGeral) {
                System.out.print("█");
            } else {
                System.out.print("░");
            }
        }
        System.out.println("] " + usuario.getEstatisticas().getTopicosEstudados().size() + "/5 tópicos");

        System.out.print("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    // 14. Exibe cabeçalho inicial do sistema
    // Personalize para seu projeto
    private void exibirCabecalho() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("            🎓 SISTEMA DE APRENDIZADO DE PROGRAMAÇÃO 🎓");
        System.out.println("                     Java - Programação Orientada a Objetos");
        System.out.println("                           Versão " + VERSAO_SISTEMA);
        System.out.println("=".repeat(70));
        System.out.println("📚 Aprenda os conceitos fundamentais de POO de forma interativa!");
        System.out.println("🎯 Tópicos: Encapsulamento | Herança | Interface | Polimorfismo | Abstração");
        System.out.println("=".repeat(70));
    }

    // 15. Gerencie exceções de forma amigável
    // Sempre trate erros para evitar travamentos
    public void gerenciarExcecoes(Exception e) {
        if (e instanceof NavegacaoException) {
            System.out.println("❌ Erro de navegação: " + e.getMessage());
        } else if (e instanceof QuestaoException) {
            System.out.println("❌ Erro na questão: " + e.getMessage());
        } else {
            System.out.println("❌ Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.print("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
}

