package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import object.objKey;

public class UI {

    GamePanel gp;
    Graphics2D g2D;
    Font arial60;
    Font arial40;
    Font arial20;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI (GamePanel gp) {
        this.gp = gp;
        arial60 = new Font("Arial", Font.BOLD, 60);
        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial20 = new Font("Arial", Font.PLAIN, 20);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2D) {
        this.g2D = g2D;

        g2D.setFont(arial40);
        g2D.setColor(Color.white);

        if (gp.gameState == gp.playState) {
            // play state stuff
        }

        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {
        String text = "Paused";

        int x = getCenterX(text);
        int y = gp.screenHeight / 2;

        g2D.drawString(text, x, y);
    }

    public int getCenterX (String text) {
        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }
}
