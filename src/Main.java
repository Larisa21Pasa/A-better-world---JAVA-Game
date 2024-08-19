import Game.*;
import Game.Window;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Window window = new Window();
        //creez un fir de executie pt fereastra mea
        Thread t  = new Thread(window);
        t.start();
    }
}