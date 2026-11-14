public class No {
    // o dado guardado neste nó
    Tripulante tripulante;

    // a seta para o próximo nó da lista
    No proximo;

    // construtor que recebe só o tripulante
    // o nó recém-criado ainda não aponta para ninguém
    public No(Tripulante tripulante) {
        this.tripulante = tripulante;
        this.proximo = null;
    }
}