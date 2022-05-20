package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
   // Folgende Funktion kopieren und "exit" ändern um ein neues Image hinzuzufügen
    public static BufferedImage exit= readBufferedImage(getPath("f.png"));
    public static BufferedImage gameBackground= readBufferedImage(getPath("Game_Background.png"));
    public static BufferedImage readBufferedImage(String fileName) {
        File f = new File(fileName);
        try {
            return ImageIO.read(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }














    public static ImageIcon scaleImages(int w, int h, ImageIcon image){
        int x = w;
        int y = h;
        BufferedImage resizing = new BufferedImage(w, h ,BufferedImage.TYPE_INT_RGB);
        Graphics2D gr2d = resizing.createGraphics();
        gr2d.drawImage(image.getImage(), 0, 0, x, y,null);
        gr2d.dispose();
        return new ImageIcon(resizing);
    }

}
