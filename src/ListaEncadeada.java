public class ListaEncadeada {
    No inicio;     // o primeiro nó da corrente (null = lista vazia)
    int tamanho;   // quantos nós a lista tem

    public ListaEncadeada() {
        this.inicio = null;
        this.tamanho = 0;
    }

    // insere um tripulante no começo da lista
    public void inserir(Tripulante t) {
        No novo = new No(t);
        novo.proximo = inicio;  // o novo aponta para quem era o primeiro
        inicio = novo;          // agora o novo é o primeiro
        tamanho++;
    }

    // mostra a lista encadeada
    public void imprimir(){
        No atual = inicio;          // começa no primeiro nó
        while (atual != null) {               // enquanto a corrente não acabou
            System.out.println(atual.tripulante);         // imprime o tripulante deste nó
            atual = atual.proximo;            // anda para o próximo nó
        }
    }

    // busca tripulantes de um planeta
    public int buscarPorPlaneta(String planeta) {
        int encontrados = 0;
        No atual = inicio;
        while (atual != null) {
            if (TabelaHash.normalizarChave(atual.tripulante.getPlaneta()).equals(planeta)) {  // se o planeta deste tripulante é o planeta buscado
                System.out.println(atual.tripulante);   // imprime o tripulante
                encontrados += 1;             // conta mais um
            }
        atual = atual.proximo; // anda para o próximo (fora do if!)
        }
        return encontrados;
    }

    // remove o tripulante com base no código
    public boolean remover(int codigo) {
        No anterior = null;   // ninguém antes do primeiro
        No atual = inicio;

        while (atual != null) {
            if (atual.tripulante.getCodigo() == codigo) {   // achou o tripulante
                if (anterior == null) {
                    // caso especial em que o tripulante é o primeiro nó
                    inicio = atual.proximo;
                } else {
                    // caso normal em que o anterior pula o atual
                    anterior.proximo = atual.proximo;
                }
                tamanho--;
                return true;
            }
            anterior = atual;          // o anterior avança...
            atual = atual.proximo;     // e o atual também
        }
        return false;   // percorreu tudo e não achou
    }

    // conta quantos planetas diferentes existem na lista
    public int contarPlanetasDiferentes() {
        int diferentes = 0;
        No atual = inicio;

        // pra cada tripulante, verifica se o planeta dele já apareceu antes na lista
        while (atual != null) {                         // anda pela lista inteira
            String chaveAtual = TabelaHash.normalizarChave(atual.tripulante.getPlaneta());

            // olha do início até o atual
            boolean jaApareceu = false;
            No anterior = inicio;
            while (anterior != atual) {                 // para quando chega no atual
                String chaveAnterior = TabelaHash.normalizarChave(anterior.tripulante.getPlaneta());
                if (chaveAnterior.equals(chaveAtual)) {
                    jaApareceu = true;                  // achou o mesmo planeta antes
                    break;                              // não precisa olhar mais
                }
                anterior = anterior.proximo;
            }

            if (!jaApareceu) {
                diferentes++;                           // planeta novo nesta lista
            }
            atual = atual.proximo;
        }
        return diferentes;
    }
}