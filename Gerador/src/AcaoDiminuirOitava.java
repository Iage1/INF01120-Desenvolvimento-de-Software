public class AcaoDiminuirOitava implements AcaoMusical{
    @Override
    public void executar(EstadoMusical estado) {
        estado.diminuirOitava();
    }
}
