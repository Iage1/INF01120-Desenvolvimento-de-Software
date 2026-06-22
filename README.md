# Gerador de Música a partir de Texto

Programa que lê um texto e o reproduz como música. Cada caractere é interpretado
como uma ação musical (tocar nota, pausa, trocar instrumento, alterar oitava,
volume ou andamento) seguindo um mapeamento definido. Cada linha do texto
corresponde a uma voz independente, permitindo polifonia.

Implementado em Java, com a biblioteca JFugue para a saída sonora e MIDI.

## Como executar

É necessário o JAR do JFugue no classpath. Compilar o projeto e executar a classe
Main.

## Organização do código

O texto é convertido em uma lista de eventos e somente depois essa lista vira som. 
O fluxo é o seguinte:

texto → CompositorDeVozes → Interpretador → lista de EventoMusical → Gerador → SaidaMusicalJFugue

As classes:

Dados
- `EstadoMusical`: oitava, volume, instrumento,
  BPM e última nota tocada.
- `EventoMusical`: uma nota ou pausa já resolvida e imutável. 

Regras
- `AcaoMusical`: interface comum a todas as regras. Cada regra do mapeamento é
  uma classe que a implementa: `AcaoTocarNota`, `AcaoPausar`, `AcaoDobrarVolume`,
  `AcaoAumentarOitava`, `AcaoDiminuirOitava`, `AcaoTrocarInstrumento`,
  `AcaoSomarInstrumento`, `AcaoAlterarBPM` e `AcaoRepetirOuPausar`.
- `DicionarioDeRegras`: associa cada caractere à sua ação. Concentra todo o
  mapeamento em um único lugar.

Motor
- `Interpretador`: processa uma linha caractere por caractere e produz a lista
  de eventos. Não tem conhecimento de vozes.

Vozes
- `Voz`: agrupa o número da voz, o atraso de entrada e seus eventos.
- `CompositorDeVozes`: divide o texto em linhas e cria uma voz por linha, cada
  uma com seu estado base de oitava, volume e instrumento.

Saída
- `Gerador`: converte as vozes na notação Staccato do JFugue.
- `SaidaMusicalJFugue`: responsável por reproduzir e salvar o MIDI.

Interface
- `JanelaPrincipal`, `AbrirArquivo` e `Main`: interface gráfica e montagem do
  sistema.
