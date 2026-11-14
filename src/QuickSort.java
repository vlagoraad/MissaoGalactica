public class QuickSort {
    long comparacoes;   // quantas vezes comparou um código com o pivô
    long trocas;        // quantas trocas fez (incluindo colocar o pivô no lugar)

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
    // os menores ficam à esquerda, os maiores ficam à direita e devolve a posição do pivô
    private int particiona(Tripulante[] v, int inicio, int fim) {
        int pivo = v[fim].getCodigo();   // o pivô é o último do trecho
        int menores = inicio;               // próxima vaga da área dos menores

        for (int j = inicio; j < fim; j++) {         // percorre tudo, menos o pivô
            comparacoes++;
            if (v[j].getCodigo() < pivo) {        // se for menor que o pivô
                swap(v, menores, j);              // vai para a vaga dos menores
                menores++;                        // a vaga avança
            }
        }
        swap(v, menores, fim);           // pivô vai para a próxima vaga
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