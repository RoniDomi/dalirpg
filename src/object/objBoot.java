package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class objBoot extends SuperObject{
    GamePanel gp;

    public objBoot(GamePanel gp) {
        name = "Boot";
        this.gp = gp;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boot1.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
