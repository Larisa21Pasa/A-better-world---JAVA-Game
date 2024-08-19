package Game.Entity;
import Util.Constants;
import Util.PlayerAmmunition;

import java.awt.*;

public class Player extends Entity{       //clasa in care implementez sprite-urile,le desenez si stabilesc  marginile playerului meu pentru coliziune

    public  Player(BuildEntity buildEntity) {
        x=buildEntity.x;
        y=buildEntity.y;
        height = buildEntity.height;
        width = buildEntity.width;
        solidAreaEntity  = buildEntity.solidAreaEntity;
        playerAmmunition=new PlayerAmmunition((int) getPlayerX(), (int) getPlayerY());  //transmiterea coordonatelor jucatorului catre clasa in care implementez aruncarea cu "praf magic"(sub forma de sageti stralucitoare)
        getPlayerImage();
    }
    public void draw(Graphics2D g) {

        switch (direction) {    //switch-ul in care, in functie de mesajul de pozitie transmis, aleg pozitiile caracterului
            case "up":
                if (spriteNumber == 1) {
                    ImageManager.DrawPosition(move_up_1, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                }
                if (spriteNumber == 2) {
                    ImageManager.DrawPosition(move_up_2, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    ImageManager.DrawPosition(move_up_2, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                }
                if (spriteNumber == 2) {
                    ImageManager.DrawPosition(move_up_1, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    ImageManager.DrawPosition(move_left_1, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                }
                if (spriteNumber == 2) {
                    ImageManager.DrawPosition(move_left_2, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    ImageManager.DrawPosition(move_right_1, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                }
                if (spriteNumber == 2) {
                    ImageManager.DrawPosition(move_right_2, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                }
                break;
            case "diagonal_up":
                if (spriteNumber == 1) {
                    ImageManager.DrawPosition(move_diagonal_right_1, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                }
                if (spriteNumber == 2) {
                    ImageManager.DrawPosition(move_diagonal_right_2, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                }
                break;
            case "diagonal_down":
                if (spriteNumber == 1) {
                    ImageManager.DrawPosition(move_diagonal_left_1, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                }
                if (spriteNumber == 2) {
                    ImageManager.DrawPosition(move_diagonal_left_2, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                }
                break;
            case "shoot_right":
                ImageManager.DrawPosition(move_diagonal_right_1, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                ImageManager.DrawPosition(move_diagonal_right_2, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                break;
            case "shoot_left":
                ImageManager.DrawPosition(move_diagonal_left_1, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                ImageManager.DrawPosition(move_diagonal_left_2, (int) getPlayerX(), (int)getPlayerY(),(int) width,(int)height,g);
                break;
        }
    }
    public void getPlayerImage() {
        move_up_1 = ImageManager.LoadImage("/Player_jump/jump1.png");
        move_up_2 = ImageManager.LoadImage("/Player_jump/jump2.png");

        move_left_1 = ImageManager.LoadImage("/Player_move/move_stanga_1.png");
        move_left_2 = ImageManager.LoadImage("/Player_move/move_stanga_4.png");

        move_right_1 = ImageManager.LoadImage("/Player_move/move1.png");
        move_right_2 = ImageManager.LoadImage("/Player_move/move4.png");

        move_diagonal_right_1 = ImageManager.LoadImage("/Player_jump/jump1.png");
        move_diagonal_right_2 = ImageManager.LoadImage("/Player_jump/jump2.png");

        move_diagonal_left_1 = ImageManager.LoadImage("/Player_jump/jump2.png");
        move_diagonal_left_2 = ImageManager.LoadImage("/Player_jump/jump4.png");
    }
    //am incercat sa transmit dreptunghiurile speficice fiecarei parti a caracterului pentru coliziune
    public Rectangle getBoundsTopPlayer()    {  return new Rectangle((int) (getPlayerX()+10), (int) getPlayerY(), (int) (width-20),10);}
    public Rectangle getBoundsBottomPlayer() {  return new Rectangle((int) (getPlayerX()), (int) ((int) getPlayerY()+height-20), (int) (width),20 );  }
    public Rectangle getBoundsLeftPlayer()   {  return new Rectangle((int) (getPlayerX()), (int) ((int) getPlayerY()+40), 20, (int) (height-80));   }
    public Rectangle getBoundsRightPlayer()  {  return new Rectangle((int) (getPlayerX()+width-20), (int) ((int) getPlayerY()+40), 20, (int) (height-80));  }
    public void setPlayerY(float yPlayer) {
        y= yPlayer;
    }
    public void setPlayerX(float xPlayer) {
        x= xPlayer;
    }
    public double getPlayerX() {
        return x;
    }
    public double getPlayerY() {
        return y;
    }
}