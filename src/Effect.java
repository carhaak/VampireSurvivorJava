import java.awt.*;

public class Effect {
    private Color color;
    private double x;
    private double y;
    private double width;
    private double height;
    public Effect(double x, double y, double width, double height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color c){
        this.color = c;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
}
