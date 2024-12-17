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
    private double life;
    private boolean isVisible, inMovement = false, inAttack = false;


    public Player1(Game game) {
        this.game = game;
        this.x = 100;
        this.y = 350;
        this.life = 200;
        isVisible = true;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void load() {
        //definir imagem do player
        ImageIcon reference = new ImageIcon("src/images/players/player1/right/player1.png");
        image = reference.getImage();

        height = image.getHeight(null);
        width = image.getWidth(null);
    }

    public void update() {
        int calculate;
        calculate = x + dx;
        if(calculate >= -26 && calculate <= 838 ){
            x += dx;
            y += dy;
        }
        //calculos de movimentação
    }

    public void keyPressed(KeyEvent key) {

        int code = key.getKeyCode();

        switch (code) {
            case KeyEvent.VK_G:
                if (game.isInGame()) {
                    if (!inAttack) {
                        ImageIcon reference2 = new ImageIcon("src/images/players/player1/right/player1attack.png");
                        image = reference2.getImage();
                        inAttack = true;
                    }
                    break;
                }
            case KeyEvent.VK_A:
                if (game.isInGame()) {
                    dx = -6;
                    if (!inMovement) {
                        // Lista de imagens para o loop
                        String[] images = {
                                "src/images/players/player1/left/player1runL.png",
                                "src/images/players/player1/left/player1run3L.png",
                                "src/images/players/player1/left/player1run2L.png",
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
            case KeyEvent.VK_D:
                if (game.isInGame()) {
                    dx = 6;
                    if (!inMovement) {
                        // Lista de imagens para o loop
                        String[] images = {
                                "src/images/players/player1/right/player1run.png",
                                "src/images/players/player1/right/player1run2.png",
                                "src/images/players/player1/right/player1run3.png",
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
            case KeyEvent.VK_G:
                if (game.isInGame()) {
                    inAttack = false;
                    ImageIcon reference2 = new ImageIcon("src/images/players/player1/right/player1.png");
                    image = reference2.getImage();
                    break;
                }
            case KeyEvent.VK_A:
                if (game.isInGame()) {
                    dx = 0;
                    if (runPLayer != null && runPLayer.isRunning()) {
                        runPLayer.stop();
                        inMovement = false;
                    }
                    ImageIcon reference3 = new ImageIcon("src/images/players/player1/right/player1.png");
                    image = reference3.getImage();
                    break;
                }
            case KeyEvent.VK_D:
                if (game.isInGame()) {
                    dx = 0;
                    if (runPLayer != null && runPLayer.isRunning()) {
                        runPLayer.stop();
                        inMovement = false;
                    }
                    ImageIcon reference = new ImageIcon("src/images/players/player1/right/player1.png");
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

    public void setLife(double life) {
        this.life = life;
    }

    public double getLife() {
        return life;
    }

    public boolean isInAttack() {
        return inAttack;
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
