
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
import java.applet.AudioClip;
import acm.util.MediaTools;

public class BalloonProgram extends GraphicsProgram
{
    private Balloon redBalloon;
    private boolean currentlyFilling;
    private GOval redCircle;
    private GLabel label;
    private AudioClip blowSound = MediaTools.loadAudioClip("inhale.wav");
    private AudioClip popSound = MediaTools.loadAudioClip("explode.wav");  
    
    private static final double AIR_AMT = 700000;
    private static final double DELAY = 1600; 
    
    public void run()
    {
        redBalloon = null;
        currentlyFilling = false;
        label = new GLabel("", 25, 25);
        label.setFont("Arial-Plain-24");
        add(label);
        while (true)
        {
            pause(DELAY);
            
            if (currentlyFilling == true)
            {
                redBalloon.addAir(AIR_AMT+200000*Math.random()-50000);
                redCircle.setSize(redBalloon.getRadius()*2, redBalloon.getRadius()*2);       
                if (redBalloon.isPopped()==true)
                    popSound.play();
                else
                    blowSound.play();
            }
        }
    }
    
    public void mousePressed(MouseEvent e)
    {
        redBalloon = new Balloon();
        redCircle = new GOval(e.getX(), e.getY(), 0,0);
        redCircle.setFilled(true);
        redCircle.setColor(Color.red);
        add(redCircle);
        currentlyFilling = true;
    }
    
    public void mouseReleased(MouseEvent e)
    {
        currentlyFilling = false;
        label.setLabel("Radius = " + (int)redBalloon.getRadius() + " Area = " + (int)redBalloon.getSurfaceArea() + " Volume = " + (int)redBalloon.getVolume());
    }

}
