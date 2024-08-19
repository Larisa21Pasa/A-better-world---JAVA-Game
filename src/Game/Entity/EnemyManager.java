package Game.Entity;

import Game.Sound.Sound;
import Game.TileManager.Tile;
import Game.Window;
import Util.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
//INFORMATII:
// - pentru fluiditatea codului, am creat o clasa in care voi avea un vector de entitati de tip inamic plasati de-a lungul hartii jocului
public class EnemyManager {
    public  Enemy[] enemies ;
    int xEnemy= 500, yEnemy = (int) (Constants.SCREEN_HEIGHT - 300);    //valorile default ale primului inamic din nivel
    int localizeXDeplasament=0;
    BuildEntity buildEntity;
    public EnemyManager(Window window) {
        CreateEnemyArray(window);
    }
    public void updateEnemyArmy(Graphics2D g) {
        for(int i=0;i<enemies.length;i++) {     //pentru fiecare inamic voi face update-ul pozitiei sale
            enemies[i].update_enemy_position(g);
        }
    }
    public void CheckEnemyColisionWithPlayer(Player player, Graphics2D g, Sound WindowSound) {
        for(int i=0;i<enemies.length;i++) {     //pentru fiecare inamic, verific coliziunea cu playerul
            enemies[i].checkCollisionEnemy(player,g,WindowSound);
        }
    }
    public void CreateEnemyArray(Window window) {
        if(Constants.LEVEL == 1) {      //NIVEL 1
            this.enemies = new Enemy[5];
            for(int i=0;i<enemies.length;i++)
            {
                buildEntity = new BuildEntity(xEnemy+localizeXDeplasament,yEnemy,200,200,window);
                enemies[i] = buildEntity.buildEnemy();      //creez inamicul
                localizeXDeplasament+=1000;     //actualizez deplasamentul regulat
            }
            localizeXDeplasament=0;
        }else if(Constants.LEVEL == 2) {    //NIVEL 2
            this.enemies=new Enemy[2];
            for(int i=0;i<enemies.length;i++)
            {
                buildEntity = new BuildEntity(xEnemy+localizeXDeplasament,yEnemy,200,200,window);
                enemies[i] = buildEntity.buildEnemy();
                localizeXDeplasament+=1000;
            }
            localizeXDeplasament=0;
        }
    }




}
