
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class Stoplight extends GraphicsProgram
{
    private static final double BULB_RADIUS = 30; 
    private static final double WINDOW_WIDTH = 754; 
    private static final double WINDOW_HEIGHT = 492;

    public void run()
    {
        GRect box = new GRect(WINDOW_WIDTH/2-BULB_RADIUS,
                              WINDOW_HEIGHT/2-3*BULB_RADIUS,
                              2*BULB_RADIUS, 6*BULB_RADIUS);
        box.setFilled(true);
        box.setColor(Color.gray);
        add(box);
        
        GOval yellowLight = new GOval(WINDOW_WIDTH/2-BULB_RADIUS,
                                      WINDOW_HEIGHT/2-BULB_RADIUS, 
                                      2*BULB_RADIUS, 2*BULB_RADIUS);
        yellowLight.setFilled(true);
        yellowLight.setColor(Color.yellow);
        add(yellowLight);
        
        GOval redLight = new GOval(WINDOW_WIDTH/2-BULB_RADIUS,
                                      WINDOW_HEIGHT/2-3*BULB_RADIUS, 
                                      2*BULB_RADIUS, 2*BULB_RADIUS);
        redLight.setFilled(true);
        redLight.setColor(Color.red);
        add(redLight);
    }


}
