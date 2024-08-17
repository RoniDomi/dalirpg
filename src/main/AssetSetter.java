package main;

import object.objBoot;
import object.objChest;
import object.objDoor;
import object.objKey;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObj() {
        gp.obj[0] = new objKey();
        gp.obj[0].name = "Castle Key";
        gp.obj[0].worldX = 34 * gp.tileSize;
        gp.obj[0].worldY = 30 * gp.tileSize;

        gp.obj[1] = new objKey();
        gp.obj[1].worldX = 29 * gp.tileSize;
        gp.obj[1].worldY = 13 * gp.tileSize;

        gp.obj[2] = new objDoor();
        gp.obj[2].name = "Castle Door";
        gp.obj[2].worldX = 14 * gp.tileSize;
        gp.obj[2].worldY = 20 * gp.tileSize;

        gp.obj[3] = new objDoor();
        gp.obj[3].name = "Castle Door";
        gp.obj[3].worldX = 15 * gp.tileSize;
        gp.obj[3].worldY = 20 * gp.tileSize;

        gp.obj[4] = new objDoor();
        gp.obj[4].worldX = 37 * gp.tileSize;
        gp.obj[4].worldY = 34 * gp.tileSize;

        gp.obj[5] = new objChest();
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 12 * gp.tileSize;

        gp.obj[6] = new objChest();
        gp.obj[6].worldX = 36 * gp.tileSize;
        gp.obj[6].worldY = 30 * gp.tileSize;

        gp.obj[7] = new objBoot();
        gp.obj[7].worldX = 17 * gp.tileSize;
        gp.obj[7].worldY = 30 * gp.tileSize;
    }
}
