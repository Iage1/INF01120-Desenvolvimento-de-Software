public class AcaoAlterarBPM implements AcaoMusical {
    @Override
    public void executar(EstadoMusical estado){
        estado.alterarBPM(valor);
    }

}
