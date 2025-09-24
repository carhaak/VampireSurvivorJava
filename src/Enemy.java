import java.awt.*;

public class Enemy extends Entity{
    public Enemy(String name, int healthPoints, int attackDamage, int armor, int x, int y, Color colour) {
        super(name, healthPoints, attackDamage, armor, x, y, 20, 20, colour);
    }
    @Override
    public boolean isEnemy() {
        return true;
    }
}
