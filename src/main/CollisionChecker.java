package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityWorldLeftX = entity.worldX + entity.solidArea.x;
        int entityWorldRightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityWorldUpY = entity.worldY + entity.solidArea.y;
        int entityWorldDownY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityWorldLeftX / gp.tileSize;
        int entityRightCol = entityWorldRightX / gp.tileSize;
        int entityTopRow = entityWorldUpY / gp.tileSize;
        int entityBottomRw = entityWorldDownY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up", "diagonal up left", "diagonal up right":
                entityTopRow = (entityWorldUpY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down", "diagonal down left", "diagonal down right":
                entityBottomRw = (entityWorldDownY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRw];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRw];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityWorldLeftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRw];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityWorldRightX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRw];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}