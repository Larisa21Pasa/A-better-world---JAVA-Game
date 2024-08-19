package Game.TileManager;

import Util.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager extends TileUtil {
    public  Tile tile[];
    public  TileManager() {
        if(Constants.LEVEL == 1) {
            tile=new Tile[10];

        }
        else if(Constants.LEVEL == 2) {
            tile=new Tile[18];
        }
        getTileImage();

    }
    public  void getTileImage() {
         //pentru fiecare element al vectorului de dale: il creez, aplicand coodronatele dorite si imaginea
        if(Constants.LEVEL == 1) {
            //pads
            setImageTile(0, true, false, false, 500, 400, 200, 50, "/Tiles/Pad_02_1 - Copie.png");
            setImageTile(1, true, false, false, 2000, 400, 200, 50, "/Tiles/Pad_02_1 - Copie.png");
            setImageTile(2, true, false, false, 3500, 500, 200, 50, "/Tiles/Pad_02_1 - Copie.png");

            //coin
            setImageTile(3, true, true, false, (int) (tile[0].x + 70), (int) (tile[0].y - 200), 50, 50, "/Coins/Coin_03 - Copie.png");
            setImageTile(4, true, true, false, (int) (tile[1].x + 70), (int) (tile[1].y - 200), 50, 50, "/Coins/Coin_03 - Copie.png");
            setImageTile(5, true, true, false, (int) (tile[2].x + 70), (int) (tile[2].y - 200), 50, 50, "/Coins/Coin_03 - Copie.png");
            setImageTile(6, true, true, false, (int) (tile[2].x + 400), (int) (tile[0].y - 200), 50, 50, "/Coins/Coin_03 - Copie.png");
            setImageTile(7, true, true, false, (int) (tile[2].x + 800), (int) (tile[1].y - 30), 50, 50, "/Coins/Coin_03 - Copie.png");

            //fc
            setImageTile(8, true, true, true, (int) (4700), (int) (500), 200, 200, "/Coins/Flight_Bonus_03.png");
            //tch
            setImageTile(9, true, false, false, 5000, 200, 900, 700, "/Player_move/TheTeacher/TeacherLevel2.png");

            setCheckWinCoins();
        }else if(Constants.LEVEL == 2) {
            //pads
            setImageTile(0, true, false, false, 500, 400, 200, 50, "/Tiles/Pad_02_1 - Copie.png");
            setImageTile(1, true, false, false, 800, 400, 200, 50, "/Tiles/Pad_02_1 - Copie.png");
            setImageTile(2, true, false, false, 1500, 300, 200, 50, "/Tiles/Pad_02_1 - Copie.png");
            setImageTile(3, true, false, false, 1950, 600, 200, 50, "/Tiles/Pad_02_1 - Copie.png");
            setImageTile(4, true, false, false, 2800, 550, 200, 50, "/Tiles/Pad_02_1 - Copie.png");
            setImageTile(5, true, false, false, 3500, 200, 200, 50, "/Tiles/Pad_02_1 - Copie.png");

            //coins->Level 1: 7 coins
            setImageTile(6, true, true, false, (int) (tile[0].x + 70), (int) (tile[0].y - 200), 50, 50, "/Coins/Coin_03 - Copie.png");
            setImageTile(7, true, true, false, (int) (tile[1].x + 70), (int) (tile[1].y - 200), 50, 50, "/Coins/Coin_03 - Copie.png");
            setImageTile(8, true, true, false, (int) (tile[2].x + 70), (int) (tile[2].y - 200), 50, 50, "/Coins/Coin_03 - Copie.png");
            setImageTile(9, true, true, false, (int) (tile[3].x + 70), (int) (tile[3].y - 200), 50, 50, "/Coins/Coin_03 - Copie.png");
            setImageTile(10, true, true, false, (int) (tile[4].x + 70), (int) (tile[4].y - 200), 50, 50, "/Coins/Coin_03 - Copie.png");
            setImageTile(11, true, true, false, (int) (tile[5].x + 70), (int) (tile[5].y - 200), 50, 50, "/Coins/Coin_03 - Copie.png");
            setImageTile(12, true, true, false, (int) (tile[5].x + 500), (int) (tile[5].y - 200), 50, 50, "/Coins/Coin_03 - Copie.png");
            setImageTile(13, true, true, true, (int) (tile[5].x + 1300), (int) (tile[5].y + 300), 200, 200, "/Coins/Flight_Bonus_03.png");

            //be careful to te door
            setImageTile(14, true, false, false, tile[1].x + 450, tile[0].y, 100, 300, "/Tiles/Prop_1.png");
            setImageTile(15, true, false, false, tile[3].x + 450, tile[3].y - 300, 150, 300, "/Tiles/Prop_5.png");
            setImageTile(16, true, false, false, tile[5].x + 200, tile[5].y + 200, 200, 300, "/Tiles/Prop_7.png");

            //the teacher:
            setImageTile(17, true, false, false, tile[13].x + 300, tile[13].y - 300, 900, 700, "/Player_move/TheTeacher/TeacherLevel1.png");
            setCheckWinCoins();
        }

    }
    public void setImageTile(int index, boolean isSolid, boolean isCoin, boolean isFinalcoin,int x, int y, int w, int h, String imagePath) {
        try {
        tile[index] = new Tile(isSolid,isCoin,isFinalcoin,x,y,w,h);
        tile[index].image =  ImageIO.read((getClass().getResourceAsStream(imagePath)));
        }catch (IOException e) {    e.printStackTrace();    }
    }

    public void setCheckWinCoins() {    //functia in care calculez numarul total de puncte din nivel
        for(Tile t : tile) {
            if(t.IsCoin)
            {
                checkWinCoins++;
                System.out.println(checkWinCoins);
            }
        }
    }
    public void draw(Graphics2D g) {
        if(Constants.LEVEL ==1) {
            //draw tile
            g.drawImage(tile[0].image, tile[0].x, tile[0].y, tile[0].width, tile[0].height, null);
            g.drawImage(tile[1].image, tile[1].x, tile[1].y, tile[1].width, tile[1].height, null);
            g.drawImage(tile[2].image, tile[2].x, tile[2].y, tile[2].width, tile[2].height, null);
            //coin
            g.drawImage(tile[3].image, tile[3].x, tile[3].y, tile[3].width, tile[3].height, null);
            g.drawImage(tile[4].image, tile[4].x, tile[4].y, tile[4].width, tile[4].height, null);
            g.drawImage(tile[5].image, tile[5].x, tile[5].y, tile[5].width, tile[5].height, null);
            g.drawImage(tile[6].image, tile[6].x, tile[6].y, tile[6].width, tile[6].height, null);
            g.drawImage(tile[7].image, tile[7].x, tile[7].y, tile[7].width, tile[7].height, null);
            //final coin
            g.drawImage(tile[8].image, tile[8].x, tile[8].y, tile[8].width, tile[8].height, null);
            g.setColor(Color.white);
            g.drawString("The last one", tile[8].x + 20, tile[8].y - 60);
            //teacher
            g.drawImage(tile[9].image, tile[9].x, tile[9].y, tile[9].width, tile[9].height, null);
            g.setColor(Color.white);
            g.drawString("Good luck to the next level", tile[9].x + 270, tile[9].y + 200);

        }else if(Constants.LEVEL == 2) {
            //draw tile
            g.drawImage(tile[0].image, tile[0].x, tile[0].y, tile[0].width, tile[0].height, null);
            g.drawImage(tile[1].image, tile[1].x, tile[1].y, tile[1].width, tile[1].height, null);
            g.drawImage(tile[2].image, tile[2].x, tile[2].y, tile[2].width, tile[2].height, null);
            g.drawImage(tile[3].image, tile[3].x, tile[3].y, tile[3].width, tile[3].height, null);
            g.drawImage(tile[4].image, tile[4].x, tile[4].y, tile[4].width, tile[4].height, null);
            //draw coins
            g.drawImage(tile[5].image, tile[5].x, tile[5].y, tile[5].width, tile[5].height, null);
            g.drawImage(tile[6].image, tile[6].x, tile[6].y, tile[6].width, tile[6].height, null);
            g.drawImage(tile[7].image, tile[7].x, tile[7].y, tile[7].width, tile[7].height, null);
            g.drawImage(tile[8].image, tile[8].x, tile[8].y, tile[8].width, tile[8].height, null);
            g.drawImage(tile[9].image, tile[9].x, tile[9].y, tile[9].width, tile[9].height, null);
            g.drawImage(tile[10].image, tile[10].x, tile[10].y, tile[10].width, tile[10].height, null);
            g.drawImage(tile[11].image, tile[11].x, tile[11].y, tile[11].width, tile[11].height, null);
            g.drawImage(tile[12].image, tile[12].x, tile[12].y, tile[12].width, tile[12].height, null);
            g.drawImage(tile[13].image, tile[13].x, tile[13].y, tile[13].width, tile[13].height, null);
            g.setColor(Color.white);
            g.drawString("The last one", tile[13].x + 20, tile[13].y - 60);

            g.drawImage(tile[14].image, tile[14].x, tile[14].y, tile[14].width, tile[14].height, null);
            g.drawImage(tile[15].image, tile[15].x, tile[15].y, tile[15].width, tile[15].height, null);
            g.drawImage(tile[16].image, tile[16].x, tile[16].y, tile[16].width, tile[16].height, null);

            //teacher
            g.drawImage(tile[17].image, tile[17].x, tile[17].y, tile[17].width, tile[17].height, null);
            g.setColor(Color.white);
            g.drawString("Good luck to the next level", tile[17].x + 270, tile[17].y + 200);
        }
    }
}
