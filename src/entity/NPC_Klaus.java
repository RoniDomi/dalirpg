package entity;

import main.GamePanel;

public class NPC_Klaus extends Entity{
    public NPC_Klaus(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 2;

        getImage();
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
}
