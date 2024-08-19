package Game.TileManager;
import java.awt.*;


public class Tile extends TileUtil {     //implementarea platformelor pentru sarit/evitat, dr si a punctelor

    public Tile(boolean solidTile, boolean Iscoin,boolean isFinalcOIN,int xTile,int yTile,int wTile,int hTile) {
        x=xTile;
        y = yTile;
        width=wTile;
        height=hTile;
        solid = solidTile;
        image = null;
        IsCoin=Iscoin;
        isFinalCoin=isFinalcOIN;
    }

    public int getX() {
        return  x;
    }
    public int getY() {
        return  y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void MakeTileDisapear() {
        setY(0);
        setX(0);
        setHeight(0);
        setWidth(0);
    }
    public Rectangle getBounds()
    {
        return new Rectangle( getX(),  getY(),width, height);
    }
}
