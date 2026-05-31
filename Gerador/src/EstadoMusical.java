public class EstadoMusical {

    //constantes
    private static final int BPM_INICIAL = 120;
    private static final int BPM_MINIMO = 1;
    private static final int OITAVA_MAXIMA = 9;
    private static final int OITAVA_MINIMA = 0;
    private static final int VOLUME_MAXIMO = 127;
    private static final int TOTAL_INSTRUMENTOS = 128;

    //atributos globais
    private static int bpmGlobal = BPM_INICIAL;

    //atributos locais
    private final int oitavaInicial;
    private int oitavaAtual;
    private int volumeAtual;
    private int instrumentoAtual;
    private String ultimaNota = null;

    //construtor
    public EstadoMusical(int oitavaInicial, int volumeInicial, int instrumentoInicial) {
        this.oitavaInicial = oitavaInicial;
        oitavaAtual = oitavaInicial;
        volumeAtual = volumeInicial;
        instrumentoAtual = instrumentoInicial;
    }

    //métodos
    public void aumentarOitava(){
        if (oitavaAtual < OITAVA_MAXIMA){
            oitavaAtual++;
        }
        else {
            oitavaAtual = oitavaInicial;
        }
    }

    public void diminuirOitava(){
        if (oitavaAtual > OITAVA_MINIMA){
            oitavaAtual--;
        }
    }

    public void dobrarVolume(){
        volumeAtual *= 2;
        if (volumeAtual > VOLUME_MAXIMO){
            volumeAtual = VOLUME_MAXIMO;
        }
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

    public static void reiniciarBPM(){
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
        return oitavaAtual;
    }

    public int obterVolumeAtual(){
        return volumeAtual;
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