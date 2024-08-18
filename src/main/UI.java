package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import object.objKey;

public class UI {

    GamePanel gp;
    Font arial40;
    Font arial20;
    BufferedImage keyImg;
    public boolean messageOn = false;
    public String message = "";

    public UI (GamePanel gp) {
        this.gp = gp;
        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial20 = new Font("Arial", Font.PLAIN, 20);
        objKey key = new objKey();
        keyImg = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2D) {
        g2D.setFont(arial40);
        g2D.setColor(Color.white);

        g2D.drawImage(keyImg, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2D.drawString(" " + gp.player.hasKey, 74, 65);

        // Message
        if (messageOn) {
            g2D.setFont(arial20);
            g2D.drawString(message, gp.tileSize/2, gp.tileSize * 5);
        }
    }
}
