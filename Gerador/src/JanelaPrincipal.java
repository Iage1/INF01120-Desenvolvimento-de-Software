import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {

    private final JTextArea areaTexto;
    private final JButton btnAbrir;
    private final JButton btnGerar;
    private final JButton btnPlayPause;

    private final CompositorDeVozes compositor =
            new CompositorDeVozes(new Interpretador(new DicionarioDeRegras()));
    private final Gerador gerador = new Gerador();
    private final SaidaMusicalJFugue saida = new SaidaMusicalJFugue();

    public JanelaPrincipal() {
        super("Gerador de Música");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        areaTexto = new JTextArea();
        areaTexto.setFont(new Font("Arial", Font.PLAIN, 20));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);

        btnAbrir = new JButton("Abrir TXT");
        btnGerar = new JButton("Gerar Música");
        btnPlayPause = new JButton("Play/Pause");



        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAbrir);
        painelBotoes.add(btnGerar);
        painelBotoes.add(btnPlayPause);

        add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        btnAbrir.addActionListener(e -> AbrirArquivo.abrir(areaTexto, this));
        btnGerar.addActionListener(e -> gerarMusica());
        btnPlayPause.addActionListener(e -> {
            if (saida.estaTocando()) {
                saida.pausar();
            } else if (saida.estaPausado()) {
                saida.retomar();
            } else {
                gerarMusica();
            }
        });

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

        String staccato = gerador.gerar(compositor.compor(texto));
        new Thread(() -> saida.tocar(staccato)).start();
    }
}