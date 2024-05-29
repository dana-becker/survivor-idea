public class Weapons{
    private int category; //basic weapons
    private int healthEff, damageEff, rangeEff;
    private String name;
    public Weapons(){
        category = (int)(Math.random()*5) + 1;
        set();
    }

    private void set(){
        if (category == 1){
            name = "random upgrade";
            healthEff = (int)(Math.random()*20) - 10;
            damageEff = (int)(Math.random()*4) - 2;
            rangeEff = (int)(Math.random()*6) - 3;
        }
        if (category == 2){
            name = "medic pack";
            healthEff = 8;
            damageEff = 0;
            rangeEff = 0;
        }
        if (category == 3){
            name = "radius increase";
            rangeEff = 4;
            damageEff = 0;
            healthEff = 0;
        } if (category == 4){ // basic weapon
            name = "sword";
            damageEff = 5;
            rangeEff = 0;
            healthEff = 0;
        }
    }

    public int getDamageEff() {
        return damageEff;
    }

    public int getHealthEff() {
        return healthEff;
    }

    public int getRangeEff() {
        return rangeEff;
    }

    public String getName() {
        return name;
    }
}
