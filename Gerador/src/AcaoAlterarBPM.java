public class AcaoAlterarBPM implements AcaoMusical {

    private int valor; //valor da alteração tem que ser passado como parametro na instanciação

    public AcaoAlterarBPM(int valor){
        this.valor = valor;
    }

    @Override
    public EventoMusical executar(EstadoMusical estado){
        estado.alterarBPM(valor);
        return null;
    }

}
