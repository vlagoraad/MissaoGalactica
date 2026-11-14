import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    // leitor do teclado, usado pelo programa inteiro
    static Scanner entrada = new Scanner(System.in);

    // lê o arquivo e registra todos os tripulantes na tabela recebida
    public static void carregarArquivo(TabelaHash tabela) {
        try {
            Scanner leitor = new Scanner(new File("tripulantes.txt"), "UTF-8");
            while (leitor.hasNextLine()) {
                String[] partes = leitor.nextLine().split(";");
                int codigo = Integer.parseInt(partes[0]);
                tabela.inserir(new Tripulante(codigo, partes[1], partes[2], partes[3]));
            }
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo tripulantes.txt não encontrado!");
        }
    }

    // lê só os primeiros 'limite' tripulantes do arquivo, na ordem em que aparecem
    public static Tripulante[] lerPrimeiros(int limite) {
        Tripulante[] v = new Tripulante[limite];
        try {
            Scanner leitor = new Scanner(new File("tripulantes.txt"), "UTF-8");
            int pos = 0;
            while (leitor.hasNextLine() && pos < limite) {
                String[] partes = leitor.nextLine().split(";");
                v[pos] = new Tripulante(Integer.parseInt(partes[0]), partes[1], partes[2], partes[3]);
                pos++;
            }
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo tripulantes.txt não encontrado!");
        }
        return v;
    }

    // mostra todos os tripulantes de um vetor, um por linha
    public static void mostrar(Tripulante[] v) {
        for (int i = 0; i < v.length; i++) {
            System.out.println(v[i]);
        }
    }

    // lê um número digitado
    public static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = entrada.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("⚠ Digite apenas números, cadete!");
            }
        }
    }

    // lê um texto digitado e se a pessoa deixar em branco, pede de novo
    public static String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = entrada.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("⚠ Não pode ficar em branco, cadete!");
        }
    }

    // pergunta qual algoritmo usar e devolve 1, 2 ou 3
    public static int escolherAlgoritmo() {
        System.out.println("  1 - Bubble Sort");
        System.out.println("  2 - Insertion Sort");
        System.out.println("  3 - Quick Sort");
        while (true) {
            int alg = lerInteiro("Qual algoritmo? ");
            if (alg >= 1 && alg <= 3) {
                return alg;
            }
            System.out.println("Escolha 1, 2 ou 3.");
        }
    }

    // opção 1 - registra um novo tripulante na estação
    public static void registrar(TabelaHash tabela) {
        int codigo = lerInteiro("Código galáctico (8 dígitos): ");
        if (codigo < 10000000 || codigo > 99999999) {
            System.out.println("O código precisa ter exatamente 8 dígitos (e não começar com 0).");
            return;
        }
        if (tabela.existeCodigo(codigo)) {
            System.out.println("Já existe um tripulante com o código " + codigo + "!");
            return;
        }
        String nome = lerTexto("Nome: ");
        String planeta = lerTexto("Planeta natal: ");
        String funcao = lerTexto("Função a bordo: ");

        tabela.inserir(new Tripulante(codigo, nome, planeta, funcao));
        int modulo = tabela.hash(TabelaHash.normalizarChave(planeta));
        System.out.println( nome + " foi registrado(a) no módulo [" + modulo + "]. Bem-vindo(a) a bordo!");
    }

    // opção 6 - ordena todos os tripulantes da estação para o embarque na cápsula
    public static void organizarEmbarque(TabelaHash tabela) {
        Tripulante[] fila = tabela.paraVetor();
        if (fila.length == 0) {
            System.out.println("A estação está vazia: ninguém para embarcar.");
            return;
        }

        int alg = escolherAlgoritmo();
        long comparacoes = 0, movimentos = 0;
        String nomeMovimento = "Trocas";

        if (alg == 1) {
            BubbleSort b = new BubbleSort();
            b.ordenar(fila);
            comparacoes = b.comparacoes;
            movimentos = b.trocas;
        } else if (alg == 2) {
            InsertionSort ins = new InsertionSort();
            ins.ordenar(fila);
            comparacoes = ins.comparacoes;
            movimentos = ins.deslocamentos;
            nomeMovimento = "Deslocamentos";
        } else {
            QuickSort q = new QuickSort();
            q.ordenar(fila);
            comparacoes = q.comparacoes;
            movimentos = q.trocas;
        }

        System.out.println("\n=== Ordem de embarque na cápsula de retorno ===");
        for (int i = 0; i < fila.length; i++) {
            System.out.println("Assento " + (i + 1) + ": " + fila[i]);
        }
        System.out.println("Comparações: " + comparacoes + " | " + nomeMovimento + ": " + movimentos);
    }

    // opção 7 - modo passo a passo com até 10 tripulantes do arquivo
    public static void passoAPasso() {
        int n = lerInteiro("Quantos tripulantes (2 a 10)? ");
        if (n < 2 || n > 10) {
            System.out.println("⚠ O modo passo a passo aceita de 2 a 10 tripulantes.");
            return;
        }
        Tripulante[] fila = lerPrimeiros(n);
        int alg = escolherAlgoritmo();

        if (alg == 1) {
            BubbleSort b = new BubbleSort();
            b.narrar = true;
            b.ordenar(fila);
        } else if (alg == 2) {
            InsertionSort ins = new InsertionSort();
            ins.narrar = true;
            ins.ordenar(fila);
        } else {
            QuickSort q = new QuickSort();
            q.narrar = true;
            q.ordenar(fila);
        }

        System.out.println("\n=== Fila final da cápsula ===");
        mostrar(fila);
    }

    // desenha o menu na tela
    public static void mostrarMenu() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║          ★ MISSÃO GALÁCTICA ★            ║");
        System.out.println("║        Estação Espacial Órbita-7         ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1 - Registrar tripulante                 ║");
        System.out.println("║ 2 - Buscar tripulantes por planeta       ║");
        System.out.println("║ 3 - Remover tripulante (código)          ║");
        System.out.println("║ 4 - Mostrar módulos (tabela hash)        ║");
        System.out.println("║ 5 - Estatísticas da tabela               ║");
        System.out.println("║ 6 - Organizar embarque na cápsula        ║");
        System.out.println("║ 7 - Modo passo a passo                   ║");
        System.out.println("║ 8 - A Grande Corrida                     ║");
        System.out.println("║ 0 - Encerrar a missão                    ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    public static void main(String[] args) {
        // cria a estação e registra os tripulantes do arquivo
        TabelaHash tabela = new TabelaHash(13, 1);
        carregarArquivo(tabela);
        System.out.println(tabela.quantidade + " tripulantes chegaram à estação Órbita-7!");

        int opcao;
        do {
            mostrarMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    registrar(tabela);
                    break;
                case 2:
                    System.out.print("Qual planeta você procura? ");
                    String planeta = entrada.nextLine();
                    System.out.println("\n--- Tripulantes de " + planeta + " ---");
                    int encontrados = tabela.buscarPorPlaneta(planeta);
                    if (encontrados == 0) {
                        System.out.println("Nenhum tripulante desse planeta está na estação.");
                    } else {
                        System.out.println("Total: " + encontrados + " tripulante(s).");
                    }
                    break;
                case 3:
                    int codigo = lerInteiro("Código galáctico do tripulante: "); // pede o código
                    if (tabela.remover(codigo)) {                           // tenta remover e vê se deu certo
                    System.out.println("Tripulante " + codigo + " desembarcou da estação.");
                } else {
                    System.out.println("Nenhum tripulante com o código " + codigo + ".");
                }
                break;
                case 4:
                    tabela.exibir();
                    break;
                case 5:
                    tabela.exibirEstatisticas();
                    break;
                case 6:
                    organizarEmbarque(tabela);
                    break;
                case 7:
                    passoAPasso();
                    break;
                case 8:
                    GrandeCorrida.correr();
                    break;
                case 0:
                    System.out.println("Missão encerrada. Até a próxima, cadete!");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha um número de 0 a 8.");
            }
        } while (opcao != 0);
    }
}