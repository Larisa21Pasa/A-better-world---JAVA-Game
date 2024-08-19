package Util;
import java.awt.*;

public class GameOver {     //implementarea mesajului pentru sfarsitul jocului
    public String text;
    public Font font;
    public double x,y;

    public GameOver() {
        this.text = "GAME OVER";
        this.font = new Font("Times New Roman",Font.BOLD,72);
        this.x = Constants.SCREEN_WIDTH/2-200;
        this.y = Constants.SCREEN_HEIGHT/2-200;
    }
    public void GameOverMessage(Graphics2D g) {     //daca playerul face coliziune cu inamicul, se afiseaza mesajul si se face trecerea catre meniul principal, pentru a reincepe nivelul
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0,0, (int) Constants.SCREEN_WIDTH, (int) Constants.SCREEN_HEIGHT);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(text, (float) x,(float)y);
    }
}