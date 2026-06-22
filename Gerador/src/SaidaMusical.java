import java.io.File;
import java.io.IOException;

public interface SaidaMusical {
    void tocar(String staccato);
    void salvarMidi(String staccato, File arquivo) throws IOException;
    void pausar();
    void retomar();
    boolean estaTocando();
    boolean estaPausado();
}
