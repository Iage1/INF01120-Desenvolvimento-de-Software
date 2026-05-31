public class AcaoAumentarOitava implements AcaoMusical {
    @Override
    public EventoMusical executar(EstadoMusical estado){
        estado.aumentarOitava();
        return null;
    }
}
