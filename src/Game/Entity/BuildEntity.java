package Game.Entity;

import Game.Window;

import java.awt.*;
//INFORMATII:
// - am aplicat MODELUL BUILDER in care am creat entitatea cu valorile necesare citite si updatate la fiecare "InitNewGame()"
// - constructorii pentru inamic si player contin si ale valori, dar nu necesare pentru transmiterea lor ca parametri, de aceea modelul builder asigura doar valorile de baza, precum pozitionarea entitatii si zona de coliziune
public class BuildEntity extends Entity {
    public BuildEntity(int x, int y, int w, int h, Window window) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.window = window;
        this.solidAreaEntity = new Rectangle((int) (x + 10), (int) (y + 10), (int) (this.width - 10), (int) (this.height - 10));
    }
    public Enemy buildEnemy() {
        return new Enemy(this);

    }
    public Player buildPlayer() {
        Player player = new Player(this);
        return player;

    }

}
