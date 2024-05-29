import processing.core.PApplet;

public class Enemies {
    private int xLoc, yLoc, xGoal, yGoal, damage, speed, health, reload, reloadCounter, range, worth;

    Character target;
    public Enemies(int xL, int yL, Character target){
        this.xLoc = xL;
        this.yLoc = yL;
        this.target = target;
        damage = 2;
        health = 20;
        speed = 1;
        reload = 300;
        reloadCounter = 0;
        range = 50;
        worth = 10;
    }

    public void act(PApplet window){
        // draw, move, attack
        reloadCounter++;
        move();
        draw(window);
        if (reloadCounter >= reload) attack();
    }

    private void attack(){
        xGoal =  target.getCurrentX();
        yGoal = target.getCurrentY();

        if (xGoal >= xLoc - range && xGoal <= xLoc + range){
            if (yGoal >= yLoc - range && yGoal <= yLoc + range){
                target.updateHealth(damage);
                reloadCounter = 0;
            }
        }
    }

    private void move() {
        xGoal =  target.getCurrentX();
        yGoal = target.getCurrentY();
        if (xLoc != xGoal){
            int mX = (xGoal - xLoc);
            if (mX < 0) {
                xLoc -= speed;
            } else {
                xLoc += speed;
            }
        }
        if (yLoc != yGoal){
            int mY = yGoal - yLoc;
            if (mY < 0){
                yLoc -=speed;
            } else {
                yLoc += speed;
            }
        }
    }

    private void draw(PApplet window) {
        window.fill(0, 200, 0);
        window.ellipse(xLoc, yLoc, range, range);
        window.fill(200, 50,50);
        window.ellipse(xLoc, yLoc, 20, 20);
    }

    public int getxLoc(){ return xLoc;}
    public int getyLoc(){ return yLoc;}

    public void updateHealth(int damage){
        health -= damage;
    }
    public int getHealth(){ return health; }

    public int getWorth() { return worth;}
}
