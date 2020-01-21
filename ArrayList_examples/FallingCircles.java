
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class FallingCircles extends GraphicsProgram
{
    private static final int NUMCIRCLES = 100;
    private static final int DIAM = 50;
    private static final double DY = 0.2;

    private GOval[] circles;

    public void run()
    {
        circles = new GOval[NUMCIRCLES];
        for (int i=0; i<circles.length;i++)
        {
            circles[i] = getRandomCircle();
        }

        while (true)
        {
            pause(10);
            for (int i=0; i<circles.length;i++)
            {
                circles[i].move(0, DY);   
            }
        }
    }

    public GOval getRandomCircle()
    {
        int x = (int)(Math.random()*getWidth());
        int y = (int)(Math.random()*getHeight());
        Color c = new Color ((int)(Math.random()*256), 
                (int)(Math.random()*256),
                (int)(Math.random()*256));
        GOval circle = new GOval(x,y,DIAM,DIAM);
        circle.setColor(c);
        circle.setFilled(true);
        add(circle);
        return circle;
    }

}
