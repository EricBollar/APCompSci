
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

// Eric Bollar
// AP CS
// September 1, 2019

public class Stoplight extends GraphicsProgram
{
    private static final double BULB_RADIUS = 60; 
    private static final double WINDOW_WIDTH = 754; 
    private static final double WINDOW_HEIGHT = 492;

    public void run()
    {
        makeBorder();
        makeRed();
        makeYellow();
        makeGreen();
    }
    
    void makeBorder() {
        GRect border = new GRect(WINDOW_WIDTH/2 - BULB_RADIUS, WINDOW_HEIGHT/2 - BULB_RADIUS * 3, 
                                 BULB_RADIUS * 2, BULB_RADIUS * 6);
        border.setFilled(true);
        border.setColor(Color.gray);
        add(border);
    }
    
    void makeRed() {
        GOval redCircle = new GOval(WINDOW_WIDTH/2 - BULB_RADIUS, WINDOW_HEIGHT/2 - BULB_RADIUS * 3,
                                    BULB_RADIUS * 2, BULB_RADIUS * 2);
        redCircle.setFilled(true);
        redCircle.setColor(Color.red);
        add(redCircle);
    }
    
    void makeYellow() {
        GOval yellowCircle = new GOval(WINDOW_WIDTH/2 - BULB_RADIUS, WINDOW_HEIGHT/2 - BULB_RADIUS,
                                    BULB_RADIUS * 2, BULB_RADIUS * 2);
        yellowCircle.setFilled(true);
        yellowCircle.setColor(Color.yellow);
        add(yellowCircle);
    }
    
    void makeGreen() {
        GOval greenCircle = new GOval(WINDOW_WIDTH/2 - BULB_RADIUS, WINDOW_HEIGHT/2 + BULB_RADIUS,
                                    BULB_RADIUS * 2, BULB_RADIUS * 2);
        greenCircle.setFilled(true);
        greenCircle.setColor(Color.green);
        add(greenCircle);
    }

}
