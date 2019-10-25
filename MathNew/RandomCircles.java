
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class RandomCircles extends GraphicsProgram
{

    public void run()
    {
        int numCircles = (int)(Math.random() * 201) + 50;
        
        while (true) {
            pause(50);
            int x = (int)(Math.random() * getWidth() + 1);
            int y = (int)(Math.random() * getHeight() + 1);
            int d = 6;
            GOval circle = new GOval(x - d/2, y - d/2, d, d);
            circle.setFilled(true);
            int r = 255 * 0.01*((int)(getWidth()/2.0) - circle.getX());
            int b = 255 * 0.01*((int)(getHeight()/2.0) - circle.getY());
            if (r > 255) {
                r = 255;
            }
            if (b > 255) {
                b = 255;
            }
            Color c = new Color(255 * (getWidth()/2 - circle.getX())/100, 0, getHeight()/2, - circle.getY());
            //Color c = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256), 255);
            circle.setColor(c);
            add(circle);
        }
    }


}
