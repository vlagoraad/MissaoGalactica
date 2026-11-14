public class InsertionSort {
    long comparacoes;     // quantas vezes comparou dois códigos
    long deslocamentos;   // quantas vezes um tripulante deslizou para a direita

    // ordena o vetor por código galáctico (crescente)
    public void ordenar(Tripulante[] v) {
        comparacoes = 0;
        deslocamentos = 0;
        int n = v.length; // quantas posições (tripulantes) o vetor tem

        for (int i = 1; i < n; i++) {        // começa no 1 pq o [0] sozinho já está ordenado
            Tripulante chave = v[i];         // usa a variável auxiliar pra tirar o que vai ser ordenado
            int j = i - 1;                   // começa olhando o vizinho da esquerda

            while (j >= 0) {
                comparacoes++;
                if (v[j].getCodigo() > chave.getCodigo()) {   // se o vizinho é maior
                    v[j + 1] = v[j];         // ele anda uma casa para a direita
                    deslocamentos++;
                    j--;                     // passa a olhar o próximo à esquerda
                } else {
                    break;                   // se achou um menor, o buraco é aqui
                }
            }
            v[j + 1] = chave;                // coloca o tripulante no buraco
        }
    }
}