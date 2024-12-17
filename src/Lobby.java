import fightgame.models.Game;

import javax.swing.*;
import java.awt.*;

public class Lobby extends JFrame {
    public Lobby() {
        this.add(new Game());
        this.setTitle("Marvel VS DC Pixel");
        ImageIcon icone = new ImageIcon("src/images/logo.png");
        this.setIconImage(icone.getImage());
        this.setSize(1024, 728);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo((Component) null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Lobby();
    }
}


