package tile;

import main.GamePanel;

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
    public int texturePack = 1;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[20];
        getTileImage();
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
        try {
            switch (texturePack) {
                case 1:
                    // Grass
                    tile[0] = new Tile();
                    tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/new/tile000.png"));

                    // Left dirt edge
                    tile[1] = new Tile();
                    tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/new/tile001.png"));

                    // Right dirt edge
                    tile[2] = new Tile();
                    tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/new/tile002.png"));

                    // Up dirt edge
                    tile[3] = new Tile();
                    tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/new/tile003.png"));

                    // Down dirt edge
                    tile[4] = new Tile();
                    tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/new/tile004.png"));

                    // Tree
                    tile[5] = new Tile();
                    tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/new/tile005.png"));
                    tile[5].collision = true;

                    // Dirt block 1
                    tile[6] = new Tile();
                    tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/new/tile006.png"));

                    // Dirt block 2
                    tile[7] = new Tile();
                    tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/new/tile007.png"));

                    // Wall
                    tile[8] = new Tile();
                    tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/new/tile008.png"));
                    tile[8].collision = true;

                    // Plank
                    tile[9] = new Tile();
                    tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/new/tile009.png"));

                    // Water
                    tile[10] = new Tile();
                    tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/new/tile010.png"));
                    tile[10].collision = true;

                    // Door
                    tile[11] = new Tile();
                    tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/new/tile011.png"));
                    tile[11].collision = true;
                    break;

                case 2:
                    // Grass
                    tile[0] = new Tile();
                    tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/retro/grass1.png"));

                    // Left dirt edge
                    tile[1] = new Tile();
                    tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/retro/dirtpathedge1.png"));

                    // Right dirt edge
                    tile[2] = new Tile();
                    tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/retro/dirtpathedge2.png"));

                    // Up dirt edge
                    tile[3] = new Tile();
                    tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/retro/dirtpathedge3.png"));

                    // Down dirt edge
                    tile[4] = new Tile();
                    tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/retro/dirtpathedge4.png"));

                    // Tree
                    tile[5] = new Tile();
                    tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/retro/tree1.png"));
                    tile[5].collision = true;

                    // Dirt block 1
                    tile[6] = new Tile();
                    tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/retro/dirt1.png"));

                    // Dirt block 2
                    tile[7] = new Tile();
                    tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/retro/dirt1.png"));

                    // Wall 1
                    tile[8] = new Tile();
                    tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/retro/wall1.png"));
                    tile[8].collision = true;

                    // Plank
                    tile[9] = new Tile();
                    tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/retro/dirt1.png"));

                    // Water
                    tile[10] = new Tile();
                    tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/retro/water1.png"));
                    tile[10].collision = true;

                    // Door
                    tile[11] = new Tile();
                    tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/retro/dirt1.png"));
                    break;
            }


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
                g2D.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null );
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
