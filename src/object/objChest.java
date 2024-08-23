package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class objChest extends SuperObject{
    GamePanel gp;

    public objChest(GamePanel gp) {
        name = "Chest";
        this.gp = gp;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest1.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
