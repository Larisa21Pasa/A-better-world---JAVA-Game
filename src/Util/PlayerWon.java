package Util;

import java.awt.*;

public class PlayerWon {  //stabilirea mesajului in cazul castigarii nivelului
    public Font font;
    public double x,y;

    public PlayerWon() {

        this.font = new Font("Lucida Calligraphy",Font.BOLD,72);
        this.x = Constants.SCREEN_WIDTH/2-300;
        this.y = Constants.SCREEN_HEIGHT/2-200;
    }
    public void WinMessage(Graphics2D g) {
        g.setColor( new Color(70,120,80));
        g.fillRect(0,0, (int) Constants.SCREEN_WIDTH, (int) Constants.SCREEN_HEIGHT);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString("Concrats!", (float) x+30,(float)y);
        g.drawString("NEXT LEVEL!", (float) x,(float)y+200);
    }
}
