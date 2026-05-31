public class AcaoRepetirOuPausar implements AcaoMusical {
    @Override
    public EventoMusical executar(EstadoMusical estado) {
        String ultima = estado.obterUltimaNota();
        if (ultima != null) {  // se o anterior era nota, repete
            return new EventoMusical(
                    ultima,
                    estado.obterOitavaAtual(),
                    estado.obterVolumeAtual(),
                    estado.obterInstrumentoAtual()
            );
        }
        return new EventoMusical();   //do contrário, pausa
    }
}