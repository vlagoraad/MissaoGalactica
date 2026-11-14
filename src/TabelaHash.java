import java.text.Normalizer;

public class TabelaHash {
    ListaEncadeada[] baldes;   // o vetor de listas
    int M;                     // tamanho da tabela (número de baldes)
    int quantidade;
    int tipoHash;   // 1 = soma simples, 2 = soma ponderada

    public TabelaHash(int M, int tipoHash) {
        this.quantidade = 0;
        this.M = M;
        this.tipoHash = tipoHash;
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

    // função hash 2 que multiplica o código de cada letra pela sua posição na palavra
    public int hashPonderado(String chave) {
        int soma = 0;
        for (int i = 0; i < chave.length(); i++) {
            soma += chave.charAt(i) * (i + 1);
        }
        return soma % M;
    }

    // chama a função hash escolhida na criação da tabela
    public int hash(String chave) {
        if (tipoHash == 1) {
            return hashSoma(chave);
        } else {
            return hashPonderado(chave);
        }
    }

    // converte o planeta para o formato da chave (maiúsculas, sem acento, sem espaços nas pontas)
    public static String normalizarChave(String texto) {
        String semAcento = Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return semAcento.trim().toUpperCase();
    }

    public void inserir(Tripulante t) {
        String chave = normalizarChave(t.getPlaneta());     // pega o planeta no formato certo
        int indice = hash(chave);;       // pega a chave da função hash
        baldes[indice].inserir(t);                     // guarda no lugar certo
        quantidade += 1;
    }

    // mostra todos os tripulantes de um planeta e devolve quantos encontrou
    public int buscarPorPlaneta(String planeta) {
        String chave = normalizarChave(planeta);  // pega o planeta no formato certo
        int indice = hash(chave);       // descobre o lugar que ele está
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

    // remove um tripulante pelo código, tem que procurar em todos os módulos
    public boolean remover(int codigo) {
        for (int i = 0; i < M; i++) {
            if (baldes[i].remover(codigo)) {          // tenta remover da gaveta
                quantidade -= 1;
                return true;    // achou e removeu então não precisa olhar as outras
            }
        }
        return false;             // passou por todas as gavetas e não achou
    }

    // indica o quanto a estação está lotada
    public double fatorDeCarga() {
        return (double) quantidade / M; // fator de carga = quantidade de tripulantes / número de módulos (n / M)
    }

    // indica quantos módulos estão vazios
    public int baldesVazios() {
        int vazios = 0;
        for (int i = 0; i < M; i++) {
            if (baldes[i].tamanho == 0) {        // se a gaveta estiver vazia
                vazios += 1; // conta mais um
            }
        }
        return vazios;
    }

    // tamanho da lista mais lotada
    public int maiorLista() {
        int maior = 0;                       // o recorde começa em 0
        for (int i = 0; i < M; i++) {
            if (baldes[i].tamanho > maior) {     // se esta gaveta tem mais que o recorde
                maior = baldes[i].tamanho;                  // o recorde passa a ser o tamanho dela
            }
        }
        return maior;
    }

    // conta as colisões reais de planetas diferentes que caíram no mesmo módulo
    public int colisoes() {
        int total = 0;
        for (int i = 0; i < M; i++) {
            if (baldes[i].tamanho != 0) {                    // só gavetas que não estão vazias
                total += baldes[i].contarPlanetasDiferentes() - 1;;       // planetas diferentes desta gaveta - 1
            }
        }
        return total;
    }

    // mostra as estatísticas da estação
    public void exibirEstatisticas() {
        System.out.println("===== ESTATÍSTICAS DA ESTAÇÃO (M = " + M + ") =====");
        System.out.println("Tripulantes registrados: " + quantidade);
        System.out.println("Fator de carga:          " + String.format("%.2f", fatorDeCarga()));
        System.out.println("Módulos vazios:          " + baldesVazios() + " de " + M);
        System.out.println("Maior lista:             " + maiorLista() + " tripulantes");
        System.out.println("Colisões reais:          " + colisoes());
    }
}