import javax.swing.*;
import java.awt.*;
// ...existing code...

public class JanelaPrincipal extends JFrame {

    private final JTextArea areaTexto;
    private final JButton btnAbrir;
    private final JButton btnGerar;
    private final JButton btnPlayPause;
    private final JButton btnSalvar;

    // armazena a última representação Staccato gerada (para salvar em MIDI)
    private String ultimoStaccato = null;

    private final CompositorDeVozes compositor =
            new CompositorDeVozes(new Interpretador(new DicionarioDeRegras()));
    private final Gerador gerador = new Gerador();
    private final SaidaMusicalJFugue saida = new SaidaMusicalJFugue();

    public JanelaPrincipal() {
        super("Gerador de Música");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 900);
        setLocationRelativeTo(null);

        areaTexto = new JTextArea();
        areaTexto.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);

        btnAbrir = new JButton("Abrir TXT");
        btnGerar = new JButton("Gerar Música");
        btnPlayPause = new JButton("Play/Pause");
        btnSalvar = new JButton("Salvar MIDI");



        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAbrir);
        painelBotoes.add(btnGerar);
        painelBotoes.add(btnPlayPause);
        painelBotoes.add(btnSalvar);

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

        btnSalvar.addActionListener(e -> {
            // Se não houve geração recente, tenta gerar a partir do texto atual
            if (ultimoStaccato == null || ultimoStaccato.isBlank()) {
                String texto = areaTexto.getText();
                if (texto == null || texto.isBlank()) {
                    JOptionPane.showMessageDialog(this,
                            "Digite ou carregue um texto antes de salvar o MIDI.",
                            "Atenção",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ultimoStaccato = gerador.gerar(compositor.compor(texto));
            }

            // delega a lógica de diálogo e escrita para SaidaMusicalJFugue
            saida.salvarMidiComDialog(this, ultimoStaccato);
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
        // armazena para possível salvamento posterior
        this.ultimoStaccato = staccato;
        new Thread(() -> saida.tocar(staccato)).start();
    }
}