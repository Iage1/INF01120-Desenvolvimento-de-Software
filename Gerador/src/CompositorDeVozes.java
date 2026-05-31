import java.util.ArrayList;
import java.util.List;

public class CompositorDeVozes {

    //parâmetros base por voz
    private static final int[] OITAVA_BASE      = {6, 5, 4, 3};
    private static final int[] VOLUME_BASE      = {100, 80, 60, 40};
    private static final int[] INSTRUMENTO_BASE = {6, 20, 0, 70};

    private final Interpretador interpretador;

    public CompositorDeVozes(Interpretador interpretador) {
        this.interpretador = interpretador;
    }

    public List<Voz> compor(String texto) {
        EstadoMusical.reiniciarBpm();           //BPM volta a 120 a cada geração

        List<Voz> vozes = new ArrayList<>();                //divide texto em vozes na quebra de linha
        String[] linhas = texto.split("\n", -1);

        for (int i = 0; i < linhas.length; i++) {
            String linha = linhas[i];
            if (linha.isBlank()) { // ignora linhas vazias
                continue;
            }

            int atraso = extrairAtraso(linha);
            String conteudo = removerAtraso(linha);

            int idx = i % OITAVA_BASE.length;           //ciclico pra mais de 4 linhas
            EstadoMusical estado = new EstadoMusical(
                    OITAVA_BASE[idx], VOLUME_BASE[idx], INSTRUMENTO_BASE[idx]);

            List<EventoMusical> eventos = interpretador.interpretar(conteudo, estado);
            vozes.add(new Voz(i, atraso, eventos));
        }
        return vozes;
    }

    //extrai o número de atraso entre colchetes
    private int extrairAtraso(String linha) {
        String s = linha.trim();
        if (s.startsWith("[")) {
            int fim = s.indexOf(']');
            if (fim > 1) {
                try { return Integer.parseInt(s.substring(1, fim).trim()); }
                catch (NumberFormatException ex) { return 0; }
            }
        }
        return 0;
    }

    //devolve a linha sem o atraso
    private String removerAtraso(String linha) {
        String s = linha.trim();
        if (s.startsWith("[")) {
            int fim = s.indexOf(']');
            if (fim >= 0) return s.substring(fim + 1).stripLeading();
        }
        return s;
    }
}
