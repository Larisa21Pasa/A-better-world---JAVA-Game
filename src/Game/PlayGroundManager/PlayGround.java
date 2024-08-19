package Game.PlayGroundManager;

import DataBase.DataBase;
import Util.Constants;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;


public class PlayGround extends PlayGroundManager {    //clasa in care implemnetez fundalul
    public PlayGround(DataBase dataBase) {
       x = 0;
       y = 0;
       height = (int) Constants.SCREEN_HEIGHT;
       width = (int) Constants.SCREEN_WIDTH;
       getPlayGroundDraw(dataBase);
    }

    public void draw(Graphics2D g) {
        //desenez imagini pe lungimea intregii world:
        g.drawImage(background, this.x-500, this.y, this.width, this.height, null);  //aplicare fundal
        g.drawImage(background, (int) (this.x-500+Constants.SCREEN_WIDTH), this.y, this.width, this.height, null);
        g.drawImage(background, (int) (this.x-500+Constants.SCREEN_WIDTH*2), this.y, this.width, this.height, null);
        g.drawImage(background, (int) (this.x-500+Constants.SCREEN_WIDTH*3), this.y, this.width, this.height, null);
        g.drawImage(background, (int) (this.x-500+Constants.SCREEN_WIDTH*4), this.y, this.width, this.height, null);
    }
    public void setPlayGroundDraw( DataBase dataBase) {
        String s=dataBase.getBackGround(Constants.LEVEL);
        try {
            background =  ImageIO.read((getClass().getResourceAsStream(s)));
        }catch (IOException e) {    e.printStackTrace();    }
    }
    public void getPlayGroundDraw(DataBase dataBase) {
        setPlayGroundDraw(dataBase);
    }
}
