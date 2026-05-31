package regras.acoes;

public class AcaoPausar implements AcaoMusical {
    @Override
    public EventoMusical executar(EstadoMusical estado) {
        return new EventoMusical();   // construtor de pausa (sem parametros)
    }
}