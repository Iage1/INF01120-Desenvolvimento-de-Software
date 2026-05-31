public class AcaoDobrarVolume implements AcaoMusical{
    @Override
    public void executar(EstadoMusical estado){
        estado.dobrarVolume();
    }
}
