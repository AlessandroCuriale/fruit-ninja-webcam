package fruit.ninja.webcam.v2.Obj;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.Random;
import javax.swing.ImageIcon;


public class Fruit {
    public Fruit(){
        this.image = new ImageIcon(getClass().getResource("/game/image/anguria.png")).getImage();
    }
    public Random ran = new Random();
    public static final double FRUIT_SIZE = 50;
    public double x;
    public double y;
    public int maxHeight = ran.nextInt(250);
    public boolean fall = false;
    private final float speed = 0.3f;
    private float angle = 0;
    private final Image image;
    
    
    
    public void changeLocation(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public void update(){
        x += Math.cos(Math.toRadians(angle))*speed;
        y += Math.cos(Math.toRadians(angle))*speed;
    }
    
    public void changeAngle(float angle){
        if(angle < 0){
            angle = 359;
        }else if(angle > 359){
            angle = 0;
        }
        this.angle = angle;
    }
    
    public void draw(Graphics2D g2){
        AffineTransform oldTransformm= g2.getTransform();
        g2.translate(x,y);
        AffineTransform tran = new AffineTransform();
        tran.rotate(Math.toRadians(angle+45), FRUIT_SIZE/2, FRUIT_SIZE/2);
        g2.drawImage(image, tran, null);
        
        g2.setTransform(oldTransformm);
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public float getAngle(){
        return angle;
    }
}
