public class AcaoSomarInstrumento implements AcaoMusical {

    private int valor;

    public AcaoSomarInstrumento(int valor){
        this.valor = valor;
    }

    @Override
    public EventoMusical executar(EstadoMusical estado){
        estado.somarInstrumento(valor);
        return null;
    }
}
