import java.util.HashMap;
import java.util.Map;

public class DicionarioDeRegras {

    private static final int HARMONICA = 22;
    private static final int TUBULAR_BELLS = 15;
    private static final int CHURCH_ORGAN = 20;
    private static final int BPM_PASSO = 10;

    private final Map<Character, AcaoMusical> regrasFixas = new HashMap<>();

    public DicionarioDeRegras() {
        registrarNotas();
        registrarPausas();
        registrarControles();
        registrarInstrumentos();
    }

    private void registrarNotas() {
        regrasFixas.put('A', new AcaoTocarNota("A"));
        regrasFixas.put('B', new AcaoTocarNota("B"));
        regrasFixas.put('C', new AcaoTocarNota("C"));
        regrasFixas.put('D', new AcaoTocarNota("D"));
        regrasFixas.put('E', new AcaoTocarNota("E"));
        regrasFixas.put('F', new AcaoTocarNota("F"));
        regrasFixas.put('G', new AcaoTocarNota("G"));
        regrasFixas.put('H', new AcaoTocarNota("Bb"));
        regrasFixas.put('~', new AcaoTocarNota("Eb"));
    }

    private void registrarPausas() {
        for (char c = 'a'; c <= 'h'; c++) {
            regrasFixas.put(c, new AcaoPausar());
        }
    }

    private void registrarControles() {
        regrasFixas.put(' ', new AcaoDobrarVolume());
        regrasFixas.put('?', new AcaoAumentarOitava());
        regrasFixas.put('V', new AcaoDiminuirOitava());
        regrasFixas.put('>', new AcaoAlterarBPM(BPM_PASSO));
        regrasFixas.put('<', new AcaoAlterarBPM(-BPM_PASSO));
    }

    private void registrarInstrumentos() {
        regrasFixas.put('!', new AcaoTrocarInstrumento(HARMONICA));
        regrasFixas.put(';', new AcaoTrocarInstrumento(TUBULAR_BELLS));
        regrasFixas.put(',', new AcaoTrocarInstrumento(CHURCH_ORGAN));
    }

    public AcaoMusical obterAcao(char caractere) {
        if (regrasFixas.containsKey(caractere)) {
            return regrasFixas.get(caractere);
        }
        if (Character.isDigit(caractere)) {
            int valor = caractere - '0';
            if (valor % 2 == 0) {
                return new AcaoSomarInstrumento(valor);
            }
            return new AcaoTrocarInstrumento(TUBULAR_BELLS);
        }
        return new AcaoRepetirOuPausar();
    }
}
