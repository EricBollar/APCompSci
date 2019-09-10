
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

// Eric Bollar
// AP CS
// September 5, 2019

public class BouncingBrew extends GraphicsProgram
{

    public void run()
    {
        GImage pic = new GImage("brew.jpg", 45, 75);
        add(pic);
        double dx = 1.1;
        double dy = 1.7;
        while (true) {
            pic.move(dx, dy);
            pause(2); // delay
            
            if (pic.getY() > getHeight() - pic.getHeight()) {
                dy = -dy;
            } else if (pic.getY() <= 0) {
                dy = -dy;
            }
            if (pic.getX() > getWidth() - pic.getWidth()) {
                dx = -dx;
            } else if (pic.getX() <= 0) {
                dx = -dx;
            }
        }
    }


}
