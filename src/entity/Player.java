package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.objChest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int spriteChangeCount = 12;
    public final int sprintSpeed = 6;
    public final int defaultSpeed = 4;
    boolean runningShoesEquipped = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();
        direction = "down";
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 30;
        worldY = gp.tileSize * 45;
        speed = 4;
    }

    public void getPlayerImage() {
        down1 = setup("/player/new/tile000");
        down2 = setup("/player/new/tile001");
        down3 = setup("/player/new/tile002");
        down4 = setup("/player/new/tile003");
        up1 = setup("/player/new/tile004");
        up2 = setup("/player/new/tile005");
        up3 = setup("/player/new/tile006");
        up4 = setup("/player/new/tile007");
        left1 = setup("/player/new/tile008");
        left2 = setup("/player/new/tile009");
        left3 = setup("/player/new/tile010");
        left4 = setup("/player/new/tile011");
        right1 = setup("/player/new/tile012");
        right2 = setup("/player/new/tile013");
        right3 = setup("/player/new/tile014");
        right4 = setup("/player/new/tile015");
    }



    public void update() {
        if (keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed) {

            speed = keyH.lShiftPressed && runningShoesEquipped ? sprintSpeed : defaultSpeed;
            spriteChangeCount = speed > 4 ? 6 : 12;

            if (keyH.upPressed && keyH.rightPressed) {
                direction = "diagonal up right";
            } else if (keyH.upPressed && keyH.leftPressed) {
                direction = "diagonal up left";
            } else if (keyH.downPressed && keyH.leftPressed) {
                direction = "diagonal down left";
            } else if (keyH.downPressed && keyH.rightPressed) {
                direction = "diagonal down right";
            } else if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObj(objIndex);

            // If collision is false, player can move!
            if (!collisionOn) {
                switch (direction) {
                    case "up":    worldY -= speed; break;
                    case "down":  worldY += speed; break;
                    case "left":  worldX -= speed; break;
                    case "right": worldX += speed; break;
                    case "diagonal up left":
                        worldY -= speed;
                        worldX -= speed;
                        break;
                    case "diagonal up right":
                        worldY -= speed;
                        worldX += speed;
                        break;
                    case "diagonal down left":
                        worldY += speed;
                        worldX -= speed;
                        break;
                    case "diagonal down right":
                        worldY += speed;
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;

            // Change sprite every x frames
            if (spriteCounter > spriteChangeCount) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 1;
                }

                spriteCounter = 0;
                }
        }
    }

    public void pickUpObj (int i) {
        if (i != 999) {

        }
    }

    public void draw(Graphics2D g2D) {
        BufferedImage image = null;

        switch (direction) {
            case "up", "diagonal up left", "diagonal up right":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
                if (spriteNum == 4) {
                    image = up4;
                }
                break;
            case "down", "diagonal down left", "diagonal down right":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
                if (spriteNum == 4) {
                    image = down4;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                if (spriteNum == 4) {
                    image = left4;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                if (spriteNum == 4) {
                    image = right4;
                }
                break;
        }

        int x = screenX;
        int y = screenY;

        if (screenX > worldX) {
            x = worldX;
        }

        if (screenY > worldY) {
            y = worldY;
        }

        // Right edge
        int rightOffset = gp.screenWidth - screenX;
        if (rightOffset > gp.worldWidth - worldX) {
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }

        // Bottom
        int bottomOffset = gp.screenHeight - screenY;
        if (bottomOffset > gp.worldHeight - worldY) {
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }


        g2D.drawImage(image, x, y, null);
    }
}
