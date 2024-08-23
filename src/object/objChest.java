package object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class objChest extends SuperObject{
    public BufferedImage openedChest;

    public objChest() {
        name = "Chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest1.png"));
            openedChest = ImageIO.read(getClass().getResourceAsStream("/objects/chest2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
