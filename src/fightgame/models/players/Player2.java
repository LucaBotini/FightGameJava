package fightgame.models.players;

import fightgame.models.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class Player2 implements ActionListener {
    private Game game;
    private Image image;
    private Timer runPLayer;
    private int x, y, dx, dy, height, width;
    private double life;
    private boolean isVisible, inMovement = false, inAttack2 = false;


    public Player2(Game game) {
        this.game = game;
        this.x = 700;
        this.y = 350;
        this.life = 200;
        isVisible = true;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void load() {
        //definir imagem do player
        ImageIcon reference = new ImageIcon("src/images/players/player2/left/player2L.png");
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
            case KeyEvent.VK_L:
                if (game.isInGame()) {
                    if (!inAttack2) {
                        ImageIcon reference2 = new ImageIcon("src/images/players/player2/left/player2attack.png");
                        image = reference2.getImage();
                        inAttack2 = true;
                    }
                }
                break;
            case KeyEvent.VK_LEFT:
                if (game.isInGame()) {
                    dx = -6;
                    if (!inMovement) {
                        // Lista de imagens para o loop
                        String[] images = {
                                "src/images/players/player2/left/player2runL.png",
                                "src/images/players/player2/left/player2run2L.png",
                                "src/images/players/player2/left/player2run3L.png",
                        };

                        // Índice inicial
                        AtomicInteger index = new AtomicInteger(0);

                        // Timer para alternar as imagens
                        runPLayer = new Timer(200, e -> {
                            // Atualizar o índice de forma cíclica
                            int currentIndex = index.getAndUpdate(i -> (i + 1) % images.length);

                            // Atualizar a imagem
                            ImageIcon reference = new ImageIcon(images[currentIndex]);
                            image = reference.getImage();
                        });

                        runPLayer.start();
                        inMovement = true;
                    }
                    break;
                }
            case KeyEvent.VK_RIGHT:
                if (game.isInGame()) {
                    dx = 6;
                    if (!inMovement) {
                        // Lista de imagens para o loop
                        String[] images = {
                                "src/images/players/player2/right/player2run.png",
                                "src/images/players/player2/right/player2run2.png",
                                "src/images/players/player2/right/player2run3.png",
                        };

                        // Índice inicial
                        AtomicInteger index = new AtomicInteger(0);

                        // Timer para alternar as imagens
                        runPLayer = new Timer(200, e -> {
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
    }

    public void keyRelease(KeyEvent key) {
        int code = key.getKeyCode();

        switch (code) {
            case KeyEvent.VK_L:
                if (game.isInGame()) {
                    inAttack2 = false;
                    ImageIcon reference2 = new ImageIcon("src/images/players/player2/left/player2L.png");
                    image = reference2.getImage();
                    break;
                }
            case KeyEvent.VK_LEFT:
                if (game.isInGame()) {
                    dx = 0;
                    if (runPLayer != null && runPLayer.isRunning()) {
                        runPLayer.stop();
                        inMovement = false;
                    }
                    ImageIcon reference3 = new ImageIcon("src/images/players/player2/left/player2L.png");
                    image = reference3.getImage();
                    break;
                }
            case KeyEvent.VK_RIGHT:
                if (game.isInGame()) {
                    dx = 0;
                    if (runPLayer != null && runPLayer.isRunning()) {
                        runPLayer.stop();
                        inMovement = false;
                    }
                    ImageIcon reference = new ImageIcon("src/images/players/player2/left/player2L.png");
                    image = reference.getImage();
                    break;
                }
        }
    }

    public Game getGame() {
        return game;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }

    public boolean isinAttack2() {
        return inAttack2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
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
