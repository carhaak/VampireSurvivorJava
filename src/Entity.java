import static java.lang.System.out;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Entity {
    private String name;
    private int healthPoints;
    private int attackDamage;
    private int armor;
    private int x,y;
    private int width, height;
    private Color color;
    private boolean attack = false;

    public Entity(String name, int healthPoints, int attackDamage, int armor, int x, int y, int width, int height, Color color){
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackDamage = attackDamage;
        this.armor = armor;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public Color getColor(){
        return this.color;
    }
    public boolean isPlayer(){
        return false;
    }
    public boolean isEnemy(){
        return false;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public void move(double dx, double dy){
        x += dx;
        y += dy;
    }

    public String getName() {
        return name;
    }
    public int getHealthPoints(){
        return this.healthPoints;
    }
    public void setHealthPoints(int HP){
        this.healthPoints = HP;
    }
    public int getArmor(){
        return this.armor;
    }
    public void setArmor(int a){
        this.armor = a;
    }
    public int getAttackDamage(){
        return this.attackDamage;
    }
    public void setAttackDamage(int atk){
        this.attackDamage = atk;
    }
    public boolean isAlive(){
        return this.healthPoints > 0;
    }
    public void takeDamage(int dmg){
        int effectiveHP = this.healthPoints + (healthPoints * armor)/100;
        this.healthPoints = effectiveHP - dmg;
        out.println(this.name + " got attacked and now has " + this.healthPoints + " HP!");
    }

    public String toString(){
        return this.name + " has " + this.healthPoints + " HP, " + this.attackDamage + " damage and " + this.armor + " armor!";
    }


    public void attacks(){
        if (!isAttacking()){
            attack = true;
        }
        javax.swing.Timer timer = new javax.swing.Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAttack();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    public void stopAttack(){
        attack = false;
    }
    public boolean isAttacking(){
        return attack;
    }
}
