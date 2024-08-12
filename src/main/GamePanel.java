package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen parameters
    final int originalTileSize = 16; //16x16px
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public GamePanel() {
        // Set screen size
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        // Lock FPS to 60
        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta > 1) {
                update();
                repaint();
                delta--;

                drawCount++;
            }

            if (timer > 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount= 0;
                timer= 0;
            }
        }
    }

    public void update() {
        if (keyH.upPressed) {
            playerY -= playerSpeed;
        } if (keyH.downPressed) {
            playerY += playerSpeed;
        } if (keyH.leftPressed) {
            playerX -= playerSpeed;
        } if (keyH.rightPressed) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(Color.white);
        g2D.fillRect(playerX, playerY, tileSize, tileSize);
        g2D.dispose();
    }
}
