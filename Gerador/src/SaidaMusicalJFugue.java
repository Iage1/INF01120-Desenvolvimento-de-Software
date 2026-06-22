import java.io.File;
import java.io.IOException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Sequence;
import org.jfugue.pattern.Pattern;
import org.jfugue.midi.MidiFileManager;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;

//camada de saída
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

    /**
     * Abre um diálogo para salvar o Pattern (staccato) como arquivo MIDI.
     * Executa a escrita em background e exibe caixas de diálogo na EDT.
     */
    public void salvarMidiComDialog(Component parent, String staccato) {
        if (staccato == null || staccato.isBlank()) {
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(parent,
                    "Nenhuma música disponível para salvar. Gere a música primeiro.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE));
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Salvar arquivo MIDI");
        chooser.setFileFilter(new FileNameExtensionFilter("MIDI Files (*.mid)", "mid"));
        int userSelection = chooser.showSaveDialog(parent);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooser.getSelectedFile();
            if (!fileToSave.getName().toLowerCase().endsWith(".mid")) {
                fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".mid");
            }
            final File finalFile = fileToSave;

            new Thread(() -> {
                try {
                    salvarMidi(staccato, finalFile);
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(parent,
                            "Arquivo MIDI salvo em:\n" + finalFile.getAbsolutePath(),
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(parent,
                            "Erro ao salvar o arquivo MIDI:\n" + ex.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE));
                }
            }).start();
        }
    }
}