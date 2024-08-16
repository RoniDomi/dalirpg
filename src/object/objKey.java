package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class objKey extends SuperObject{
    public objKey() {
        name = "Key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
