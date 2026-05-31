public class AcaoAumentarOitava implements AcaoMusical {
    @Override
    public void executar(EstadoMusical estado){
        estado.aumentarOitava();
    }
}
