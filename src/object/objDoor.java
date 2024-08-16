package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class objDoor extends SuperObject{
    public objDoor() {
        name = "Door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }

}
