import java.util.List;

// Converte as vozes numa string Staccato do JFugue.
// Classe PURA (sem JFugue) -> fácil de testar.
public class Gerador {

    public String gerar(List<Voz> vozes) {
        StringBuilder sb = new StringBuilder();
        sb.append("T").append(EstadoMusical.obterBpmGlobal()).append(' ');

        for (Voz voz : vozes) {
            sb.append('V').append(voz.obterNumero()).append(' ');

            // atraso de entrada: n pausas de semínima
            for (int i = 0; i < voz.obterAtraso(); i++) sb.append("Rq ");

            int instrumentoEmitido = -1;
            for (EventoMusical ev : voz.obterEventos()) {
                if (ev.ehPausa()) {
                    sb.append("Rq ");
                } else {
                    if (ev.obterInstrumento() != instrumentoEmitido) {
                        sb.append('I').append(ev.obterInstrumento()).append(' ');
                        instrumentoEmitido = ev.obterInstrumento();
                    }
                    sb.append(ev.obterNota())
                            .append(ev.obterOitava())
                            .append("q")            // duração: semínima
                            .append('a').append(ev.obterVolume())
                            .append(' ');
                }
            }
        }
        return sb.toString().trim();
    }
}