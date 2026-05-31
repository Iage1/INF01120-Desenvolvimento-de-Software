public class AcaoTocarNota implements AcaoMusical{

    private final String nota;

    public AcaoTocarNota(String nota){
        this.nota = nota;
    }

    @Override
    public EventoMusical executar(EstadoMusical estado) {
        return new EventoMusical(nota, estado.obterOitavaAtual(), estado.obterVolumeAtual(), estado.obterInstrumentoAtual());
    }

}