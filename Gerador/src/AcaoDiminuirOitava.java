public class AcaoDiminuirOitava implements AcaoMusical {
    @Override
    public EventoMusical executar(EstadoMusical estado) {
        estado.diminuirOitava();
        return null;
    }
}
