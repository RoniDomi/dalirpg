package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    GamePanel gp;
    Graphics2D g2D;
    Font kitchenSink;
    public String currentDialogue;
    public int commandNum = 0;

    public UI (GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/Kitchen Sink.ttf");
            kitchenSink = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void draw (Graphics2D g2D) {
        this.g2D = g2D;

        g2D.setFont(kitchenSink);
        g2D.setColor(Color.white);
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
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
        // Opaque bg
        g2D.setColor(new Color(0, 0, 0, 100));
        g2D.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Text
        g2D.setColor(new Color(255, 253, 232));
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 56F));

        String text = "Paused";
        int x = getCenterX(text);
        int y = gp.screenHeight / 2;

        g2D.drawString(text, x, y);
    }

    public void drawTitleScreen() {
        // Background
        g2D.setColor(new Color(30,40,176));
        g2D.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 56F));
        String title = "Dali's Adventure";

        int x, y;
        x = getCenterX(title);
        y = gp.tileSize * 3;

        // Shadow text
        g2D.setColor(new Color(34,3,34));
        g2D.drawString(title, x + 5, y + 5);

        // Main color
        g2D.setColor(new Color(255, 253, 232));
        g2D.drawString(title, x, y);

        // Player image
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2D.drawImage(gp.player.down2, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // Menu
        if (commandNum == 0) {
            g2D.setColor(new Color(255, 202, 87));
        }
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 26F));
        title = "New Game";
        x = getCenterX(title);
        y += gp.tileSize * 4;
        g2D.drawString(title, x, y);
        g2D.setColor(new Color(255, 253, 232));

        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 26F));
        if (commandNum == 1) {
            g2D.setColor(new Color(255, 202, 87));
        }
        title = "Load Game";
        x = getCenterX(title);
        y += gp.tileSize;
        g2D.drawString(title, x, y);
        g2D.setColor(new Color(255, 253, 232));

        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 26F));
        if (commandNum == 2) {
            g2D.setColor(new Color(255, 202, 87));
        }
        title = "Quit";
        x = getCenterX(title);
        y += gp.tileSize;
        g2D.drawString(title, x, y);
        g2D.setColor(new Color(255, 253, 232));
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
