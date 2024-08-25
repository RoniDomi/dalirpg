package main;

import entity.NPC_Klaus;
import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObj() {

    }

    public void setNPC() {
        gp.npc[0] = new NPC_Klaus(gp);
        gp.npc[0].worldX = gp.tileSize * 25;
        gp.npc[0].worldY = gp.tileSize * 25;
    }
}
