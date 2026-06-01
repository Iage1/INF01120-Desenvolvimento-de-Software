import java.util.HashMap;
import java.util.Map;

public class DicionarioDeRegras {

    private static final int HARMONICA = 22;
    private static final int TUBULAR_BELLS = 15;
    private static final int CHURCH_ORGAN = 20;
    private static final int BPM_PASSO = 10;

    private final Map<Character, AcaoMusical> regrasFixas = new HashMap<>();

    public DicionarioDeRegras() {
        //notas
        regrasFixas.put('A', new AcaoTocarNota("A"));
        regrasFixas.put('B', new AcaoTocarNota("B"));
        regrasFixas.put('C', new AcaoTocarNota("C"));
        regrasFixas.put('D', new AcaoTocarNota("D"));
        regrasFixas.put('E', new AcaoTocarNota("E"));
        regrasFixas.put('F', new AcaoTocarNota("F"));
        regrasFixas.put('G', new AcaoTocarNota("G"));
        regrasFixas.put('H', new AcaoTocarNota("Bb"));
        regrasFixas.put('~', new AcaoTocarNota("Eb"));

        //pausa
        for (char c = 'a'; c <= 'h'; c++) { //letras minúsculas
            regrasFixas.put(c, new AcaoPausar());
        }
        //volume
        regrasFixas.put(' ', new AcaoDobrarVolume());

        //oitavas
        regrasFixas.put('?', new AcaoAumentarOitava());
        regrasFixas.put('V', new AcaoDiminuirOitava());

        //bpm
        regrasFixas.put('>', new AcaoAlterarBPM(BPM_PASSO));
        regrasFixas.put('<', new AcaoAlterarBPM(-BPM_PASSO));

        //instrumento
        regrasFixas.put('!', new AcaoTrocarInstrumento(HARMONICA));
        regrasFixas.put(';', new AcaoTrocarInstrumento(TUBULAR_BELLS));
        regrasFixas.put(',', new AcaoTrocarInstrumento(CHURCH_ORGAN));

    }
    public AcaoMusical obterAcao(char caractere) {  //checa se se está no map, se sim devolve a ação de lá
        if (regrasFixas.containsKey(caractere)) {   //se não, vai pra Ação de repetir ou pausar
            return regrasFixas.get(caractere);
        }
        if (Character.isDigit(caractere)){    //se instrumento for par, soma ao instrumento, se for ímpar, troca pro Tubular Bells
            int valor = caractere - '0';    //converte char em int
            if (valor % 2 == 0){
                return new AcaoSomarInstrumento(valor);
            }
            return new AcaoTrocarInstrumento(TUBULAR_BELLS);
        }
        return new AcaoRepetirOuPausar();
    }

}