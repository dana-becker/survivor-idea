import processing.core.PApplet;

public class Coin {
    int x, y, worth;
    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
        worth = 1;
    }

    public void draw(PApplet window){
        window.fill(218,165,32);
        window.ellipse(x, y, 5, 5);
    }

    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getWorth(){ return worth; }
}
