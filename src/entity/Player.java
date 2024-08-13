package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        if (keyH.upPressed) {
            y -= speed;
        } if (keyH.downPressed) {
            y += speed;
        } if (keyH.leftPressed) {
            x -= speed;
        } if (keyH.rightPressed) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2D) {
        g2D.setColor(Color.white);
        g2D.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
