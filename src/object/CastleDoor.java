package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class CastleDoor extends SuperObject{
    GamePanel gp;

    public CastleDoor(GamePanel gp) {
        name = "Castle Door";
        this.gp = gp;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door1.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }

}