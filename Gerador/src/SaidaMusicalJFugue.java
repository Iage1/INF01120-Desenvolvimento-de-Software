import java.io.File;
import java.io.IOException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Sequence;
import org.jfugue.pattern.Pattern;
import org.jfugue.midi.MidiFileManager;

// Camada de saída: a ÚNICA classe que conhece o JFugue.
public class SaidaMusicalJFugue {

    private Sequencer sequencer;
    private File tempMidi;
    private volatile boolean paused = false;
    private volatile boolean playing = false;
    private long pausePosition = 0;

    public synchronized void tocar(String staccato) {
        stopAndClose();
        try {
            Pattern pattern = new Pattern(staccato);
            tempMidi = File.createTempFile("gerador_musica", ".mid");
            tempMidi.deleteOnExit();
            MidiFileManager.savePatternToMidi(pattern, tempMidi);

            Sequence seq = MidiSystem.getSequence(tempMidi);
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(seq);
            sequencer.addMetaEventListener(meta -> {
                if (meta.getType() == 47) { // end of track
                    stopAndClose();
                }
            });
            sequencer.start();
            playing = true;
            paused = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void pausar() {
        try {
            if (sequencer != null && sequencer.isOpen() && sequencer.isRunning()) {
                pausePosition = sequencer.getMicrosecondPosition();
                sequencer.stop();
                paused = true;
                playing = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void retomar() {
        try {
            if (sequencer != null && sequencer.isOpen() && paused) {
                sequencer.setMicrosecondPosition(pausePosition);
                sequencer.start();
                paused = false;
                playing = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean estaTocando() {
        return sequencer != null && sequencer.isOpen() && sequencer.isRunning();
    }

    public synchronized boolean estaPausado() {
        return paused;
    }

    private synchronized void stopAndClose() {
        try {
            if (sequencer != null) {
                if (sequencer.isRunning()) {
                    sequencer.stop();
                }
                if (sequencer.isOpen()) {
                    sequencer.close();
                }
                sequencer = null;
            }
            playing = false;
            paused = false;
            pausePosition = 0;
            if (tempMidi != null && tempMidi.exists()) {
                tempMidi.delete();
                tempMidi = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvarMidi(String staccato, File arquivo) throws IOException {
        MidiFileManager.savePatternToMidi(new Pattern(staccato), arquivo);
    }
}