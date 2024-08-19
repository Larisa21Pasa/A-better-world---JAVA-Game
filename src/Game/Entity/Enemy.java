package Game.Entity;

import Game.Sound.Sound;
import Game.State.State;
import Game.TileManager.TileUtil;
import Game.Window;
import Util.Constants;
import Util.GameOver;

import java.awt.*;

import static Game.Window.g;

public class Enemy extends  Entity{      //clasa in care implementez crearea inamicului, in care verific coliziunea acestuia cu jucatorul si in care reactualizez pozitia inamicului
      public Enemy(BuildEntity buildEntity) {     //constructor cu parametrul bulider
          //initializarea valorilor cu cele create in clasa BuildEntity
        x=  buildEntity.x;
        y=  buildEntity.y;
        height = buildEntity.height;
        width =  buildEntity.width;
        solidAreaEntity=buildEntity.solidAreaEntity;
        window =buildEntity.window;

        //alte variabile initializate ca default:
        defaultXPosition = (int) buildEntity.x;
        goLeft=0;
        goRight=0;
        gameOver=new GameOver();
        sound=new Sound();
        getEnemyImage();
    }

    public void update_enemy_position(Graphics2D g) {   //pentru inceput, inamicul va traversa de la dreapta la stanga fereastra jocului
       if(goLeft <= 50) {
           x -= 10;
           goLeft++;
       }
       else if(goRight <= 50) {
           x+=10;
           goRight++;
       }
       else {   //reset
           goLeft=0;
           goRight=0;
       }

        this.spriteCounter++;
        SetEntityCounter(); //functie pentru reactualizarea alternativa a imaginii inamicului
        draw(g);    //desenarea lui
    }
    public void draw(Graphics2D g) {
        if (this.spriteNumber == 1) {
          ImageManager.DrawPosition(walkEnemy1, (int) x, (int) y, (int)width, (int)this.height,g);
        }
        if (this.spriteNumber == 2) {
            ImageManager.DrawPosition(walkEnemy2, (int) x, (int) y, (int)width, (int)this.height,g);
        }
    }
    public void getEnemyImage() {
        walkEnemy1= ImageManager.LoadImage("/Enemy/inamic1.png");
        walkEnemy2= ImageManager.LoadImage("/Enemy/inamic2.png");
    }

    public void checkCollisionEnemy(Player player, Graphics2D g, Sound WindowSound) {   //verific coliziunea acestuia cu jucatorul
        if (player.getBoundsTopPlayer().intersects(this.getBoundsEnemy())) {    //daca cei doi (caracterul si inamicul) fac coliziune , jocul se termina
            FinishLevel(WindowSound,window);
        }

    }
    public void FinishLevel( Sound WindowSound,Window window) {     //functie pentru terminarea jocului cu necesitatea reluarii nivelului curent
        gameOver.GameOverMessage(g);
        WindowSound.StopMusic();
        WindowSound.PlaySoundsEffect(1);
        Window.tutorialState = 0;
        window.gameState= window.menuState;
        window.InitNewGame();

    }

    public Rectangle getBoundsEnemy() {     return new Rectangle((int) getX(), (int) getY(),(int)this.width,(int)this.height);    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double xEnemy) {
        x = (float) xEnemy;
    }
    public void setY(double yEnemy) {
        y = (float) yEnemy;
    }
    public void MakeEnemyDisappear() {
        setX(0);
        setY(0);
        setWidth(0);
        setHeight(0);
    }
    public void setWidth(double w) {   width = (float) w; }
    public void setHeight(double h) {  height = (float) h;   }
    public void SetEntityCounter() {
        if (this.spriteCounter > 5) {
            if (this.spriteNumber == 1) {
                this.spriteNumber = 2;
            } else if (this.spriteNumber == 2) {
                this.spriteNumber = 1;
            }
            this.spriteCounter = 0;
        }
    }
}
