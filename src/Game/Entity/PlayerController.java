package Game.Entity;
import Game.Sound.Sound;
import Game.Window;
import  Util.*;
import java.util.LinkedList;


public class PlayerController extends Entity {     //clasa in care: reactualizez pozitia caracterului in functie de tastele apasate, implementez efectul de cadere, reactualizez pozitiile "armei" aruncate de caracter
    public PlayerController(Player player,KL kl) {
        this.player = player;
        this.kl=kl;
        player.sound=new Sound();
    }
    public void update_player_position(double delta_time) {
        if (kl.UP_pressed || kl.RIGHT_pressed || kl.LEFT_pressed || kl.SPACE_pressed || kl.DOWN_pressed) {

            if ( kl.UP_pressed && kl.RIGHT_pressed)
            {
                if(  player.x + 100 * delta_time <= Constants.WORLD_WIDTH-500) {    //limitez accesul
                    Player.direction = "diagonal_up"; //setez valoarea directiei pentru switch-ul din clasa Player
                    Player.jumping_right = true;     //in functie de combinarea tastelor, setez true/false valorile pentru saritura
                    Player.jumping_left = false;
                    Player.jumping_up = false;
                    this.player.y = (float) (this.player.y - speedJump / 2 * delta_time);  //reactualizez pozitiile jucatorului
                    this.player.x = (float) (this.player.x + speedJump / 2 * delta_time);

                    player.sound.PlaySoundsEffect(6);

                }
            }else if ( kl.UP_pressed && kl.LEFT_pressed ) {     //la fel si in restul functiei
                Player.direction="diagonal_down";
                Player.jumping_left =true;
                Player.jumping_right=false;
                Player.jumping_up=false;
                if( player.x + 100 * delta_time > Constants.TOOLBAR_LEFT) {     //limitez accestul caracterului, stabilind drept limite pentru stanga/sus/jos dimensiunile ferestrei
                    this.player.y = (float) (this.player.y - speedJump / 2 * delta_time);
                    this.player.x = (float) (this.player.x - speedJump / 2 * delta_time);
                }
                player.sound.PlaySoundsEffect(6);

            }else if ( kl.DOWN_pressed && kl.RIGHT_pressed ) {
                Player.direction="diagonal_up";
                if((int) (player.y + 100 * delta_time+player.height) < Constants.SCREEN_HEIGHT-200  && player.x + 100 * delta_time  <= Constants.WORLD_WIDTH-500) {
                    this.player.y = (float) (this.player.y + speedJump / 2 * delta_time);
                    this.player.x = (float) (this.player.x + speedJump / 2 * delta_time);
                }
            }else if (kl.DOWN_pressed && kl.LEFT_pressed){
                Player.direction="diagonal_down";
                if(  player.x + 100 * delta_time > Constants.TOOLBAR_LEFT && (int) (player.y + 100 * delta_time+player.height) < Constants.SCREEN_HEIGHT-200 ) {
                    this.player.y = (float) (this.player.y + speedJump / 2 * delta_time);
                    this.player.x = (float) (this.player.x - speedJump / 2 * delta_time);
                }
            }else if (kl.UP_pressed && player.y - 100 * delta_time > Constants.TOOLBAR_HEIGHT) {
                Player.direction = "up";
                Player.jumping_up =true;
                Player.jumping_right=false;
                Player.jumping_left=false;
                this.player.y = (float) (this.player.y - speedJump * delta_time);

                player.sound.PlaySoundsEffect(6);
            }else if (kl.RIGHT_pressed && player.x + 100 * delta_time  <= Constants.WORLD_WIDTH-500) {
                Player.direction = "right";
                this.player.x = (float) (this.player.x + speedPlayerX * delta_time);
                last_keyPressedRight= true;


            }else if (kl.LEFT_pressed && player.x + 100 * delta_time > Constants.TOOLBAR_LEFT) {
                Player.direction = "left";
                this.player.x = (float) (this.player.x - speedPlayerX * delta_time);
                last_keyPressedRight= false;
            }else if ( kl.SPACE_pressed ) {     //in cazul apasarii tastei "SPACE", voi adauga la lista dublu inlantuita arma
                System.out.println("shoot");
                player.sound.PlaySoundsEffect(5);
                if(last_keyPressedRight) {  //pentru a stabilidirectia curenta, folosesc variabila "last_keyPressedRight"
                    Player.direction = "shoot_right";
                    addAmmunitionR(new PlayerAmmunition((int) player.getPlayerX(), (int) player.getPlayerY() ));
                }
                else  {
                    Player.direction = "shoot_left";
                    addAmmunitionL(new PlayerAmmunition((int) player.getPlayerX(), (int) player.getPlayerY() ));
                }
            }else if (kl.DOWN_pressed && ((player.y + 100 * delta_time) + player.height) < Constants.SCREEN_HEIGHT - 100) {
                Player.direction = "down";
                this.player.y = (float) (this.player.y + speedPlayerY * delta_time);
            }
            SetPlayerCounter();
        }else {     //in cazul in care nu apas pe nimic, creez efectul de cadere
                if(Player.jumping_right && ((player.y + 100 * delta_time) + player.height) < Constants.SCREEN_HEIGHT - 100 ) {
                    if(player.y - 100 * delta_time > Constants.TOOLBAR_HEIGHT && !fallingStop) {
                        player.x += 30;
                        player.y += 60;
                    }
                }
                if(Player.jumping_left && ((player.y + 100 * delta_time) + player.height) < Constants.SCREEN_HEIGHT - 100 ) {
                    if(player.y - 100 * delta_time > Constants.TOOLBAR_HEIGHT && !fallingStop) {
                        player.x-=30;
                       player.y+=60;
                    }
                }
                if(Player.jumping_up && ((player.y + 100 * delta_time) + player.height) < Constants.SCREEN_HEIGHT - 100)
                {
                    if(!fallingStop)
                        player.y+=80;
                }
        }
    }

