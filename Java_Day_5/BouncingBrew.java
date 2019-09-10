
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class BouncingBrew extends GraphicsProgram
{

    public void run()
    {
        GImage pic = new GImage("brew.jpg", 45, 75);
        add(pic);
        double dx = 1.1;
        double dy = 1.7;
        while (true)
        {
            pause(8);
            pic.move(dx, dy);
            if (pic.getY() + pic.getHeight() > getHeight())
            {
                dy = -dy;
            }
            if (pic.getX() + pic.getWidth() > getWidth())
            {
                dx = -dx;   
            }
            if (pic.getY() < 0)
            {
                dy = -dy;
            }
            if (pic.getX() < 0)
            {
                dx = -dx;
            }
        }
    }


}
