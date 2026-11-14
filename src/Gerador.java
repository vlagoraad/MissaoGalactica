import java.util.Random;

public class Gerador {
    private static Random sorteio = new Random();

    private static String[] PRIMEIROS = {"Zibo", "Quark", "Lumi", "Gloop", "Cometa", "Pixel",
            "Plim", "Fafá", "Orbi", "Tico", "Jujuba", "Kiko", "Zuzu", "Mimi", "Bolota", "Gugu"};
    private static String[] SOBRENOMES = {"Zumbido", "Quarkinho", "Lunar", "Gelatina", "Clotilde",
            "Pulsar", "Vortex", "Foguete", "Orbital", "Telescópio", "Kepler", "Meteoro", "Galáxia"};
    private static String[] PLANETAS = {"Marte", "Júpiter", "Plutão", "Vênus", "Mercúrio", "Vega",
            "Netuno", "Saturno", "Kepler", "Órion", "Urano", "Sírius", "Andrômeda", "Titã",
            "Gliese", "Proxima", "Europa", "Ceres"};
    private static String[] FUNCOES = {"Piloto", "Engenheiro", "Navegadora", "Cozinheiro",
            "Astrônoma", "Mecânica", "Cientista", "Médico de bordo", "Comunicações"};

    // sorteia um elemento qualquer de uma lista
    private static String sortear(String[] lista) {
        return lista[sorteio.nextInt(lista.length)];
    }

    // gera n tripulantes com códigos únicos, já em ordem CRESCENTE
    public static Tripulante[] gerarOrdenado(int n) {
        Tripulante[] v = new Tripulante[n];
        int degrau = 90000000 / n;      // espaço reservado para cada código

        for (int i = 0; i < n; i++) {
            int codigo = 10000000 + i * degrau + sorteio.nextInt(degrau);
            String nome = sortear(PRIMEIROS) + " " + sortear(SOBRENOMES);
            v[i] = new Tripulante(codigo, nome, sortear(PLANETAS), sortear(FUNCOES));
        }
        return v;
    }

    // embaralha o vetor com o algoritmo de Fisher-Yates
    public static void embaralhar(Tripulante[] v) {
        for (int i = v.length - 1; i > 0; i--) {
            int j = sorteio.nextInt(i + 1);     // sorteia uma posição de 0 a i
            Tripulante aux = v[i];              // troca v[i] com v[j]
            v[i] = v[j];
            v[j] = aux;
        }
    }

    // inverte o vetor: o primeiro vira o último, e assim por diante
    public static void inverter(Tripulante[] v) {
        for (int i = 0; i < v.length / 2; i++) {           // só até a metade
            int espelho = v.length - 1 - i;           // a posição correspondente na outra ponta
            Tripulante aux = v[i];                    // guarda o da esquerda
            v[i] = v[espelho];                        // o da direita vai para a esquerda
            v[espelho] = aux;                         // o guardado vai para a direita
        }
    }
}