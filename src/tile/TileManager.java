package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public String texturePack = "new/";

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[20];
        getTileImage();
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
        // Grass
        setup(0, "tile000", false);
        // Left dirt edge
        setup(1, "tile001", false);
        // Right dirt edge
        setup(2, "tile002", false);
        // Up dirt edge
        setup(3, "tile003", false);
        // Down dirt edge
        setup(4, "tile004", false);
        // Tree
        setup(5, "tile005", true);
        // Dirt block 1
        setup(6, "tile006", false);
        // Dirt block 2
        setup(7, "tile007", false);
        // Wall
        setup(8, "tile008", true);
        // Plank
        setup(9, "tile009", false);
        // Water
        setup(10, "tile010", true);
    }

    public void setup(int index, String imgPath, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + texturePack + imgPath + ".png"));
            tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapFilePath) {
        try {
            InputStream is = getClass().getResourceAsStream(mapFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

            br.close();

        } catch (Exception e) {}
    }

    public void draw(Graphics2D g2D) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldCol) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2D.drawImage(tile[tileNum].image, screenX, screenY, null );
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
