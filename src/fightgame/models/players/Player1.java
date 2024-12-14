package fightgame.models.players;

import fightgame.models.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class Player1 implements ActionListener {
    private Game game;
    private Image image;
    private Timer runPLayer;
    private int x, y, dx, dy, height, width;
    private boolean isVisible, inMovement = false;


    public Player1(Game game) {
        this.game = game;
        this.x = 100;
        this.y = 350;
        isVisible = true;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void load(){
        //definir imagem do player
        ImageIcon reference = new ImageIcon("src/images/players/player1/player1.png");
        image = reference.getImage();

        height = image.getHeight(null);
        width = image.getWidth(null);
    }

    public void update() {
        x += dx;
        y += dy;
        //fazendo a movimentação
    }
    public void keyPressed(KeyEvent key) {

        int code = key.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                dy = -6;
                System.out.println("w");
                break;
            case KeyEvent.VK_S:
                dy = 6;
                System.out.println("s");
                break;
            case KeyEvent.VK_A:
                dx = -6;
                System.out.println("a");
                break;
            case KeyEvent.VK_D:
                dx = 6;
                if(!inMovement){
                    // Lista de imagens para o loop
                    String[] images = {
                            "src/images/players/player1/player1run1.png",
                            "src/images/players/player1/player1run2.png"
                    };

                    // Índice inicial
                    AtomicInteger index = new AtomicInteger(0);

                    // Timer para alternar as imagens
                    runPLayer = new Timer(300, e -> {
                        // Atualizar o índice de forma cíclica
                        int currentIndex = index.getAndUpdate(i -> (i + 1) % images.length);

                        // Atualizar a imagem
                        ImageIcon reference = new ImageIcon(images[currentIndex]);
                        image = reference.getImage();
                    });

                    runPLayer.start();
                    inMovement = true;
                }

        }
    }
    public void keyRelease(KeyEvent key) {
        int code = key.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                dy = 0;
                break;
            case KeyEvent.VK_S:
                dy = 0;
                break;
            case KeyEvent.VK_A:
                dx = 0;
                break;
            case KeyEvent.VK_D:
                dx = 0;
                if (runPLayer != null && runPLayer.isRunning()) {
                    runPLayer.stop();
                    inMovement = false;
                }
                ImageIcon reference = new ImageIcon("src/images/players/player1/player1.png");
                image = reference.getImage();
                break;
        }
    }

    public Game getGame() {
        return game;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
