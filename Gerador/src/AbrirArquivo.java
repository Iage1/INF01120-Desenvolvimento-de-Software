import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;

public class AbrirArquivo {

    public static void abrir(JTextArea areaTexto, Component parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecione um arquivo TXT");

        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            File arquivo = chooser.getSelectedFile();
            try {
                String texto = Files.readString(arquivo.toPath());
                areaTexto.setText(texto);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parent,
                        "Não foi possível abrir o arquivo:\n" + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}