import org.jfugue.player.Player;

public class GerarMusica {

    private static final int OITAVA_INICIAL = 5;
    private static final int VOLUME_INICIAL = 64;
    private static final int INSTRUMENTO_INICIAL = 0;

    public static void tocar(String texto) {
        if (texto == null || texto.isBlank()) {
            return;
        }

        EstadoMusical.reiniciarBPM();
        String padrao = montarPadrao(texto);
        if (padrao.isBlank()) {
            return;
        }

        Player player = new Player();
        player.play(padrao);
    }

    private static String montarPadrao(String texto) {
        DicionarioDeRegras regras = new DicionarioDeRegras();
        EstadoMusical estado = new EstadoMusical(OITAVA_INICIAL, VOLUME_INICIAL, INSTRUMENTO_INICIAL);

        StringBuilder builder = new StringBuilder();
        builder.append("T").append(EstadoMusical.obterBpmGlobal()).append(" ");
        builder.append("I").append(INSTRUMENTO_INICIAL).append(" ");

        int ultimoInstrumento = estado.obterInstrumentoAtual();
        int ultimoBpm = EstadoMusical.obterBpmGlobal();
        int ultimoVolume = estado.obterVolumeAtual();

        for (char caractere : texto.toCharArray()) {
            if (caractere == '\n' || caractere == '\r' || caractere == '\t') {
                continue;
            }

            AcaoMusical acao = regras.obterAcao(caractere);
            int antesInstrumento = estado.obterInstrumentoAtual();
            int antesBpm = EstadoMusical.obterBpmGlobal();
            int antesVolume = estado.obterVolumeAtual();

            EventoMusical evento = acao.executar(estado);

            if (estado.obterInstrumentoAtual() != antesInstrumento) {
                builder.append("I").append(estado.obterInstrumentoAtual()).append(" ");
                ultimoInstrumento = estado.obterInstrumentoAtual();
            }
            if (EstadoMusical.obterBpmGlobal() != antesBpm) {
                builder.append("T").append(EstadoMusical.obterBpmGlobal()).append(" ");
                ultimoBpm = EstadoMusical.obterBpmGlobal();
            }
            if (estado.obterVolumeAtual() != antesVolume) {
                builder.append("X[Volume]=").append(estado.obterVolumeAtual()).append(" ");
                ultimoVolume = estado.obterVolumeAtual();
            }

            if (evento != null) {
                if (evento.ehPausa()) {
                    builder.append("R ");
                    estado.limparUltimaNota();
                } else {
                    String nota = evento.obterNota();
                    builder.append(nota)
                            .append(evento.obterOitava())
                            .append("q ");
                    estado.registrarUltimaNota(nota);
                }
            }
        }

        return builder.toString().trim();
    }
}