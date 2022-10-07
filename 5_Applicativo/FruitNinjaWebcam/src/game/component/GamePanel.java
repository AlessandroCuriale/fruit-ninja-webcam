/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package game.component;

import game.obj.Bomb;
import game.obj.Fruit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author alessandro.curiale
 */
public class GamePanel extends javax.swing.JPanel {

    /**
     * Creates new form GamePanel
     */
    public String gameTime;
    public Random ran = new Random();
    private long time = System.currentTimeMillis();  
    private long actualTime;
    private Graphics2D g2;
    private BufferedImage image;
    private int width;
    private int height;
    private Thread thread;
    private boolean start = true;
    
    //Game FPS
    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;
    
    private List<Fruit> fruits;
    private List<Bomb> bombs;
        public void start(){  
        width = getWidth();
        height = getHeight();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2= image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(start){
                    long startTime = System.nanoTime();
                    actualTime = (System.currentTimeMillis() - time)/1000;  
                    gameTime = (int)actualTime + "";
                    System.out.println(gameTime);
                    
                    drawBackgorund();
                    drawGame();
                    render();
                    
                    long time = System.nanoTime()-startTime;
                    if(time < TARGET_TIME){
                        long sleep = (TARGET_TIME-time)/1000000;
                        sleep(sleep);
                    }
                    for (int i = 0; i < fruits.size(); i++) {
                        Fruit fruit = fruits.get(i);
                        if(fruit != null){
                            double yChange = -3;
                            
                            if(fruit.fall && fruit.y > 550- fruit.maxHeight){
                                yChange = 1.2;
                            }else if(fruit.fall && fruit.y > 450- fruit.maxHeight){
                                yChange = 0.75;
                            }else if(fruit.y < 400 - fruit.maxHeight || fruit.fall){
                                yChange = 0.5;
                                fruit.fall = true;
                            }else if(!fruit.fall && fruit.y < 550- fruit.maxHeight){
                                yChange = -1.2;
                            }else if(!fruit.fall && fruit.y < 450- fruit.maxHeight){
                                yChange = -0.75;
                            }else if(!fruit.fall && fruit.y < 400- fruit.maxHeight){
                                yChange = -0.75;
                            }
                                    
                            fruit.changeLocation(fruit.x,fruit.y + yChange);
                            fruit.changeAngle(fruit.getAngle() + 1);
                            fruit.update();
                        }

                    }
                    
                    for (int i = 0; i < bombs.size(); i++) {
                        Bomb bomb = bombs.get(i);
                        if(bomb != null){
                            double yChange = -3;
                            
                            if(bomb.fall && bomb.y > 550- bomb.maxHeight){
                                yChange = 1.2;
                            }else if(bomb.fall && bomb.y > 450- bomb.maxHeight){
                                yChange = 0.75;
                            }else if(bomb.y < 400 - bomb.maxHeight || bomb.fall){
                                yChange = 0.5;
                                bomb.fall = true;
                            }else if(!bomb.fall && bomb.y < 550- bomb.maxHeight){
                                yChange = -1.2;
                            }else if(!bomb.fall && bomb.y < 450- bomb.maxHeight){
                                yChange = -0.75;
                            }else if(!bomb.fall && bomb.y < 400- bomb.maxHeight){
                                yChange = -0.75;
                            }
                                    
                            bomb.changeLocation(bomb.x,bomb.y + yChange);
                            bomb.changeAngle(bomb.getAngle() + 1);
                            bomb.update();
                        }

                    }
                    
                }
               
            }
        });
        initComponents();
        initObjectGame();
        
        //System.out.println(gameTime);
        thread.start();
    }
    
    private void addFruit(){
        int locationX = ran.nextInt(width-100)+50;
        Fruit fruit = new Fruit();
        fruit.changeLocation(locationX,height);
        fruit.changeAngle(ran.nextInt(360));
        fruits.add(fruit);
    }
    private void addBomb(){
        int locationX = ran.nextInt(width-100)+50;
        Bomb bomb = new Bomb();
        bomb.changeLocation(locationX,height);
        bomb.changeAngle(ran.nextInt(360));
        bombs.add(bomb);
    }
    private void initObjectGame() {
        fruits = new ArrayList<>();
        bombs = new ArrayList<>();
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(start){
                    addFruit();
                    sleep(2000);
                }
            }
            
        }).start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(start){
                    addBomb();
                    sleep(20000);
                }
            }
            
        }).start();
        

    }
    private void drawBackgorund(){
        g2.setColor(new Color(120,60,0));
        g2.fillRect(0, 0, width, height);
    }
    private void drawGame(){
        for (int i = 0; i < fruits.size(); i++) {
            Fruit fruit = fruits.get(i);
            if(fruit != null){
                fruit.draw(g2);
            }
        }
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if(bomb != null){
                bomb.draw(g2);
            }
        }
    }
    private void render(){
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }
    private void sleep(long speed){
        try{
            Thread.sleep(speed);
        }catch(InterruptedException ex){
            System.err.println(ex);
        }
    }
    public GamePanel() {
        //initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
