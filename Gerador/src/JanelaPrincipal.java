import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {

    private final JTextArea areaTexto;
    private final JButton btnAbrir;
    private final JButton btnGerar;

    public JanelaPrincipal() {
        super("Gerador de Música");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        areaTexto = new JTextArea();
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);

        btnAbrir = new JButton("Abrir TXT");
        btnGerar = new JButton("Gerar Música [implementar]");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAbrir);
        painelBotoes.add(btnGerar);

        add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        btnAbrir.addActionListener(e -> AbrirArquivo.abrir(areaTexto, this));
        btnGerar.addActionListener(e -> gerarMusica());

        setVisible(true);
    }

    private void gerarMusica() {
        String texto = areaTexto.getText();
        if (texto == null || texto.isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Digite ou carregue um texto antes de gerar a música.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
            }
    }
}