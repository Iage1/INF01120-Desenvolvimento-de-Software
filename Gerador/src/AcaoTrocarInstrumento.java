public class AcaoTrocarInstrumento implements AcaoMusical {

    private final int instrumento;

    public AcaoTrocarInstrumento(int instrumento) {
        this.instrumento = instrumento;
    }

    @Override
    public EventoMusical executar(EstadoMusical estado) {
        estado.definirInstrumentoAtual(instrumento);
        return null;
    }
}