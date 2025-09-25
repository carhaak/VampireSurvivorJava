import java.awt.*;

public class Player extends Entity{
    private double attackRadius = 30;

    public Player(int x, int y) {
        super("Player", 100, 25, 10, x, y, 10, 10, Color.BLACK);
    }

    @Override
    public boolean isPlayer() {
        return true;
    }
    public double getAttackRadius(){
        return attackRadius;
    }
}
