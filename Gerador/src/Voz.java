import java.util.List;

public class Voz {
    private final int numero;
    private final int atraso;
    private final List<EventoMusical> eventos;

    public Voz(int numero, int atraso, List<EventoMusical> eventos) {
        this.numero = numero;
        this.atraso = atraso;
        this.eventos = eventos;
    }
    public int obterNumero() { return numero; }
    public int obterAtraso() { return atraso; }
    public List<EventoMusical> obterEventos() { return eventos; }
}
