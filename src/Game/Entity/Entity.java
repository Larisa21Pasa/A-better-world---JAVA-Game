package Game.Entity;

import Game.Sound.Sound;
import Game.Window;
import Util.GameOver;
import Util.KL;
import Util.PlayerAmmunition;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public abstract class Entity { //clasa ABSTRACTA care contine variabilele care sunt util. pt player si inamic

    // coordonatele folosite la crearea si localizarea entitatilor in ecran
    public float x,y,width,height;

    //variabile necesare playerului:
    public PlayerAmmunition playerAmmunition;

    //necesar desenului:
    public BufferedImage  move_right_1 ,move_right_2, move_left_1, move_left_2,move_up_1, move_up_2;
    public BufferedImage move_diagonal_right_1,move_diagonal_right_2,move_diagonal_left_1,move_diagonal_left_2;

    //necesar clasei PlayerController pentru implementare gravitatie, miscari
    public  static boolean jumping_right=false,jumping_left=false,jumping_up=false;
    public static String direction =  "right";

    public static float speedPlayerX=500;
    public static float speedPlayerY=500;
    public static  float speedJump= speedPlayerY*3;
    public static  boolean last_keyPressedRight=true,   fallingStop=false;


    //variabile necesare Inamicului:
    public BufferedImage walkEnemy1,walkEnemy2; //necesar pentru desen
    public static int goLeft ;
    public static int goRight ;
    public GameOver gameOver;
    public  int defaultXPosition ;
    public Window window;

    //variabile utilizate de player si inamic:
    public int spriteCounter = 0, spriteNumber=1;
    public Sound sound;
    public Rectangle solidAreaEntity;

    //player controller:
    //creez doua liste pe care le folosesc pentru fiecare sens de aruncat cu "arma" caracterului:
    public final LinkedList<PlayerAmmunition> playerAmmunitionsRight = new LinkedList<>();
    public final LinkedList<PlayerAmmunition> playerAmmunitionsLeft = new LinkedList<>();
    public   PlayerAmmunition playerAmmunitionTemp;
    public Player player;
    public KL kl;




}
