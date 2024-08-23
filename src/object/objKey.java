package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class objKey extends SuperObject{
    GamePanel gp;

    public objKey(GamePanel gp) {
        name = "Key";
        this.gp = gp;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key1.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
