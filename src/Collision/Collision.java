package Collision;

import  Game.Entity.Player;
import Game.Entity.PlayerController;
import Game.Sound.Sound;
import Game.State.State;
import Game.TileManager.TileManager;
import Game.Window;
import Util.Constants;
import Util.PlayerWon;
import Game.TileManager.Tile;
import static Game.TileManager.TileManager.checkWinCoins;
import static Game.Window.g;

public class Collision {        //clasa in care verific daca jucatorul face coliziune cu obiecte precum platformele si punctele
    public static int countCoin=0;
    PlayerWon playerWon=new PlayerWon();
    Sound sound=new Sound();

    public void checkCollisionPlayer(Player player, TileManager tileManager, Sound WindowSound, Window window) {

        for(Tile t : tileManager.tile)      //pentru fiecare dala
        {
            if(!t.solid) break;     //verific daca este solida pentru a face coliziune. In caz contrar, inchei for-ul pentru dala respectiva
            //pentru dala solida, verific fiecare latura a jucatorului pentru a vedea care face coliziune
            if (player.getBoundsTopPlayer().intersects(t.getBounds())) {  //daca se intersecteaza in partea de sus
                PlayerController.setSpeedJump(0);       //setez viteza de salt cu 0
                player.setPlayerY(t.getY() + t.height*3); //reactualizez coordonata y pentru jucator
                CheckIsCoin(t,WindowSound,window); //verific cu functia CheckIsCoin() daca este punct. In caz afirmativ, ractualizez mesajul pe ecran si, eventual, opresc jocul, anuntand castigarea acestui nivel prin adunarea tuturor punctelor disponibile
            }
            //setez valoarea initiala a vitezei pentru saritura
            PlayerController.setSpeedJump(1000);

            if (player.getBoundsBottomPlayer().intersects(t.getBounds())) { //la fel procedez si pentru celelalte cazuri de coliziune
                PlayerController.fallingStop=true;  //pentru a putea opri caracterul pe platforma, folosesc o valoarea booleana care imi indica atunci cand jucatorul este in cadere
                PlayerController.setSpeedPlayerY(0);
                player.setPlayerY(t.getY() - t.height*3);
                CheckIsCoin(t,WindowSound,window);
                break;
            }
            PlayerController.setSpeedPlayerY(500);
            PlayerController.fallingStop=false;

            if (player.getBoundsLeftPlayer().intersects(t.getBounds())) {
                PlayerController.setSpeedPlayerX(0);
                player.setPlayerX(t.getX() + t.width);
                CheckIsCoin(t,WindowSound,window);
            }
            PlayerController.setSpeedPlayerX(500);

            if (player.getBoundsRightPlayer().intersects(t.getBounds())) {
                PlayerController.setSpeedPlayerX(0);
                player.setPlayerX(t.getX() - t.width);
                CheckIsCoin(t,WindowSound,window);
            }
            PlayerController.setSpeedPlayerX(500);
        }
    }

    public void CheckIsCoin(Tile t, Sound WindowSound,Window window) {   //functia prin care verific daca este punct si care ajuta la incheierea jocului

            if (t.IsCoin) {  //in caz afirmativ, dala dispare
                sound.PlaySoundsEffect(3);  //aplic sunet potrivit
                t.MakeTileDisapear();
                countCoin++;    //variabila folosita pentru afisarea pe ecran a nr de puncte acumulat
                System.out.println("ai castigat " + countCoin + " puncte");

                checkWinCoins--;  //scad din totalul punctelor din nivelul curent

                if (checkWinCoins == 0) //daca s-au terminat punctele, afisez mesajul pentru  castig si inchei jocul
                {
                    FinishLevel(WindowSound, window);  //se termina nivelul
                }
            }
    }
    public void FinishLevel( Sound WindowSound,Window window) {
            playerWon.WinMessage(g);    //afisare mesaj coreapunzator
            WindowSound.StopMusic();    // inchiderea sunetului -> voi trece in meniul principal din care trec in urmatorul nivel
            WindowSound.PlaySoundsEffect(2);    //aplicare sunet "Congrats!"
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
            }
            //dupa un delay, se aplica se al doilea sunet "Level up" doar daca sunt in  nivelul 1
            if (Constants.LEVEL == 1) {
                WindowSound.PlaySoundsEffect(8);
            }
            Window.tutorialState = 0;   //resetez starea tutorial pentru a trece prima data in meniul principal
        //trecerea de la un nivel la altul si viceversa
             if(Constants.LEVEL == 1) {
                 Constants.LEVEL = 2;
            }else if(Constants.LEVEL == 2) {
                 Constants.LEVEL = 1;
             }
            window.InitNewGame();   //resetarea valorilor elementelor din joc
            window.gameState = window.menuState;
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
            }
    }
}





