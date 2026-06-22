public class EstadoMusical {

    //constantes
    private static final int BPM_INICIAL = 120;
    private static final int BPM_MINIMO = 1;
    private static final int TOTAL_INSTRUMENTOS = 128;

    //atributos globais
    private static int bpmGlobal = BPM_INICIAL;

    //atributos locais
    private Oitava oitava;
    private Volume volume;
    private int instrumentoAtual;
    private String ultimaNota = null;

    //construtor
    public EstadoMusical(int oitavaInicial, int volumeInicial, int instrumentoInicial) {
        this.oitava = new Oitava(oitavaInicial);
        this.volume = new Volume(volumeInicial);
        instrumentoAtual = instrumentoInicial;
    }

    //métodos
    public void aumentarOitava(){
        oitava = oitava.aumentar();
    }

    public void diminuirOitava(){
        oitava = oitava.diminuir();
    }

    public void dobrarVolume(){
        volume = volume.dobrar();
    }

    public void alterarBPM(int valor) {
        bpmGlobal += valor;
        if (bpmGlobal < BPM_MINIMO){
            bpmGlobal = BPM_MINIMO;
        }
    }

    public void somarInstrumento(int valor){
        instrumentoAtual += valor;
        instrumentoAtual = instrumentoAtual % TOTAL_INSTRUMENTOS; //torna a operação cíclica.
    }

    public void definirInstrumentoAtual(int instrumento){
        instrumentoAtual = instrumento;
    }

    public static void reiniciarBpm(){
        bpmGlobal = BPM_INICIAL;
    }

    public void registrarUltimaNota(String nota){
        ultimaNota = nota;
    }

    public void limparUltimaNota(){
        ultimaNota = null;
    }

    //getters
    public int obterOitavaAtual(){
        return oitava.valor();
    }

    public int obterVolumeAtual(){
        return volume.valor();
    }

    public int obterInstrumentoAtual(){
        return instrumentoAtual;
    }

    public static int obterBpmGlobal(){
        return bpmGlobal;
    }

    public String obterUltimaNota() {
        return ultimaNota;
    }
}
