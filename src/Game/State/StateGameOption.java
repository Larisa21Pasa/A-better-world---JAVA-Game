package Game.State;

import Game.Entity.ImageManager;
import Game.Sound.Sound;
import Game.Window;
import Util.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StateGameOption extends State {    //clasa in care  implementez optiunile sound, end game, back (to game)
    public StateGameOption(Window window) {
        this.text = "OPTION";
        this.window=window;
        this.font = new Font("Lucida Calligraphy", Font.BOLD, 36);
        this.x = getX_ForCenteredText(text,Window.g);
        this.y = Constants.SCREEN_HEIGHT / 2 - 200;
    }

    public  void draw(Graphics2D g) {
        if(window.gameState == window.optionState)
        {
            drawoOptionMessage(g);
        }
    }

    public  void drawoOptionMessage(Graphics2D g) {
        g.setColor(new Color(70, 120, 80));
        g.setFont(arial_40);

        int frameX=300;
        int frameY = 300;
        int frameW=700;
        int frameH = 500;
        drawSubWindow(frameX,frameY,frameW,frameH,g);
        switch (subStateOption) {
            case 0:
                option_top(frameX,frameY,g);
                break;
        }
    }

    public void option_top(int frameX, int frameY,Graphics2D g) {
        int textX,textY;
        int drawY=80;

        //title
        g.setFont(this.font);
        g.setColor(Color.white);

        textX=getX_ForCenteredText(text,g);
        textY = frameY + drawY+20;

        // g.setFont(g.getFont().deriveFont(Font.BOLD, 48F));
        g.drawString("Option",textX,textY);
        drawY+=80;

        //fs
        textX=getX_ForCenteredText(text,g)-150;
        textY = frameY + drawY;

        g.drawString("Full Screen : not this time",textX,textY);
        drawY+=80;
        if(commandNum == 0) {
          g.drawString(">",textX-50,textY);
        }

        //sound
        textY = frameY + drawY;
        int soundY=textY;
        g.drawString("Sound",textX,textY);
        drawY+=80;
        if(commandNum == 1) {
            g.drawString(">",textX-50,textY);
        }

        //end game
        textY = frameY + drawY;

        g.drawString("End Game",textX,textY);
        drawY+=80;
        if(commandNum == 2) {
            g.drawString(" >",textX-50,textY);
            if(window.kl.ENTER_pressed) {
                options_EndGameConfirmation(300,300,g);
                commandNum=4;
            }
        }
        //back
        textX +=200;
        textY = frameY + drawY;

        g.drawString("Back",textX,textY);
        if(commandNum == 3) {
            g.drawString(">",textX-50,textY);
        }

        //music volume
        textY=soundY-30;
        g.setStroke(new BasicStroke(3));
        g.drawRect(textX,textY,250,30); //250/5=50
        int volumeWidth = 50* Sound.VolumeScale;
        g.fillRect(textX,textY,volumeWidth,30 );
    }

    public void options_EndGameConfirmation(int frameX, int frameY,Graphics2D g) {
        int textX = frameX;
        int textY = frameY;

        String text = "Quit the game and \nreturn to te menu?";
        for(String line:text.split("\n")) {
            g.drawString(text,textX,textY);
            textY+=40;
        }
        if(window.kl.ENTER_pressed) {
            if(  window.tutorialState==1 )
                window.tutorialState=0;
        }
    }
    public void drawSubWindow(int x,int y,int w, int h,Graphics2D g)
    {
        try {
            BufferedImage image =  ImageIO.read((ImageManager.class.getResourceAsStream("/Game/State/PlayerHelloMenu/Captură.PNG")));
            g.drawImage(image,x,y,w,h,null);
        }catch (IOException e){e.printStackTrace();}
    }
    public int getX_ForCenteredText(String text, Graphics2D g)
    {
        int length = (int) g.getFontMetrics().getStringBounds(text,g).getWidth();
        int x= (int) (Constants.SCREEN_WIDTH/2-length/2);
        return x;
    }

}
