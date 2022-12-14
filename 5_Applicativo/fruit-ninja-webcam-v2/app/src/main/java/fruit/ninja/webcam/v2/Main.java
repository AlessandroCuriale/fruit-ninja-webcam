/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package fruit.ninja.webcam.v2;

import java.awt.Canvas;
import java.lang.Object;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.lang.*;
import java.util.ArrayList;

import org.bytedeco.javacv.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_imgproc.*;
import org.bytedeco.opencv.opencv_calib3d.*;
import org.bytedeco.opencv.opencv_objdetect.*;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.bytedeco.videoinput.videoInput;
import org.opencv.highgui.HighGui;

import com.google.common.io.Files;

import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.FFmpegFrameRecorder;

import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_calib3d.*;
import static org.bytedeco.opencv.global.opencv_objdetect.*;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvSaveImage;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.bytedeco.videoinput.videoInput;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Renderer;
import java.awt.Image;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import de.gurkenlabs.litiengine.*;
import de.gurkenlabs.litiengine.GameWindow;


import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.tweening.Tweenable;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.entities.behavior.Path;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.environment.PropMapObjectLoader;
import de.gurkenlabs.litiengine.environment.tilemap.MapProperty;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.*;
import de.gurkenlabs.litiengine.graphics.RenderEngine;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.gurkenlabs.litiengine.gui.GuiProperties;
import de.gurkenlabs.litiengine.gui.SpeechBubble;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.gui.screens.Resolution.Ratio;
import de.gurkenlabs.litiengine.gui.screens.Resolution.Ratio16x10;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import java.awt.geom.Rectangle2D;
import java.awt.Toolkit;

public class Main extends GameScreen implements IRenderable, Tweenable {
    private static int height;
    private static int width;
    final static OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
    public Main(){
        super();
    }
    public static void main(String[] args) {
        
        Game.info().setName("FRUIT NINJA WEBCAM");
        Game.info().setSubTitle("");
        Game.info().setVersion("2");
        Game.info().setDescription("Una prova di un gioco in litiengine");
        
        
        /*
        int count = (int)videoInput.listDevices();

        System.out.println(count);
        String[] descriptions = new String[count];
        for (int i = 0; i < descriptions.length; i++) {
          descriptions[i] = videoInput.getDeviceName(i).getString();
        }
        System.out.println("------");
        System.out.println(descriptions[0]);
         
         */
        
        Game.init(args);
        Game.window().getRenderComponent().setBackground(Color.gray);
        //FrameGrabber grabber = new OpenCVFrameGrabber(0);
        try            
        {
            grabber.setImageHeight(1080);
            grabber.setImageWidth(1920);
            
            grabber.start();
        }
        catch (Exception e) 
        {    
            System.out.println("!!! Exception");
        }
        Game.screens().add(new Main());
        
        Game.start();
        
        
    }
    
    
    OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
    IplImage img;
    @Override
    public void render(final Graphics2D g) {

        super.render(g); 
        g.setColor(Color.BLACK);
        height = Game.window().getHeight();
        width = Game.window().getWidth();
        //System.out.println(height);
        //System.out.println("width: " + width + "quadrato: " + -width/2);
        Rectangle2D rect = new Rectangle2D.Double(0, 0, width/2, height/2);        
        

        System.out.println(Game.graphics().getBaseRenderScale());
        try {
            
            Frame frame = null;
            //Frame frame = grabber.grab();
            if (frame != null){
                img = converter.convert(frame);
                //the grabbed frame will be flipped, re-flip to make it right
                
                //save
                cvFlip(img, img, 1);
                cvSaveImage("img.jpg", img);
                
                BufferedImage bufferedImage = ImageIO.read(new File("img.jpg"));

                
                Game.graphics().renderImage(g, bufferedImage, -960, -540);
                
            }
                

            //Resources.images().add("E:\\I3BB\\adasdasdasdasd\\img.jpg", image);
         } catch (IOException e) {
             e.printStackTrace();
         }


        //hand detection https://www.javacodegeeks.com/2012/12/hand-and-finger-detection-using-javacv.html

        Game.graphics().renderShape(g, rect);
        
        TextRenderer.render(g, Game.metrics().getFramesPerSecond() + " FPS", 10, Game.window().getResolution().getHeight() - 20);        
    }      
}
