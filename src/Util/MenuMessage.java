package Util;

import Util.Constants;

import java.awt.*;

public class MenuMessage {  //stabilirea mesajului in cazul castigarii nivelului

    public static void NewGameMessage(Graphics2D g, int x, int y) {
        g.setFont(g.getFont().deriveFont(Font.BOLD, 24));
        g.drawString("You already passed ", (float) (x) + 600, (float) (y + 90));
        g.drawString(" the 1'st level.", (float) (x) + 600, (float) (y + 120));
        g.drawString("Press Load Game for 2'nd level. ", (float) (x) + 600, (float) (y + 150));
    }
    public static void LoadGameMessage(Graphics2D g, int x, int y) {
        g.setFont(g.getFont().deriveFont(Font.BOLD, 20));
        g.drawString("If you lose or won the 1'st level, ", (float) (x) + 600, (float) (y + 190));
        g.drawString("you have to press New Game", (float) (x) + 600, (float) (y + 220));
        g.drawString(" Let's try again! ", (float) (x) + 600, (float) (y + 250));
    }
}
