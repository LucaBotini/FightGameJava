package fightgame.models;

import fightgame.models.players.Player1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Game extends JPanel implements ActionListener {
    private Player1 player1;
    private Image bottom;


    public Game() {
        setFocusable(true);
        setDoubleBuffered(true);
        this.requestFocusInWindow();
        String path = "src/images/backgrounds/background.png";
        ImageIcon reference = new ImageIcon(path); //recebe a imagem
        bottom = reference.getImage(); // variavel de referencia para imagem
        player1 = new Player1(this);
        player1.load();

        addKeyListener(new keyboardAdapter());
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(bottom, 0, 0, null);//colocando a imagem de fato na janela
        graphics.drawImage(player1.getImage(), player1.getX(), player1.getY(), this);
        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class keyboardAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player1.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player1.keyRelease(e);
        }
    }
}
