public class Oitava {
    private static final int MINIMA = 0;
    private static final int MAXIMA = 9;

    private final int valor;
    private final int valorBase;   //pra onde voltar se estourar o limite

    public Oitava(int valorBase) {
        this.valorBase = valorBase;
        this.valor = valorBase;
    }
    private Oitava(int valor, int valorBase) {
        this.valor = valor;
        this.valorBase = valorBase;
    }

    public Oitava aumentar() {
        if (valor < MAXIMA) return new Oitava(valor + 1, valorBase);
        return new Oitava(valorBase, valorBase);   //estourou volta à base
    }
    public Oitava diminuir() {
        if (valor > MINIMA) return new Oitava(valor - 1, valorBase);
        return new Oitava(valorBase, valorBase);
    }
    public int valor() { return valor; }
}