import org.jfugue.player.Player;
import javax.swing.*;
import javax.swing.SwingUtilities;

class Main {

    public static void main(String[] args) {
        Player player = new Player();
        player.play("C5");
        System.out.println("feito!");
    

        SwingUtilities.invokeLater(JanelaPrincipal::new);
    }
}