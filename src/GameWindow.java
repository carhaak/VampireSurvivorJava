import javax.swing.*;
public class GameWindow extends JFrame{
    private int width = 1000;
    private int height = 1000;
    public GameWindow(){
        setTitle("Window");
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel(width,height);
        add(panel);

        setVisible(true);
    }
}
