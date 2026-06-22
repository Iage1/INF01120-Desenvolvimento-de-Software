import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//produz lista de eventos de uma linha (voz)
public class Interpretador {
    private final DicionarioDeRegras dicionario;

    public Interpretador(DicionarioDeRegras dicionario) {
        this.dicionario = dicionario;

    }

    public List<EventoMusical> interpretar(String linha, EstadoMusical estado) {
        List<EventoMusical> eventos = new ArrayList<>();
        if (linha != null && linha.strip().equals("PIMENTA")) {
            pimenta();
            return eventos;
        }

        for (char caractere : linha.toCharArray()){                 //pra cada caractere, pergunta qual a ação,
            AcaoMusical acao = dicionario.obterAcao(caractere);     // executa a ação, e e verifica se cria um evento
            EventoMusical resultado = acao.executar(estado);
            if (resultado != null) {
                eventos.add(resultado);
                if (resultado.ehPausa()) {
                    estado.limparUltimaNota();
                } else {
                    estado.registrarUltimaNota(resultado.obterNota());
                }
            }
        }
    return eventos;
    }

    private void pimenta() {
        File arquivoImagem = Paths.get(System.getProperty("user.dir"), "Gerador", "lib", "pimenta.png").toFile();
        ImageIcon icone = new ImageIcon(arquivoImagem.getPath());
        if (icone.getIconWidth() <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Imagem não encontrada: " + arquivoImagem.getAbsolutePath(),
                    "Pimenta",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JLabel label = new JLabel(icone);
        JOptionPane.showMessageDialog(null,
                label,
                "Pimenta",
                JOptionPane.PLAIN_MESSAGE);
    }
}
