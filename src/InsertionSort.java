public class InsertionSort {
    long comparacoes;        // quantas vezes comparou dois códigos
    long deslocamentos;      // quantas vezes um tripulante deslizou para a direita
    boolean narrar = false;  // liga/desliga o modo passo a passo

    // ordena o vetor por código galáctico (crescente)
    public void ordenar(Tripulante[] v) {
        comparacoes = 0;
        deslocamentos = 0;
        int n = v.length; // quantas posições (tripulantes) o vetor tem

        for (int i = 1; i < n; i++) {        // começa no 1 pq o [0] sozinho já está ordenado
            Tripulante chave = v[i];         // usa a variável auxiliar pra tirar o que vai ser ordenado
            int j = i - 1;                   // começa olhando o vizinho da esquerda

            if (narrar) {
                System.out.println("\n★ Insertion Sort · rodada " + i);
                System.out.println("  " + chave.etiqueta() + " levanta e procura seu assento...");
            }

            while (j >= 0) {
                comparacoes++;
                if (v[j].getCodigo() > chave.getCodigo()) {   // se o vizinho é maior
                    if (narrar) {
                        System.out.println("  " + v[j].etiqueta() + " > " + chave.etiqueta()
                                + "? SIM → " + v[j].primeiroNome() + " desliza uma poltrona para trás!");
                    }
                    v[j + 1] = v[j];         // ele anda uma casa para a direita
                    deslocamentos++;
                    j--;                     // passa a olhar o próximo à esquerda
                } else {
                    if (narrar) {
                        System.out.println("  " + v[j].etiqueta() + " > " + chave.etiqueta()
                                + "? NÃO → achou o lugar!");
                    }
                    break;                   // se achou um menor, o buraco é aqui
                }
            }
            v[j + 1] = chave;                // coloca o tripulante no buraco

            if (narrar) {
                System.out.println("  " + chave.primeiroNome() + " senta no assento [" + (j + 1) + "]");
                System.out.println("  fim da rodada " + i + " → comparações: " + comparacoes
                        + " | deslocamentos: " + deslocamentos);
            }
        }
    }
}