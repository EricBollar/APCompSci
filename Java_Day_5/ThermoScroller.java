
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Font;

// Eric Bollar
// AP CS
// September 10, 2019

public class ThermoScroller extends GraphicsProgram
{
    private static final int BUTTONSIZE = 60;
    private static final int FROMEDGE = 100;
    GOval redButton;
    GOval blueButton;
    GLabel tempText;
    Thermostat t;
    
    public void run()
    {
        makeButtons();
        makeText();
        makeThermostat();
        while (true) {
            updateText();
            moveText();
            
            pause(5);
        }
    }
    
    public void makeThermostat() {
        t = new Thermostat(0, 100);
    }
    
    public void mouseMoved(MouseEvent e) {
        if (getElementAt(e.getX(), e.getY()) == tempText) {
            tempText.setColor(Color.cyan);
        } else {
            tempText.setColor(Color.black);
        }
        
        if (getElementAt(e.getX(), e.getY()) == redButton) {
            t.warmer();
        } else if (getElementAt(e.getX(), e.getY()) == blueButton) {
            t.colder();
        }
    }
    
    public void moveText() {
        int buffer = 5;
        if (tempText.getX() > getWidth() + buffer) {
            tempText.setLocation(-tempText.getWidth() - buffer , tempText.getY());
        }
        tempText.move(1, 0);
    }
    
    public void updateText() {
        tempText.setText("Temperature: " + t.getValue() + "°");
    }
    
    public void makeText() {
        tempText = new GLabel("Temperature: ", 0, getHeight() - FROMEDGE);
        Font f = new Font("Arial", Font.PLAIN, 36);
        tempText.setFont(f);
        add(tempText);
    }
    
    public void makeButtons() {
        redButton = new GOval(FROMEDGE - 0.5 * BUTTONSIZE, FROMEDGE, BUTTONSIZE, BUTTONSIZE);
        blueButton = new GOval(getWidth() - FROMEDGE - 0.5 * BUTTONSIZE, FROMEDGE, BUTTONSIZE, BUTTONSIZE);
        redButton.setFilled(true);
        blueButton.setFilled(true);
        redButton.setColor(Color.red);
        blueButton.setColor(Color.blue);
        add(redButton);
        add(blueButton);
    }


}
