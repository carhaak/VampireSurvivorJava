import java.util.List;

public class CombatManager {

    public void handleCombat(Player player, List<Entity> entities) {
        for (Entity e : entities) {
            if (e.isEnemy()) {
                if (player.canDealDamage() && player.inAttackRange(e, (int) player.getAttackRadius())) {
                    e.takeDamage(player.getAttackDamage());
                    player.markHit();
                }
            }
        }
    }
}

