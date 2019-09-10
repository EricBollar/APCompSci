
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
        light = new GOval(-1000, -1000, 2*RADIUS, 2*RADIUS);
        light.setFilled(true);
        light.setColor(Color.yellow);
        add(light);
    }
    
    public void mouseMoved(MouseEvent event)
    {
        light.setLocation(event.getX() - RADIUS, event.getY() - RADIUS);   
    }


}
