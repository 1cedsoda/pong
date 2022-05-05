package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCreate {
    public static BufferedImage exit= readBufferedImage("Exit_Button_Lobby_Panel.png");
    public static BufferedImage exit2= readBufferedImage("Exit_Button_Lobby_Panel.png");
    public static BufferedImage readBufferedImage(String fileName) {
        File f = new File(fileName);
        try {
            return ImageIO.read(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
