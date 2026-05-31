import java.io.File;
import java.io.IOException;
import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;
import org.jfugue.midi.MidiFileManager;

// Camada de saída: a ÚNICA classe que conhece o JFugue.
public class SaidaMusicalJFugue {

    public void tocar(String staccato) {
        new Player().play(staccato);
    }

    public void salvarMidi(String staccato, File arquivo) throws IOException {
        MidiFileManager.savePatternToMidi(new Pattern(staccato), arquivo);
    }
}