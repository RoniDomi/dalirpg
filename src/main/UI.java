package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import object.objKey;

public class UI {

    GamePanel gp;
    Font arial60;
    Font arial40;
    Font arial20;
    BufferedImage keyImg;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI (GamePanel gp) {
        this.gp = gp;
        arial60 = new Font("Arial", Font.BOLD, 60);
        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial20 = new Font("Arial", Font.PLAIN, 20);
        objKey key = new objKey(gp);
        keyImg = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2D) {

        if (gameFinished) {
            messageOn = false;
            g2D.setFont(arial40);
            g2D.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();


            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);

            g2D.drawString(text, x, y);

            g2D.setFont(arial60);
            g2D.setColor(Color.yellow);

            text = "Congratulations!";
            textLength = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();


            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);

            g2D.drawString(text, x, y);

            gp.gameThread = null;

            text = "Demo Completed!";
            textLength = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();


            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2) + 50;

            g2D.drawString(text, x, y);

            gp.gameThread = null;
        }

        g2D.setFont(arial40);
        g2D.setColor(Color.white);

        g2D.drawImage(keyImg, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2D.drawString(" " + gp.player.hasKey, 74, 65);

        // Message
        if (messageOn) {
            g2D.setFont(arial20);
            g2D.drawString(message, gp.tileSize/2, gp.tileSize * 5);

            messageCounter++;

            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
