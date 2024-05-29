import processing.core.PApplet;
import java.util.ArrayList;

public class Character {
    int x, y;
    private int mouseX, mouseY;
    private int speed, health, reload, reloadCounter, damage, range;

    private int bank, currentLevel;

    private ArrayList<Weapons> weapons;

    public Character() {
        x = 300;
        y = 300;
        speed = 2;
        health = 100;
        reload = 300;
        reloadCounter = 0;
        damage = 20; // need to change
        range = 80;
        bank = 0;
        currentLevel = -1;
        weapons = new ArrayList<>();
    }

    public void act(int mouseX, int mouseY, PApplet window, ArrayList<Enemies> e, ArrayList<Coin> c){
        updateMouse(mouseX, mouseY);
        moving();
        draw(window);
        incAttackCounter();
        if (reloadCounter >= reload) {
            attack(e);
        }
        collectCoins(c);
    }

    // private void checkLevels(PApplet window) {
    //        if (currentLevel != 5){ // max level
    //            if (bank >= levels[currentLevel+1]){
    //                gainWeapons(window);
    //            }
    //        }
    //    }
    //
    //    private void gainWeapons(PApplet window) {
    //        Weapons result = null;
    //            window.fill(50,50,50);
    //            //choice 1
    //            Weapons choice1 = new Weapons();
    //            window.rect(50, 200, 100, 150);
    //
    //            //choice 2
    //            window.rect(200, 200, 100, 150);
    //            Weapons choice2 = new Weapons();
    //
    //            //choice 3
    //            window.rect(350, 200, 100, 150);
    //            Weapons choice3 = new Weapons();
    //           if (window.mousePressed){
    //              if (mouseY >= 200 && mouseY <= 350){
    //                  if (mouseX >= 50 && mouseX <= 150) result = choice1;
    //                  if (mouseX >=200 && mouseX <= 300) result = choice2;
    //                  if (mouseX >= 350 && mouseX <= 500) result = choice3;
    //              }}
    //        addWeapon(result);
    //    }
    //
    //    private void addWeapon(Weapons result) {
    //        if (result != null){
    //            health += result.getHealthEff();
    //            range += result.getRangeEff();
    //            damage += result.getDamageEff();
    //        }
    //    }

    private void collectCoins(ArrayList<Coin> coins) {
        for (int i = coins.size() - 1; i >= 0; i--){
            Coin c = coins.get(i);
            if (c.getX() <= x + 30/2 && c.getX() >= x - 30/2
                    && c.getY() <= y + 30/2 && c.getX() >= x - 30/2){ // 30 is the size
                bank += c.getWorth();
                coins.remove(c);
            }
        }
    }

    private void attack(ArrayList<Enemies> enemies) {
        for (Enemies e : enemies){
            if (e.getxLoc() >= x - range && e.getxLoc() <= x + range + 30/2){
                if (e.getyLoc() >= y - range && e.getyLoc() <= x + range + 30/2){
                    e.updateHealth(damage);
                    reloadCounter = 0;
                    System.out.println("attacked");
                }
            }
        }
    }

    private void draw(PApplet window) {
        window.fill(0, 200, 0);
        window.ellipse(x, y, range, range);
        window.fill(0);
        window.ellipse(x, y, 30, 30);
    }

    public void updateMouse(int mouseX, int mouseY) {
        this.mouseX = mouseX - 10;
        this.mouseY = mouseY + 10;
    }
    public int getCurrentX() { return x; }
    public int getCurrentY() { return y; }
    public void moving() {
        int xGoal = mouseX;
        int yGoal = mouseY;

        if (x != xGoal){
            int mX = (xGoal - x);
            if (mX < 0) {
                x -= speed;
            } else {
                x += speed;
            }
        }
        if (y != yGoal){
            int mY = yGoal - y;
            if (mY < 0){
                y -=speed;
            } else {
                y += speed;
            }
        }
    }

    public int getHealth() { return health; }
    public void incAttackCounter(){ reloadCounter++;}
    public void updateHealth(int damage){ health -= damage; }

    public int getBank() {return bank; }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void addWeapon(Weapons result) {
        if (result != null){
            health += result.getHealthEff();
            range += result.getRangeEff();
            damage += result.getDamageEff();
        }
    }
}
