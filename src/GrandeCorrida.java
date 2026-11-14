public class GrandeCorrida {

    static int[] TAMANHOS = {50, 1000, 10000};
    static String[] SITUACOES = {"Aleatório", "Já ordenado", "Ordem inversa"};
    static String[] ALGORITMOS = {"Bubble Sort", "Insertion Sort", "Quick Sort"};

    // resultados: [algoritmo][situação][tamanho]
    static long[][][] tempos = new long[3][3][3];        // em nanossegundos
    static long[][][] comparacoes = new long[3][3][3];
    static long[][][] trocas = new long[3][3][3];        // no Insertion: deslocamentos

    // faz uma cópia do vetor (cada algoritmo ordena a sua própria cópia)
    static Tripulante[] copiar(Tripulante[] original) {
        Tripulante[] copia = new Tripulante[original.length];
        for (int i = 0; i < original.length; i++) {
            copia[i] = original[i];
        }
        return copia;
    }

    // gera os dados de uma situação: 0 = aleatório, 1 = já ordenado, 2 = ordem inversa
    static Tripulante[] gerarSituacao(int situacao, int n) {
        Tripulante[] v = Gerador.gerarOrdenado(n);
        if (situacao == 0) {
            Gerador.embaralhar(v);
        } else if (situacao == 2) {
            Gerador.inverter(v);
        }
        return v;
    }

    // roda um algoritmo sobre o vetor e guarda tempo, comparações e trocas
    static void executar(int alg, int sit, int tam, Tripulante[] v) {
        long inicio, fim;

        if (alg == 0) {
            BubbleSort b = new BubbleSort();
            inicio = System.nanoTime();
            b.ordenar(v);
            fim = System.nanoTime();
            comparacoes[alg][sit][tam] = b.comparacoes;
            trocas[alg][sit][tam] = b.trocas;
        } else if (alg == 1) {
            InsertionSort ins = new InsertionSort();
            inicio = System.nanoTime();
            ins.ordenar(v);
            fim = System.nanoTime();
            comparacoes[alg][sit][tam] = ins.comparacoes;
            trocas[alg][sit][tam] = ins.deslocamentos;
        } else {
            QuickSort q = new QuickSort();
            inicio = System.nanoTime();
            q.ordenar(v);
            fim = System.nanoTime();
            comparacoes[alg][sit][tam] = q.comparacoes;
            trocas[alg][sit][tam] = q.trocas;
        }
        tempos[alg][sit][tam] = fim - inicio;
    }

    // confere se o vetor ficou mesmo em ordem crescente
    static boolean estaOrdenado(Tripulante[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            if (v[i].getCodigo() > v[i + 1].getCodigo()) {
                return false;
            }
        }
        return true;
    }

    // A Grande Corrida: 3 algoritmos x 3 situações x 3 tamanhos
    public static void correr() {
        System.out.println("\n A GRANDE CORRIDA VAI COMEÇAR!");

        for (int sit = 0; sit < 3; sit++) {
            for (int tam = 0; tam < 3; tam++) {
                // os MESMOS dados para os três algoritmos
                Tripulante[] base = gerarSituacao(sit, TAMANHOS[tam]);

                for (int alg = 0; alg < 3; alg++) {
                    Tripulante[] copia = copiar(base);
                    executar(alg, sit, tam, copia);
                    if (!estaOrdenado(copia)) {
                        System.out.println("ERRO: " + ALGORITMOS[alg] + " não ordenou corretamente!");
                    }
                }
            }
        }

        imprimirTabela("TEMPO (milissegundos)", tempos, true);
        imprimirTabela("COMPARAÇÕES", comparacoes, false);
        imprimirTabela("TROCAS (deslocamentos no Insertion Sort)", trocas, false);
    }

    // imprime a tabela
    static void imprimirTabela(String titulo, long[][][] dados, boolean emMilissegundos) {
        System.out.println("\n===== " + titulo + " =====");
        System.out.printf("%-15s %-14s %15s %15s %15s%n", "Algoritmo", "Situação", "n = 50", "n = 1.000", "n = 10.000");
        for (int alg = 0; alg < 3; alg++) {
            for (int sit = 0; sit < 3; sit++) {
                System.out.printf("%-15s %-14s", ALGORITMOS[alg], SITUACOES[sit]);
                for (int tam = 0; tam < 3; tam++) {
                    if (emMilissegundos) {
                        System.out.printf(" %15.3f", dados[alg][sit][tam] / 1_000_000.0);
                    } else {
                        System.out.printf(" %15d", dados[alg][sit][tam]);
                    }
                }
                System.out.println();
            }
        }
    }
}