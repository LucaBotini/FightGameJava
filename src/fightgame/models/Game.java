package fightgame.models;

import fightgame.models.players.Player1;
import fightgame.models.players.Player2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Game extends JPanel implements ActionListener {
    private Player1 player1;
    private Player2 player2;
    private Image bottom, imageLifePlayer1, imageLifePlayer2;
    private Timer timer, killed;
    private double lifePlayer1, lifePlayer2;
    private boolean inGame, attack;


    public Game() {
        setFocusable(true);
        setDoubleBuffered(true);
        this.requestFocusInWindow();
        String path = "src/images/backgrounds/background.png";
        ImageIcon reference = new ImageIcon(path); //recebe a imagem
        bottom = reference.getImage(); // variavel de referencia para imagem
        inGame = true;
        player1 = new Player1(this);
        player1.load();
        player2 = new Player2(this);
        String imageLife2 = "src/images/backgrounds/lifeplayer2.png";
        ImageIcon referenceLife2 = new ImageIcon(imageLife2); //recebe a imagem
        imageLifePlayer2 = referenceLife2.getImage(); // variavel de referencia para imagem
        player2.load();

        addKeyListener(new keyboardAdapter());

        timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(bottom, 0, 0, null);//colocando a imagem de fato na janela
        graphics.drawImage(player1.getImage(), player1.getX(), player1.getY(), this);
        graphics.drawImage(player2.getImage(), player2.getX(), player2.getY(), this);
        graphics.setFont(new Font("Arial", Font.BOLD, 20)); // Configura a fonte
        graphics.setColor(Color.RED); // Configura a cor do texto

        graphics.drawString(String.valueOf(lifePlayer1), 100, 80);

        graphics.drawImage(imageLifePlayer2, 700, 30, this);//colocando a imagem de vida2
        graphics.drawString(String.valueOf(lifePlayer2), 920, 80);

        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        player1.update();
        player2.update();
        checkCollisions();
        repaint();
    }

    public void checkCollisions() {
        Rectangle shapePlayer1 = player1.getBounds();
        Rectangle shapePlayer2 = player2.getBounds();
        ImageIcon reference;
        lifePlayer1 = player1.getLife();
        lifePlayer2 = player2.getLife();
        if (!player1.isInAttack()) {
            attack = false;
        }
        if (shapePlayer1.intersects(shapePlayer2)) {
            if (lifePlayer2 > 0 && player1.isInAttack() && !attack) {
                reference = new ImageIcon("src/images/players/player2/left/player2red.png");
                player2.setImage(reference.getImage());
                player2.setLife(lifePlayer2 - 20);
                attack = true;
                Timer timer = new Timer(150, e -> {
                    ImageIcon normalImage = new ImageIcon("src/images/players/player2/left/player2L.png");
                    player2.setImage(normalImage.getImage());
                });
                timer.setRepeats(false); // Garante que o timer execute apenas uma vez
                timer.start(); // Inicia o timer
            } else if (lifePlayer2 <= 0 && inGame) {
                String[] images = {
                        "src/images/players/player2/left/player2killed.png",
                        "src/images/players/player2/left/player2killed2.png",
                        "src/images/players/player2/left/player2killed3.png",
                };

                // Índice inicial
                AtomicInteger index = new AtomicInteger(0);

                Timer killedTimer = new Timer(500, e -> {
                    int currentIndex = index.getAndIncrement();

                    // Verifica se já exibimos todas as imagens
                    if (currentIndex >= images.length) {
                        ((Timer) e.getSource()).stop(); // Para o timer após completar a sequência
                        return;
                    }

                    // Atualiza a imagem do player2
                    ImageIcon killedImage = new ImageIcon(images[currentIndex]);
                    player2.setImage(killedImage.getImage());
                });

                killedTimer.start(); // Inicia a sequência de animação
                inGame = false;
            }
        }
    }

    private class keyboardAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player1.keyPressed(e);
            player2.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player1.keyRelease(e);
            player2.keyRelease(e);
        }
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
}
