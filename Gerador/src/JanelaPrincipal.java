        areaTexto.setWrapStyleWord(true);

        btnAbrir = new JButton("Abrir TXT");
        btnGerar = new JButton("Gerar Música");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAbrir);
@@ -41,18 +41,6 @@ private void gerarMusica() {
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        new Thread(() -> {
            try {
                GerarMusica.tocar(texto);
            } catch (Exception ex) {
                ex.printStackTrace();
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this,
                        "Erro ao gerar música: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE));
            }
        }).start();
    }
}