    public static void setSpeedPlayerX(float speedPlayerX) {
        PlayerController.speedPlayerX = speedPlayerX;
    }
    public static void setSpeedPlayerY(float speedPlayerY) {
        PlayerController.speedPlayerY = speedPlayerY;
    }
    public void SetPlayerCounter() {
        player.spriteCounter++;
        if (player.spriteCounter > 5) {
            if (player.spriteNumber == 1) {
                player.spriteNumber = 2;
            } else if (player.spriteNumber == 2) {
                player.spriteNumber = 1;
            }
            player.spriteCounter = 0;
        }
    }

    public void update_ammunition(EnemyManager enemyManager) {

        for (int i = 0; i < playerAmmunitionsRight.size(); i++) {
            playerAmmunitionTemp = playerAmmunitionsRight.get(i);
            playerAmmunitionTemp.update_shoot_right();
            for(int j=0;j<enemyManager.enemies.length;j++) {
                if (enemyManager.enemies[j].getBoundsEnemy().intersects(playerAmmunitionTemp.getBoundsAmnunition())) {
                    enemyManager.enemies[j].sound.PlaySoundsEffect(4);
                    enemyManager.enemies[j].MakeEnemyDisappear();
                    playerAmmunitionsRight.clear();
                }
            }
        }

        for (int i = 0; i < playerAmmunitionsLeft.size(); i++) {
            playerAmmunitionTemp = playerAmmunitionsLeft.get(i);
            playerAmmunitionTemp.update_shoot_left();
            for (int j = 0; j < enemyManager.enemies.length; j++) {
                if (enemyManager.enemies[j].getBoundsEnemy().intersects(playerAmmunitionTemp.getBoundsAmnunition())) {
                    enemyManager.enemies[j].MakeEnemyDisappear();
                    playerAmmunitionsLeft.clear();
                }
            }

        }
    }

    public void addAmmunitionR(PlayerAmmunition a) {    playerAmmunitionsRight.add(a);  }
    public void addAmmunitionL(PlayerAmmunition a) {    playerAmmunitionsLeft.add(a);   }
    public static void setSpeedJump(float speedJump) { PlayerController.speedJump = speedJump; }

}