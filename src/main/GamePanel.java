package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen parameters
    final int originalTileSize = 16; //16x16px
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // World Map Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    // FPS
    int FPS = 60;

    // System
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound sfx = new Sound();
    public UI ui = new UI(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;
    public boolean musicStopped = false;


    // Entities and Objects
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    // Game State
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogue = 3;

    public GamePanel() {
        // Set screen size
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame() {
        aSetter.setObj();
        aSetter.setNPC();
        gameState = playState;

        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
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

//            Fps clock
//            if (timer > 1000000000) {
//                System.out.println("FPS: " + drawCount);
//                drawCount= 0;
//                timer= 0;
//            }
        }
    }

    public void update() {
        // Player
        if (gameState == playState) {
            player.update();
        }

        // Npc
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].update();
            }
        }

        if (gameState == pauseState) {

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        // DEBUG
        long drawStart = 0;
        drawStart = System.nanoTime();

        // Tiles
        tileM.draw(g2D);

        // Objects
        for (int i = 0; i < obj.length; i ++) {
            if (obj[i] != null) {
                obj[i].draw(g2D, this);
            }
        }

        // NPC
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].draw(g2D);
            }
        }

        // Player
        player.draw(g2D);

        // UI
        ui.draw(g2D);
        if (keyH.checkDrawTime) {
            long endTime = System.nanoTime();
            long time = endTime - drawStart;

            g2D.setColor(Color.white);
            g2D.drawString("Draw time: " + time, 10, 400);
            System.out.println(time);
        }

        g2D.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSFX(int i) {
        sfx.setFile(i);
        sfx.play();
    }
}
