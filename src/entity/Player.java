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
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int spriteChangeCount = 12;
    public final int sprintSpeed = 6;
    public final int defaultSpeed = 4;

    public int hasKey = 0;
    boolean hasCastleKey = false;
    boolean runningShoesEquipped = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
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
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 45;
        speed = 4;
    }

    public void getPlayerImage() {
        down1 = setup("tile000");
        down2 = setup("tile001");
        down3 = setup("tile002");
        down4 = setup("tile003");
        up1 = setup("tile004");
        up2 = setup("tile005");
        up3 = setup("tile006");
        up4 = setup("tile007");
        left1 = setup("tile008");
        left2 = setup("tile009");
        left3 = setup("tile010");
        left4 = setup("tile011");
        right1 = setup("tile012");
        right2 = setup("tile013");
        right3 = setup("tile014");
        right4 = setup("tile015");
    }

    public BufferedImage setup (String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/new/" + imageName + ".png"));
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
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
            String objName = gp.obj[i].name;

            switch (objName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    gp.playSFX(4);
                    gp.ui.showMessage("You got a Key!");
                    break;
                case "Castle Key":
                    hasCastleKey = true;
                    hasKey++;
                    gp.obj[i] = null;
                    gp.playSFX(4);
                    gp.ui.showMessage("You got a Castle Key!");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                        gp.playSFX(2);
                        gp.ui.showMessage("Door unlocked!");
                    } else {
                        gp.ui.showMessage("You don't have a key!");
                        gp.playSFX(1);
                    }
                    break;
                case "Castle Door":
                    if (hasCastleKey) {
                        gp.obj[2] = null;
                        gp.obj[3] = null;
                        hasCastleKey = false;
                        gp.playSFX(2);
                        gp.ui.showMessage("Doors unlocked!");
                        hasKey--;
                    } else {
                        gp.ui.showMessage("You don't have a key!");
                        gp.playSFX(1);
                    }
                    break;
                case "Boot":
                    runningShoesEquipped = true;
                    gp.obj[i] = null;
                    gp.playSFX(3);
                    gp.ui.showMessage("Acquired running boots! Press SHIFT to run!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSFX(5);
                    break;
            }
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

        g2D.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
