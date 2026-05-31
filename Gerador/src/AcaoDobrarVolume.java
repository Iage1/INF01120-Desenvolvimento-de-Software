public class AcaoDobrarVolume implements AcaoMusical {
    @Override
    public EventoMusical executar(EstadoMusical estado){
        estado.dobrarVolume();
        return null;
    }
}
