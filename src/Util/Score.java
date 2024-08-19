package Util;

import Collision.Collision;

import java.awt.*;

public class Score {        //contorizarea scorului (numarul de puncte adunate) in partea stanga-sus a ecranului
    public String text;
    public Font font;
    public double x,y,width,height;

    public Score() {
        this.text = "You got ";
        this.font = new Font("Times New Roman",Font.BOLD,26);
        this.x =50;
        this.y = 70;
        this.width=450;
        this.height=80;
    }
    public void ScoreMessage(Graphics2D g){
       g.setColor( new Color(70,120,80));
        g.fillRect(0,0, (int) width, (int) height);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(text+Collision.countCoin+" coins!", (float) x,(float)y);
        g.drawString("Level: " +Constants.LEVEL, (float) x+250,(float)y);
    }

}
