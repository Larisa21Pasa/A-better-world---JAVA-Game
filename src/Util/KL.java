package Util;
import Game.Sound.Sound;
import Game.State.State;
import Game.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KL implements KeyListener {         //creez variabile pentru fiecare taste
    public boolean UP_pressed, DOWN_pressed,LEFT_pressed,RIGHT_pressed,SPACE_pressed,P_pressed,O_pressed,ENTER_pressed;
    Window window;



    public KL(Window window)
    {
        this.window=window;
    }   //constructor

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {    //apelez functia pentru managerierea starii  curente a jocului, in functie de tastele apasate
        //MENU STATE KL:
        if (window.gameState == window.menuState) {
            MenuState(e);
        }
        //PLAY STATE KL:
        else if (window.gameState == window.playState) {
            PlayState(e);
        }
        //PAUSE STATE:
        else if(window.gameState == window.pauseState) {
            PauseState();
        }
        //OPTION STATE KL:
        else if (window.gameState == window.optionState) {
            OptionState(e);
        }
    }
    public void MenuState(KeyEvent e){      //functia in care setez valorile pentru commandNum, o variabila ajutatoare pentru alegerile din meniu
        if (window.tutorialState == 0) {    //daca nu sunt in starea "tutorial", starea dinaintea inceperii jocului (unde se precizeaza cum se poate juca), setez  commandNum in urmatorul mod:
            //prin tastele UP / DOWN aleg optiunea dorita din meniu
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                State.commandNum--;
                if (State.commandNum < 0) {     //fiind doar 3 optiuni, daca se ajunge la prima + UP_pressed, trec la ultima
                    State.commandNum = 2;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {   //fiind doar 3 optiuni, daca se ajunge la ultima + DOWN_pressed, trec la prima
                State.commandNum++;                     //faciliteaza fluiditatea codului
                if (State.commandNum > 2) {
                    State.commandNum = 0;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {      //daca am selectat o optiune
                if (State.commandNum == 0) {    //NEW GAME - valabil doar cand termin toate nivelurile ->caz contrar, se va afisa mesaj corespunzator <-
                   if(Constants.LEVEL == 1) {   //Conditionare pentru a intra in nivel nou ( adica nivelul 1)
                           ENTER_pressed = true;
                           window.tutorialState = 1;    //activez starea de "tutorial"
                       }
                }
                if (State.commandNum == 1) {        //LOAD GAMEA - valabil doar cand  am trecut de nivelul 1 ->caz contrar, se va afisa mesaj corespunzator <-
                     if(Constants.LEVEL == 2) {
                        ENTER_pressed = true;
                        window.tutorialState = 1;
                     }
                }
                if (State.commandNum == 2) {        //QUIT - inchiderea ferestrei jocului
                    System.exit(0);
                }
            }
            System.out.println("nivel"+Constants.LEVEL);
        } else if (window.tutorialState == 1) {     //daca sunt in "tutorial"
            //prin tastele UP / DOWN aleg optiunea dorita din meniu
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                State.commandNum--;
                if (State.commandNum < 3) {
                    State.commandNum = 4;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                State.commandNum++;
                if (State.commandNum > 4) {
                    State.commandNum = 3;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {        //daca am selectat o optiune
                if (State.commandNum == 3) {       //SKIP - trece de tutorial in nivelul curent
                    ENTER_pressed=true;
                    window.gameState = window.playState;
                    window.sound.PlayMusic(0);
                }
                if (State.commandNum == 4) {        //BACK - intoarcere in meniul principal
                    ENTER_pressed=true;
                    Window.tutorialState = 0;
                }

            }
        }
    }
    public void PlayState(KeyEvent e) {     //in starea play se regasesc toate comenzile de modificare a pozitiei playerului
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            UP_pressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            LEFT_pressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            RIGHT_pressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            SPACE_pressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            DOWN_pressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            P_pressed = true;
            window.gameState=window.pauseState;     //trecerea in modul pauza -> nu se updateaza nimic din harta
        } else  if(e.getKeyCode() == KeyEvent.VK_O) {
            window.gameState = window.optionState;
        }
        else  if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            ENTER_pressed=true;
        }
    }
    public void OptionState(KeyEvent e) {       //optiune valabila in timpul jocului
        if (e.getKeyCode() == KeyEvent.VK_O) {      //conditia de intrare in meniul de optiuni
            O_pressed = true;
            window.gameState = window.playState;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            State.commandNum--;
            if (State.commandNum < 0) {
                State.commandNum = 3;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            State.commandNum++;
            if (State.commandNum > 3) {
                State.commandNum = 0;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {        //implementare pentru modificarea nivelului SUNET
            if(window.subStateOption == 0) {
                if(State.commandNum == 1 && Sound.VolumeScale > 0)  //daca este sageata pozitionata pe optiunea de sunet
                {
                    Sound.VolumeScale--;
                    Sound.checkMusicVolume();   //actualizare nivel sunet
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(window.subStateOption == 0) {
                if(State.commandNum == 1 && Sound.VolumeScale < 5)
                {
                    Sound.VolumeScale++;
                    Sound.checkMusicVolume();
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (State.commandNum == 3) {    //BACK - intoarcerea in joc

                window.gameState = window.playState;


            }
            if (State.commandNum == 2) {    //END - terminarea jocului - > meniu principal - > reluare nivel curent
                Window.tutorialState = 0;
                window.gameState= window.menuState;;
                window.sound.StopMusic();       //trecand in meniul principal, sunetul este oprit pentru a fi activat la inceperea jocului (nivelului)
                window.InitNewGame();   //se initiala un nou joc - > resetarea valorilor pentru nivelul corespunzator
            }
        }
    }
    public void PauseState() {
        window.gameState = window.playState;    //revenirea din modul pauza dupa  prima apasare a tastei P
    }




    @Override
    public void keyReleased(KeyEvent e) {   //setarea cheilor pe false in caz de eliberare
        if(e.getKeyCode() == KeyEvent.VK_UP)         {  UP_pressed = false; }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {  LEFT_pressed = false;   }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {  RIGHT_pressed = false;  }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE) {  SPACE_pressed = false;  }
        else  if(e.getKeyCode() == KeyEvent.VK_DOWN) {  DOWN_pressed = false; }
        else  if(e.getKeyCode() == KeyEvent.VK_P) {  P_pressed = false; }
        else  if(e.getKeyCode() == KeyEvent.VK_O) {  O_pressed = false; }
        else  if(e.getKeyCode() == KeyEvent.VK_ENTER) {  ENTER_pressed = false; }
    }
}