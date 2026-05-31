import java.util.ArrayList;
import java.util.List;

//produz lista de eventos de uma linha (voz)
public class Interpretador {
    private final DicionarioDeRegras dicionario;

    public Interpretador(DicionarioDeRegras dicionario){
        this.dicionario = dicionario;

    }

    public List<EventoMusical> interpretar(String linha, EstadoMusical estado){
        List<EventoMusical> eventos = new ArrayList<>();
        for (char caractere : linha.toCharArray()){                 //pra cada caractere, pergunta qual a ação,
            AcaoMusical acao = dicionario.obterAcao(caractere);     // executa a ação, e e verifica se cria um evento
            EventoMusical resultado = acao.executar(estado);
            if (resultado != null){
                eventos.add(resultado);
            }
            if (caractere >= 'A' && caractere <= 'H') {
                estado.registrarUltimaNota(resultado.obterNota());
            } else {
                estado.limparUltimaNota();
            }
        }
        return eventos;
    }
}
