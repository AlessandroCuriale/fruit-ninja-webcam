/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Curiale Alessandro
 */
public class Sprite extends Entity {

    GamePanel gp;
    int size;
    Random ran = new Random();
                //a[0.08; 0.5] b[0.0; 1.0] c[-20;10]
    double spostX = ran.nextDouble() * 180 + 10 ;
    double ampiezza = ran.nextDouble() * 15 + 5;
    public Sprite(GamePanel gp, int size, String imagePath, double a ,double b, double c) {
        this.gp = gp;
        this.size = size;
        this.a = a;
        this.b = b;
        this.c = c;
        Random ranInc = new Random();
        if(ranInc.nextInt(0, 2) == 1){
            this.x = 1920;
            this.incX = -0.1;
        }else{
            this.x = 0;
            this.incX = 0.1;
        }
        this.x = 1920;
        if (imagePath != null) {
            try {
                setImage(imagePath);
            } catch (IOException ex) {
                Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Sprite(GamePanel gp, int size, double a ,double b, double c) {
        this(gp, size, null, a, b, c);
    }

    public void setImage(String imagePath) throws IOException {
        System.out.println(new File(imagePath).getAbsolutePath());
        image = ImageIO.read(new File(imagePath));
    }

    
    //ax^2 + bx + c
    public void update() {
        this.x += this.incX;
        
        this.y = a * Math.pow(x/ampiezza-spostX, 2)+ b * x + c;
        
        if(!isVisible()){
            
        }
    }

    public void cutFruits(){
        gp.addPoints();
    }
    
    public boolean isVisible(){
        

        if(this. x <= gp.getWidth() && this.y <= gp.getHeight()){
            this.visible = true;
            return true;
        }else{
            return false;
        }
    }
    public void draw(Graphics2D g2) throws IOException {

        if (image != null) {
            BufferedImage imageToDraw = image;
                      
            g2.drawImage(imageToDraw, (int)x, (int)y, size, size, null);
        }
    }
}
