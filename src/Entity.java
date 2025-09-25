import static java.lang.System.out;
import java.awt.Color;

public class Entity {
    private String name;
    private int healthPoints;
    private int attackDamage;
    private int armor;
    private int x, y;
    private int width, height;
    private Color color;

    private boolean attack = false;
    private boolean canAttack = true;
    private boolean hasHit = false;

    public Entity(String name, int healthPoints, int attackDamage, int armor,
                  int x, int y, int width, int height, Color color) {
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

    // --- getters/setters ---
    public Color getColor() { return this.color; }
    public boolean isPlayer() { return false; }
    public boolean isEnemy() { return false; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public void move(double dx, double dy) { x += dx; y += dy; }
    public String getName() { return name; }
    public int getHealthPoints() { return this.healthPoints; }
    public void setHealthPoints(int HP) { this.healthPoints = HP; }
    public int getArmor() { return this.armor; }
    public void setArmor(int a) { this.armor = a; }
    public int getAttackDamage() { return this.attackDamage; }
    public void setAttackDamage(int atk) { this.attackDamage = atk; }
    public boolean isAlive() { return this.healthPoints > 0; }

    public void takeDamage(int dmg) {
        int effectiveHP = this.healthPoints + (healthPoints * armor) / 100;
        this.healthPoints = effectiveHP - dmg;
        out.println(this.name + " got attacked and now has " + this.healthPoints + " HP!");
    }

    @Override
    public String toString() {
        return this.name + " has " + this.healthPoints + " HP, " +
                this.attackDamage + " damage and " + this.armor + " armor!";
    }

    // --- Attack system ---
    public void attacks() {
        if (!canAttack) return;

        attack = true;
        canAttack = false;
        hasHit = false; // reset for this swing

        // stop attack after 200 ms
        javax.swing.Timer attackTimer = new javax.swing.Timer(200, e -> stopAttack());
        attackTimer.setRepeats(false);
        attackTimer.start();

        // re-enable attack after 500 ms cooldown
        javax.swing.Timer cooldownTimer = new javax.swing.Timer(500, e -> canAttack = true);
        cooldownTimer.setRepeats(false);
        cooldownTimer.start();
    }

    public void stopAttack() {
        attack = false;
    }

    public boolean isAttacking() {
        return attack;
    }

    // --- One-hit-per-swing support ---
    public boolean canDealDamage() {
        return attack && !hasHit;
    }

    public void markHit() {
        hasHit = true;
    }

    // --- Range check helper ---
    public boolean inAttackRange(Entity other, int radius) {
        double dx = other.getX() - this.getX();
        double dy = other.getY() - this.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= radius;
    }
}
