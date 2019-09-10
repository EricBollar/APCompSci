
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
import java.lang.Math;

public class MouseTracker extends GraphicsProgram
{
    GLabel label;
    int ovalRadius = 30;
    GOval oval;
    
    public void run()
    {
        label = new GLabel("", 50, 50);
        label.setFont("Arial-Plain-24");
        add(label);
        oval = new GOval(80, 120, ovalRadius*2, ovalRadius*2);
        oval.setFilled(true);
        oval.setColor(Color.blue);
        add(oval);
    }

    public void mouseMoved(MouseEvent event) {
        label.setText("X: " + event.getX() + ", Y: " + event.getY());
        /*if (Math.pow(event.getX(), 2) <= 2 * Math.pow(ovalRadius, 2) + oval.getX()) {
            if (Math.pow(event.getY(), 2) <= 2 * Math.pow(ovalRadius, 2) + oval.getY()) {
                label.setColor(Color.blue);
            } else {
                label.setColor(Color.black);
            }
        } else {
            label.setColor(Color.black);
        }*/
    }
    
    public void mouseClicked(MouseEvent event) {
        oval.setLocation(event.getX() - ovalRadius, event.getY() - ovalRadius);
    }

}
