public class Volume {
    private static final int MAXIMO = 127;

    private final int valor;

    public Volume(int valor) {
        this.valor = Math.min(valor, MAXIMO);   //não passa do máximo
    }

    public Volume dobrar() {
        return new Volume(valor * 2);
    }
    public int valor() { return valor; }
}