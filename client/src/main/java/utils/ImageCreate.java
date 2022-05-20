package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCreate {


    public static String getOperatingSystem() {
        String os = System.getProperty("os.name");
        // System.out.println("Using System Property: " + os);
        return os;
    }
    public static String getPath (String fileName) {
        String filePath;
        String os = getOperatingSystem();
        if(os.contains("Windows")) {
            filePath = new File("").getAbsolutePath() + "\\..\\client\\src\\main\\resources\\"+fileName;
            System.out.println(filePath);
            return filePath;
        } else {
            filePath = new File("").getAbsolutePath() + "/client/src/main/resources/"+fileName;
            System.out.println(filePath);
            return filePath;
        }
    }
   /* public static String gettingPath (String fileName) {
        String filePath;
        switch("os") {
            case "Windows 10":
            case "Windows 11":
                filePath = new File("").getAbsolutePath() + "\\client\\src\\main\\resources\\"+fileName;
                System.out.println(filePath);
                return filePath;
            default:
                filePath = new File("").getAbsolutePath() + "/client/src/main/resources/"+fileName;
                System.out.println(filePath);
                return filePath;
        }
    }

    */
   // Folgende Funktion kopieren und "exit" ändern um ein neues Image hinzuzufügen
    //public static BufferedImage exit= readBufferedImage("/Users/kevinstamm/IdeaProjects/pong/client/src/main/resources/f.png");
    public static BufferedImage exit= readBufferedImage(getPath("f.png"));
    public static BufferedImage readBufferedImage(String fileName) {
        File f = new File(fileName);
        try {
            return ImageIO.read(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
