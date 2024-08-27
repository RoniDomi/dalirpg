package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    GamePanel gp;
    Graphics2D g2D;
    Font kitchenSink;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue;

    public UI (GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/Kitchen Sink.ttf");
            kitchenSink = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2D) {
        this.g2D = g2D;

        g2D.setFont(kitchenSink);
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

        for (String line: currentDialogue.split("\n")) {
            g2D.drawString(line, x, y);
            y += 40;
        }
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
