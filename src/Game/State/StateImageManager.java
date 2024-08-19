package Game.State;

import Game.Entity.ImageManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StateImageManager {
    public static BufferedImage LoadImage(String path) {
        try {
            return ImageIO.read((StateImageManager.class.getResourceAsStream(path)));
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
