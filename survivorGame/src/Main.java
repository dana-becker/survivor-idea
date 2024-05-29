import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    ArrayList<Enemies> enemies;
    int enemyCounter;

    ArrayList<Coin> coins;

    Character main;
    int[] levels = {30, 60, 100, 120, 150};

    public void settings(){
        size(600,600);
    }
    public void setup(){
        main = new Character();
        enemies = new ArrayList<>();
        coins = new ArrayList<>();
        Enemies E = new Enemies((int) (Math.random()*600), (int) (Math.random()*600), main);
        enemies.add(E);
    }
    public void draw(){
        ArrayList<Integer> toRemove = new ArrayList<>();
        background(180);
        writeText();
        main.act(mouseX, mouseY, this, enemies, coins);
        if (needNewWeapons()) addWeapons();
        for (int i = enemies.size() -1 ; i >= 0; i--){
            Enemies e = enemies.get(i);
            e.act(this);
            if (e.getHealth() <= 0){
                enemyDeathSequence(e.getWorth(), e.getxLoc(), e.getyLoc());
               enemies.remove(e);
            }
        }
        if (enemyCounter == 500){ // can change
            Enemies E = new Enemies((int) (Math.random()*600), (int) (Math.random()*600), main);
            enemies.add(E);
            enemyCounter = 0;
        } else {
            enemyCounter++;
        }
        for (Coin c : coins){
            c.draw(this);
        }
    }

    private void addWeapons() { // how to add pause?
        background(0);
        Weapons result = null;
         fill(50,50,50);
         //choice 1
        Weapons choice1 = new Weapons();
        rect(50, 200, 100, 150);
        //choice 2
          rect(200, 200, 100, 150);
        Weapons choice2 = new Weapons();
        //choice 3
        rect(350, 200, 100, 150);
        Weapons choice3 = new Weapons();

             if (mousePressed) {
                 if (mouseY >= 200 && mouseY <= 350) {
                     if (mouseX >= 50 && mouseX <= 150) result = choice1;
                     if (mouseX >= 200 && mouseX <= 300) result = choice2;
                     if (mouseX >= 350 && mouseX <= 500) result = choice3;
                 }
             }
        main.addWeapon(result);
    }


    private boolean needNewWeapons() {
        if (main.getBank() >= levels[main.getCurrentLevel()+1]) return true;
        return false;
    }

    private void enemyDeathSequence(int worth, int xL, int yL) {
        for (int i = 0; i < worth; i++){
            Coin n = new Coin((int)((Math.random()*xL+10)+ xL - 10), (int)(Math.random()*(yL+10) + yL - 10));
            coins.add(n);
        }
    }

    private void writeText() {
        fill(0);
        text("Health: " + main.getHealth(), 100, 30);
        text("Number of Enemies: " + enemies.size(), 300, 30);
        text("Bank: " + main.getBank(), 500, 30);
    }


    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
