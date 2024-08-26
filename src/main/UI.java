package main;

import java.awt.*;

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
    public String currentDialogue;

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

        if (gp.gameState == gp.dialogue) {
            drawDialogueScreen();
        }
    }

    public void drawPauseScreen () {
        String text = "Paused";

        int x = getCenterX(text);
        int y = gp.screenHeight / 2;

        g2D.drawString(text, x, y);
    }

    public void drawDialogueScreen () {
        //  Dialogue Window
        int x, y, width, height;

        x = gp.tileSize * 2;
        y = gp.tileSize / 2;
        width = gp.screenWidth - gp.tileSize * 4;
        height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        g2D.drawString(currentDialogue, x, y);
    }

    public void drawSubWindow (int x, int y, int width, int height) {
        Color col = new Color(0, 0, 0);
        g2D.setColor(col);
        g2D.fillRoundRect(x, y, width, height, 10, 10);

        col = new Color(255, 255, 255);
        g2D.setColor(col);
        g2D.setStroke(new BasicStroke(5));
        g2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 5, 5);
    }

    public int getCenterX (String text) {
        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }
}
