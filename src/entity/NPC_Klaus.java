package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Klaus extends Entity{
    public NPC_Klaus(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        spriteChangeSpeed = 16;
        getImage();
        setDialogue();
    }

    public void getImage() {
        down1 = setup("/npc/klaus/tile000");
        down2 = setup("/npc/klaus/tile001");
        down3 = setup("/npc/klaus/tile002");
        down4 = setup("/npc/klaus/tile003");
        up1 = setup("/npc/klaus/tile004");
        up2 = setup("/npc/klaus/tile005");
        up3 = setup("/npc/klaus/tile006");
        up4 = setup("/npc/klaus/tile007");
        left1 = setup("/npc/klaus/tile008");
        left2 = setup("/npc/klaus/tile009");
        left3 = setup("/npc/klaus/tile010");
        left4 = setup("/npc/klaus/tile011");
        right1 = setup("/npc/klaus/tile012");
        right2 = setup("/npc/klaus/tile013");
        right3 = setup("/npc/klaus/tile014");
        right4 = setup("/npc/klaus/tile015");
    }

    public void setAction() {
        if (gp.gameState == gp.playState) {
            actionLockCounter ++;
            Random random = new Random();

            int i = random.nextInt(100) + 1;

            if (actionLockCounter == 120) {
                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75) {
                    direction = "right";
                }

                actionLockCounter = 0;
            }
        }
    }

    public void setDialogue () {
        dialogue[0] = "Dali! You're \nfinally here!";
        dialogue[1] = "The master is \nlooking for you.";
        dialogue[2] = "I think you're \nfinally going \non a mission.";
        dialogue[3] = "Go on then, \nwhat are you \nwaiting for?";
    }

    public void speak () {
        super.speak();
    }
}
