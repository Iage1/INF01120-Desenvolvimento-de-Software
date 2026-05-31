public class EventoMusical {

    private static final int SEM_VALOR = -1; //valor que não se aplica, para as pausas

    private final String nota;
    private final int oitava;
    private final int volume;
    private final int instrumento;
    private final boolean pausa;

    //um construtor pra nota e um construtor pra pausa
    public EventoMusical(String nota, int oitava, int volume, int instrumento){
        this.nota = nota;
        this.oitava = oitava;
        this.volume = volume;
        this.instrumento = instrumento;
        this.pausa = false;
    }

    public EventoMusical(){
        nota = null;
        oitava = SEM_VALOR;
        volume = SEM_VALOR;
        instrumento = SEM_VALOR;
        pausa = true;

    }

    //getters
    public boolean ehPausa(){
        return pausa;
    }
    public String obterNota(){
        return nota;
    }

    public int obterOitava(){
        return oitava;
    }

    public int obterVolume(){
        return volume;
    }

    public int obterInstrumento(){
        return instrumento;
    }

}
