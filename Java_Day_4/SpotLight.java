
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class SpotLight extends GraphicsProgram
{
    private static final double RADIUS = 50;
    GOval light;
    
    public void run()
    {
        GRect back = new GRect(0, 0, getWidth(), getHeight());
        back.setFilled(true);
        back.setColor(Color.black);
        add(back);
        light = new GOval(0, 0, 2*RADIUS, 2*RADIUS);
        light.setFilled(true);
        light.setColor(Color.yellow);
        add(light);
    }

    public void mouseMoved(MouseEvent event) {
        light.setLocation(event.getX() - RADIUS, event.getY() - RADIUS);
    }

}
