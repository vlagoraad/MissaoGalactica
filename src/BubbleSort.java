public class BubbleSort {
    long comparacoes;   // quantas vezes comparou dois códigos
    long trocas;        // quantas vezes trocou dois tripulantes de lugar

    // ordena o vetor por código galáctico (crescente)
    public void ordenar(Tripulante[] v) {
        comparacoes = 0;
        trocas = 0;
        int n = v.length; // quantas posições (tripulantes) o vetor tem

        for (int i = 0; i < n - 1; i++) {            // cada volta é uma passagem
            boolean houveTroca = false;

            for (int j = 0; j < n - 1 - i; j++) {    // percorre os pares ainda não ordenados
                comparacoes++;
                if (v[j].getCodigo() > v[j + 1].getCodigo()) {                           // se o da esquerda tem código maior
                    // troca v[j] com v[j + 1]
                    Tripulante aux = v[j];      // guarda o da esquerda na variável auxiliar
                    v[j] = v[j + 1];            // o da direita vai para a esquerda
                    v[j + 1] = aux;             // o guardado (o antigo da esquerda) vai para a direita
                    trocas++;
                    houveTroca = true;
                }
            }

            if (houveTroca == false) {                // se a passagem inteira não trocou ninguém...
                break;                               // já está ordenado, pode parar
            }
        }
    }
}