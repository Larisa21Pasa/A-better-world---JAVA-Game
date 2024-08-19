package Game.State;

import Game.Window;
import Util.Constants;
import Util.MenuMessage;
import java.awt.*;

public class StateGameMenu  extends  State{
    public StateGameMenu(Window window) {
        this.text = "A better world";
        this.window=window;
        this.font = new Font("Lucida Calligraphy", Font.BOLD, 76);
        this.x = getX_ForCenteredText(text,Window.g)-100;
        this.y = Constants.SCREEN_HEIGHT / 2 - 200;
        getMenuImages();
    }

    public  void draw(Graphics2D g) {
        //MENU STATE:
        if(window.gameState == window.menuState) {
            drawMenuMessage(g);
        }
    }

    public  void drawMenuMessage(Graphics2D g)  {
        Font fontTutorial=new Font("Lucida Calligraphy",Font.PLAIN,24);
        if( window.tutorialState == 0  ) {
            getMenuImage(g);
            g.setFont(font);
            //Shadow:
            g.setColor(Color.darkGray);
            g.drawString(text, (int) (x + 5), (int) (y + 5));
            //Main color:
            g.setColor(Color.white);
            g.drawString(text, (int) x, (int) y);
            //Fairy image hello:
            getHelloImage(g);

            //MENU:
            newGame = "NEW GAME";
            g.setFont(g.getFont().deriveFont(Font.BOLD, 48F));
            g.drawString(newGame, (float) x + 200, (float) (y + 100));
            if (commandNum == 0) {
                g.drawString(">", (float) (x)+150, (float) (y + 100));
                if( Constants.LEVEL == 2) {
                    MenuMessage.NewGameMessage(g, (int) x,(int)y);
                }
            }

            loadGame = "LOAD GAME";
            g.setFont(g.getFont().deriveFont(Font.BOLD, 48F));
            g.drawString(loadGame, (float)  x + 200, (float) (y + 200));
            if (commandNum == 1) {
                g.drawString(">", (float) (x)+150, (float) (y + 200));
                if( Constants.LEVEL == 1) {
                 MenuMessage.LoadGameMessage(g,(int)x,(int)y);
                }
            }

            quit = "QUIT";
            g.setFont(g.getFont().deriveFont(Font.BOLD, 48F));
            g.drawString(quit, (float)x + 200, (float) (y + 300));
            if (commandNum == 2) {
                g.drawString(">", (float)(x)+150, (float) (y + 300));
            }
        }
        else if(window.tutorialState == 1) {
            getTutorialImage(g);
            g.setColor(Color.white);

            g.setFont(g.getFont().deriveFont(42F));
            String text = "Tutorial";
            g.drawString(text, (int) x+100, (int) y-100);

            g.setFont(fontTutorial);
            text = "For move you can use: UP, DOWN, LEFT, RIGHT.";
            g.drawString(text, (int) x-200, (int) y);

            text = "If you want to change music volume or go back to the main menu, press O.";
            g.drawString(text, (int) x-200, (int) y+40);

            text = "For a break, just press P.";
            g.drawString(text, (int) x-200, (int) y+80);

            text = "BE CAREFUL! If you touch some flower, you may dissapear. Just try to return in window.  ";
            g.drawString(text, (int) x-200, (int) y+120);


            text = "You are in an examen, so be perfect!";
            g.drawString(text, (int) x-200, (int) y+160);

            text = "Now you are ready to help the fairy!";
            g.drawString(text, (int) x-200, (int) y+200);

            text = "Skip";
            g.drawString(text, (int) x+500, (int) y+240);
            if(commandNum == 3) {
                g.drawString(">",(float) x+450 ,(float) (y + 240));
            }

            text = "Back";
            g.drawString(text, (int) x+500, (int) y+280);
            if(commandNum == 4 ) {
                g.drawString(">",(float) x+450 ,(float) (y + 280));
            }
        }
    }

    public void getHelloImage(Graphics2D g) {
        StateImageManager.DrawPosition(hello,100, (int) (y + 100), 400, 400,g);
    }
    public void getMenuImage(Graphics2D g) {
        StateImageManager.DrawPosition(imageMenu,0, 0, (int) Constants.SCREEN_WIDTH, (int) Constants.SCREEN_HEIGHT,g);
    }
    public void getTutorialImage(Graphics2D g) {
        StateImageManager.DrawPosition(imageTutorial,0, 0, (int) Constants.SCREEN_WIDTH, (int) Constants.SCREEN_HEIGHT,g);
    }
    public int getX_ForCenteredText(String text, Graphics2D g) {
        int length = (int) g.getFontMetrics().getStringBounds(text,g).getWidth();
        int x= (int) (400);
        return x;
    }

    public void getMenuImages() {
        hello = StateImageManager.LoadImage("/Player_jump/jump1.png");
        imageMenu = StateImageManager.LoadImage("/PlayGroundLayers/PlaygroundMenu/backgroundMenu.png");
        imageTutorial = StateImageManager.LoadImage("/PlayGroundLayers/PlaygroundMenu/backgroundTutorial.PNG");
    }
}
