package Game;
import Game.Entity.Player;
import Util.Constants;
//INFORMATII:
// -implementez sablonul SINGLETON
public class Camera {   //Am folosit Singleton pattern
    private  float x,y;
    private  static Camera camera = null;


    private Camera() {
        this.x =0;
        this.y = 0;
    }
    public void update(Player player)
    {
        x = (float) (-player.getPlayerX() + Constants.SCREEN_WIDTH/8);
    }

        public static Camera getInstanceCamera()  {
        if (camera == null) {
            camera = new Camera();
        }
        return camera;
    }
    public static void Reset()
    {
        camera = null;
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
}
