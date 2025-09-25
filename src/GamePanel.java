import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {
    private int panelWidth;
    private int panelHeight;
    private Player player;
    private List<Entity> entities = new ArrayList<>();
    private CombatManager combatManager = new CombatManager();
    private Set<Integer> pressedKeys = new HashSet<>();

    private List<Effect> effects = new ArrayList<Effect>();

    public GamePanel(int width, int height){
        this.panelWidth = width;
        this.panelHeight = height;

        entities.add(player = new Player(panelWidth/2, panelHeight/2));
        entities.add(new Goblin(panelWidth/10, panelHeight/10 ));
        entities.add(new Troll(panelWidth/10 * 9, panelHeight/10 * 9));

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        Timer fps = new Timer(5, e -> {
            updatePlayer();
            repaint();
        });
        fps.start();

        Timer enemyMove = new Timer(50, e -> updateEnemies());
        enemyMove.start();
    }

    public int getPanelWidth(){ return panelWidth; }
    public int getPanelHeight(){ return panelHeight; }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        for (Effect e : effects){
            g.setColor(e.getColor());
            g.fillOval((int) e.getX(), (int)e.getY(), (int)e.getWidth(), (int)e.getHeight());
        }

        for (Entity e : entities){
            g.setColor(e.getColor());
            g.fillRect(e.getX()-3, e.getY()-3, e.getWidth(), e.getHeight());
        }
    }

    public void updatePlayer(){
        double s = 5;
        double d = s / Math.sqrt(2);

        if(!player.isAttacking()){
            effects.clear();
        }

        if(pressedKeys.size()<=1){
            if (pressedKeys.contains(KeyEvent.VK_W)) player.move(0,-s);
            if (pressedKeys.contains(KeyEvent.VK_A)) player.move(-s,0);
            if (pressedKeys.contains(KeyEvent.VK_D)) player.move(s,0);
            if (pressedKeys.contains(KeyEvent.VK_S)) player.move(0,s);
        } else {
            if (pressedKeys.contains(KeyEvent.VK_W) && pressedKeys.contains(KeyEvent.VK_D)) player.move(d,-d);
            if (pressedKeys.contains(KeyEvent.VK_W) && pressedKeys.contains(KeyEvent.VK_A)) player.move(-d,-d);
            if (pressedKeys.contains(KeyEvent.VK_S) && pressedKeys.contains(KeyEvent.VK_D)) player.move(d,d);
            if (pressedKeys.contains(KeyEvent.VK_S) && pressedKeys.contains(KeyEvent.VK_A)) player.move(-d,d);
        }
    }

    public void updateEnemies() {
        Iterator<Entity> it = entities.iterator();

        while (it.hasNext()) {
            Entity e = it.next();

            if (e.isEnemy()) {
                // move towards player
                moveEnemyTowardsPlayer(e);

                // delegate combat logic
                combatManager.handleCombat(player, entities);

                if (!e.isAlive()) {
                    it.remove();
                }
            }
        }
    }


    public void moveEnemyTowardsPlayer(Entity e) {
        if (e.isEnemy()) {
            // --- enemy movement ---
            if (Math.abs(e.getX() - player.getX()) >= Math.abs(e.getY() - player.getY())) {
                if (e.getX() > player.getX()) e.move(-5, 0);
                else if (e.getX() < player.getX()) e.move(5, 0);
            } else {
                if (e.getY() < player.getY()) e.move(0, 5);
                else if (e.getY() > player.getY()) e.move(0, -5);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent k){
        pressedKeys.add(k.getKeyCode());

        if(k.getKeyCode() == KeyEvent.VK_K) {
            player.attacks();
            double r = player.getAttackRadius();
            effects.add(new Effect(player.getX() - (r/2), player.getY() - (r/2), r, r, Color.BLUE));

        }
    }

    @Override
    public void keyReleased(KeyEvent k){
        pressedKeys.remove(k.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
