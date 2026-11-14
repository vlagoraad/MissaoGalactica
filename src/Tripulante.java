public class Tripulante {
    // campos de cada tripulante
    private int codigo;
    private String nome;
    private String planeta;
    private String funcao;

    // construtor que recebe os dados do tripulante e guarda nos atributos
    public Tripulante(int codigo, String nome, String planeta, String funcao) {
        this.codigo = codigo;
        this.nome = nome;
        this.planeta = planeta;
        this.funcao = funcao;
    }

    // getters pras outras classes conseguirem ler os dados
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getPlaneta() {
        return planeta;
    }

    public String getFuncao() {
        return funcao;
    }

    // formatação de como o tripulante aparece na tela
    @Override
    public String toString() {
        return nome + " (" + codigo + ")" + " - " + planeta + " - " + funcao;
    }

    // nome com código pra narração
    public String etiqueta() {
        return nome + " (" + codigo + ")";
    }

    // só o primeiro nome pra narração
    public String primeiroNome() {
        return nome.split(" ")[0];
    }
}