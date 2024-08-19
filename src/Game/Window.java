package Game;;
import javax.swing.*;
import java.awt.*;

import Collision.Collision;
import DataBase.DataBase;
import Game.Entity.*;
import Game.PlayGroundManager.PlayGround;
import Game.Sound.Sound;
import Game.State.StateGameMenu;
import Game.State.StateGameOption;
import Game.State.StateGamePause;
import Game.TileManager.TileManager;
import Game.TileManager.TileUtil;
import Util.*;

public class Window extends JFrame implements Runnable  {   //clasa in care implemenetz toate componentele jocului, in care creez fereastra si in care reactualizez jocul la fiecare frame

    //Graphics2D este libraria pe care o folosesc ca sa gestionez culorile,formele, dimensiunile din joc
    public static Graphics2D g,g2;
    public  KL kl = new KL(this);
    public Player player;
    public static Enemy enemy;
    public EnemyManager enemyManager;
    public PlayerController playerController;
    public PlayGround playGround;
    public static TileManager tileManager;
    public static Collision collision;
    public Score score;
    public  Camera camera;
    public StateGamePause stateGamePause;
    public StateGameMenu stateGameMenu;
    public StateGameOption stateGameOption;
    public Sound sound;

    //GAME STATE:
    public int gameState;
    public static int staticgame;
    public  final int menuState = 0;
    public  final int playState=1;
    public final int pauseState = 2;
    public static int tutorialState=0; //0:the first screen, 1:the sec screen
    public final int optionState=3, subStateOption=0;

    //DATA BASE
    public DataBase dataBase;

    public Window() {
        this.setSize((int) Constants.SCREEN_WIDTH, (int) Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
       // this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addKeyListener(kl);
        //getGraphics() creeaza contextul grafic in care var g este utilizata
        InitNewGame();
    }

    public  void InitNewGame()
    {
        g = (Graphics2D) this.getGraphics();
        g2 = (Graphics2D) this.getGraphics();
        Collision.countCoin=0;

        TileUtil.checkWinCoins=0;
        player =  new BuildEntity( 0,(int)(Constants.SCREEN_HEIGHT-300),200,200,this).buildPlayer();
        enemyManager = new EnemyManager(this);
        playerController = new PlayerController(player,kl);
        tileManager = new TileManager();
        collision =  new Collision();
        score=new Score();
        camera=Camera.getInstanceCamera();
        sound=new Sound();

        //Game state:
        gameState=menuState;
        stateGamePause =new StateGamePause(this);
        stateGameMenu = new StateGameMenu(this);
        stateGameOption = new StateGameOption(this);


        //setare constante pt stabilirea limitelor sus/jos/stanga:
        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.TOOLBAR_LEFT = this.getInsets().left;
        Constants.TOOLBAR_BOTTOM = this.getInsets().bottom;

        //db
        dataBase=new DataBase();
        dataBase.CreateDataBase();
        playGround=new PlayGround(dataBase);


    }

    public void update(double delta_time) { //actualizarea la fiecare frame a pozitiei jucatorului, a coliziunilor si a altor functionalitati implementate
        if ((gameState == menuState)) {
            stateGameMenu.draw(g);
        }
        else {
            if (gameState == playState) {
                camera.update(player);

                g.translate(camera.getX(), camera.getY());
                playGround.draw(g);
                playerController.update_player_position(delta_time);
                playerController.update_ammunition(enemyManager);
                player.draw(g);
                tileManager.draw(g);
                enemyManager.updateEnemyArmy(g);
                g.translate(-camera.getX(), -camera.getY());
                enemyManager.CheckEnemyColisionWithPlayer(player, g, sound);
                collision.checkCollisionPlayer(player, tileManager, sound,this);
                score.ScoreMessage(g);
            }
            if (gameState == pauseState) {
                //nothing for now
                stateGamePause.draw(g);
            }
            if(gameState == optionState) {
                stateGameOption.draw(g);
            }
        }

    }


    @Override
    public void run() {
        double lastFrame = 0.0;
        // sound.PlayMusic(0);
        while (true) {
            //calculez timpul dintre frame uri
            double time = Time.getTime();
            double delta_time = time-lastFrame;
            lastFrame  = time; //actualizez ultimul frame
            update(delta_time);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }

}

