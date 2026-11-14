public class QuickSort {
    long comparacoes;   // quantas vezes comparou um código com o pivô
    long trocas;        // quantas trocas fez (incluindo colocar o pivô no lugar)
    boolean narrar = false;  // liga/desliga o modo passo a passo

    // ponto de entrada (zera os contadores e ordena o vetor inteiro)
    public void ordenar(Tripulante[] v) {
        comparacoes = 0;
        trocas = 0;
        quickSort(v, 0, v.length - 1);
    }

    // ordena o trecho de v entre as posições ini e fim (recursivo)
    private void quickSort(Tripulante[] v, int inicio, int fim) {
        if (inicio < fim) {                          // se o trecho tiver 2 ou mais elementos
            int p = particiona(v, inicio, fim);      // pivô vai para a posição p
            quickSort(v, inicio, p - 1);             // ordena os menores (esquerda)
            quickSort(v, p + 1, fim);             // ordena os maiores (direita)
        }
        // se inicio >= fim: 1 elemento ou nenhum → já está ordenado (caso base)
    }

    // coloca o pivô (último elemento) no lugar certo
    // menores à esquerda, maiores à direita. Devolve a posição do pivô.
    private int particiona(Tripulante[] v, int ini, int fim) {
        int pivo = v[fim].getCodigo();   // o pivô é o último do trecho
        int menores = ini;               // próxima vaga da área dos menores

        if (narrar) {
            System.out.println("\n★ Quick Sort · organizando os assentos [" + ini + " a " + fim + "]");
            System.out.println("  Pivô da vez: " + v[fim].etiqueta());
        }

        for (int j = ini; j < fim; j++) {         // percorre tudo, menos o pivô
            comparacoes++;
            if (v[j].getCodigo() < pivo) {        // é menor que o pivô?
                if (narrar) {
                    System.out.println("  " + v[j].etiqueta() + " < pivô? SIM → "
                            + v[j].primeiroNome() + " vai para o grupo da frente (assento [" + menores + "])");
                }
                swap(v, menores, j);              // vai para a vaga dos menores
                menores++;                        // a vaga avança
            } else if (narrar) {
                System.out.println("  " + v[j].etiqueta() + " < pivô? NÃO → "
                        + v[j].primeiroNome() + " fica no grupo do fundo");
            }
        }
        swap(v, menores, fim);           // pivô vai para a próxima vaga

        if (narrar) {
            System.out.println("  " + v[menores].primeiroNome() + " (o pivô) senta no assento ["
                    + menores + "], seu lugar definitivo!");
            System.out.println("  comparações: " + comparacoes + " | trocas: " + trocas);
        }
        return menores;                  // essa é a posição final do pivô
    }

    // troca dois tripulantes de lugar no vetor
    private void swap(Tripulante[] v, int a, int b) {
        Tripulante aux = v[a];
        v[a] = v[b];
        v[b] = aux;
        trocas++;
    }
}