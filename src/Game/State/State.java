package Game.State;



import Game.Window;

import java.awt.*;
import java.awt.image.BufferedImage;

//INFORMATII:
// - clasa state este o clasa abstracta in care stochez valorile default pentru afisarea meniului, pentru numarul optiunii la care ma situez (commandNum)
public abstract class State {
    public Window window;
    static Font arial_40;
    public static BufferedImage imageMenu,hello,imageTutorial;

    public String text,newGame, loadGame, quit;
    public Font font;
    public double x;
    public double y;
    public static int commandNum;
    public  int  subStateOption=0;

}
