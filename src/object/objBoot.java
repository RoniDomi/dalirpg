package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class objBoot extends SuperObject{
    public objBoot() {
        name = "Boot";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boot1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
