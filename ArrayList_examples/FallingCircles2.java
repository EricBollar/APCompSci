
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList; // !!!

public class FallingCircles2 extends GraphicsProgram
{
    private static final int NUMCIRCLES = 100;
    private static final int DIAM = 50;
    private static final double DY = 0.2;

    ArrayList<GOval> circles; // !!!
    
    public void run()
    {
        circles = new ArrayList<GOval>(); // !!!
        for (int i=0; i<NUMCIRCLES; i++)
        {
            GOval circle = getRandomCircle();
            circles.add(circle); // !!!
        }
        
        while (true)
        {
            pause(5);
            for (int i=0; i<circles.size(); i++) // !!!
            {
                circles.get(i).move(0, DY); // !!!
                if (circles.get(i).getY()>getHeight())
                {
                    circles.remove(i); // !!!
                }
            }
        }
    }
    
    public void mouseClicked(MouseEvent event)
    {
        GOval circle = getRandomCircle();
        circle.setLocation(event.getX(), event.getY());
        circles.add(circle); // !!!
    }
    
    public GOval getRandomCircle()
    {
        GOval circle = new GOval((int)(Math.random()*getWidth())-DIAM/2,
                                 (int)(Math.random()*getHeight()),
                                 DIAM, DIAM);
        
        circle.setFilled(true);
        Color color = new Color((int)(Math.random()*256),
                                (int)(Math.random()*256),
                                (int)(Math.random()*256));
        circle.setColor(color);
        add(circle);
        return circle;
    }


}
