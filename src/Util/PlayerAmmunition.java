package Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static Game.Window.g;
//INFORMATII:
// - munitia este nelimitata, fiind, din poveste, un atuu nativ
// - echilibrand balanta, desi e nelimitata, playerul nu poate "impusca" inamicii daca se afla in mers/ zbor

public class PlayerAmmunition {     //clasa in care  updatez "munitia" playerului
    int x,y,width,height;
    public BufferedImage image;
    public PlayerAmmunition(int x, int y) {
        this.x=x+20;
        this.y=y+30;
        this.width=Constants.ARROW_WIDTH;
        this.height=Constants.ARROW_HEIGHT;
        getAmmunitionImage();
    }
    public void update_shoot_right() {
        this.x +=50;
        draw(g);
    }
    public void update_shoot_left() {
        this.x -=50;
        draw(g);
    }
    public Rectangle getBoundsAmnunition()  {    return new Rectangle(this.x,this.y,this.width,this.height); }
    public void draw(Graphics2D g) { g.drawImage(image,  this.x, this.y, this.width, this.height,null); }
    public void getAmmunitionImage() {  try {   this.image = ImageIO.read((getClass().getResourceAsStream("/Ammunition/Icon25.png")));  }catch (IOException e) {    e.printStackTrace();    }   }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
