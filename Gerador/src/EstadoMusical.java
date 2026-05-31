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
    private int oitavaAtual;
    private int oitavaInicial;
    private int volumeAtual;
    private int instrumentoAtual;

    //instanciação e parâmetros iniciais
    public EstadoMusical(int oitavaInicial, int volumeInicial, int instrumentoInicial) {
        this.oitavaInicial = oitavaInicial; //precisa guardar o valor da oitava inicial (olhar aumentarOitava)
        this.oitavaAtual = oitavaInicial;
        this.volumeAtual = volumeInicial;
        this.instrumentoAtual = instrumentoInicial;
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

    //getters e setters
    public int obterOitavaAtual(){
        return oitavaAtual;
    }

    public int obterVolumeAtual(){
        return volumeAtual;
    }

    public int obterInstrumentoAtual(){
        return instrumentoAtual;
    }

    public void definirInstrumentoAtual(int instrumento){
        instrumentoAtual = instrumento;
    }

    public static int obterBpmGlobal(){
        return bpmGlobal;
    }
}