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
        tile = new Tile[50];
        getTileImage();
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/maps/worldmap.txt");
    }

    public void getTileImage() {
        // Grass
        setup(0, "tile000", false);
        setup(1, "tile001", false);
        setup(2, "tile002", false);
        setup(3, "tile003", false);
        setup(4, "tile004", false);
        // Tree
        setup(5, "tile005", true);
        // Dirt
        setup(6, "tile006", false);
        setup(7, "tile007", false);
        // Wall
        setup(8, "tile008", true);
        // Plank
        setup(9, "tile009", false);
        // Water
        setup(10, "tile010", true);
        // More grass
        setup(11, "tile011", false);
        setup(12, "tile012", false);
        setup(13, "tile013", false);
        setup(14, "tile014", false);
        setup(15, "tile015", false);
        setup(16, "tile016", false);
        setup(17, "tile017", false);
        setup(18, "tile018", false);
        setup(19, "tile019", false);
        // Water edges
        setup(20, "tile020", true);
        setup(21, "tile021", true);
        setup(22, "tile022", true);
        setup(23, "tile023", true);
        setup(24, "tile024", true);
        setup(25, "tile025", true);
        setup(26, "tile026", true);
        setup(27, "tile027", true);
        setup(28, "tile028", true);
        setup(29, "tile029", true);
        setup(30, "tile030", true);
        setup(31, "tile031", true);
        // Grass turns
        setup(32, "tile032", false);
        setup(33, "tile033", false);
        setup(34, "tile034", false);
        setup(35, "tile035", false);
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
