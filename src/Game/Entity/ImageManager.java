package Game.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class ImageManager { //clasa separata prin care gestionez citirea si  desenarea imaginilor

    public static BufferedImage LoadImage(String path) {
        try {
            return ImageIO.read((ImageManager.class.getResourceAsStream(path)));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static  void DrawPosition(BufferedImage image,int x, int y, int w, int h, Graphics2D g) {
        g.drawImage(image, (int) x, (int)y, (int)w, (int)h,null);
    }

}