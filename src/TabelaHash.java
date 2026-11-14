import java.text.Normalizer;

public class TabelaHash {
    ListaEncadeada[] baldes;   // o vetor de listas
    int M;                     // tamanho da tabela (número de baldes)

    public TabelaHash(int M) {
        this.M = M;
        this.baldes = new ListaEncadeada[M];   // cria o vetor (cheio de null)

        // cria uma lista vazia em cada posição
        for (int i = 0; i < M; i++) {
            baldes[i] = new ListaEncadeada();
        }
    }

    // função hash 1 que soma os códigos dos caracteres da chave e tira o resto por M
    public int hashSoma(String chave) {
        int soma = 0;
        for (int i = 0; i < chave.length(); i++) {
            soma += chave.charAt(i);   // cada char vira seu número
        }
        return soma % M;               // encaixa a soma entre 0 e M - 1
    }

    // converte o planeta para o formato da chave (maiúsculas, sem acento, sem espaços nas pontas)
    public static String normalizarChave(String texto) {
        String semAcento = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return semAcento.trim().toUpperCase();
    }

    public void inserir(Tripulante t) {
        String chave = normalizarChave(t.getPlaneta());     // pega o planeta no formato certo
        int indice = hashSoma(chave);;       // pega a chave da função hash
        baldes[indice].inserir(t);                     // guarda no lugar certo
    }

    // mostra todos os tripulantes de um planeta e devolve quantos encontrou
    public int buscarPorPlaneta(String planeta) {
        String chave = normalizarChave(planeta);  // pega o planeta no formato certo
        int indice = hashSoma(chave);       // descobre o lugar que ele está
        return baldes[indice].buscarPorPlaneta(chave);   // busca na lista da gaveta e devolve o resultado
    }

    // mostra a tabela inteira
    public void exibir() {
        for (int i = 0; i < M; i++) {
            System.out.println("Módulo [" + i + "]:");
            if (baldes[i].tamanho == 0) {                      // a lista desta gaveta está vazia?
                System.out.println("  (vazio)");
            } else {
                baldes[i].imprimir();  // imprime a lista desta gaveta
            }
        }
    }
}