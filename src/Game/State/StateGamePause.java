package Game.State;

import Game.Window;
import Util.Constants;

import java.awt.*;

public class StateGamePause extends State {



    public StateGamePause(Window window) {
        this.text = "PAUSE";
        this.window=window;
        this.font = new Font("Times New Roman", Font.BOLD, 72);
        this.x = getX_ForCenteredText(text,Window.g);
        this.y = Constants.SCREEN_HEIGHT / 2 - 200;
    }

    public  void draw(Graphics2D g) {
        if(window.gameState == window.pauseState) {
            drawPauseMessage(g);
        }
    }

    public  void drawPauseMessage(Graphics2D g) {
        g.setFont(arial_40);
        g.setColor(Color.white);
        g.drawString(text, (int) x, (int) y);
    }
    public int getX_ForCenteredText(String text, Graphics2D g) {
        int length = (int) g.getFontMetrics().getStringBounds(text,g).getWidth();
        int x= (int) (Constants.SCREEN_WIDTH/2-length/2);
        return x;
    }

}
