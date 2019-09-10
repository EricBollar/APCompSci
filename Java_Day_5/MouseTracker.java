
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class MouseTracker extends GraphicsProgram
{
    GLabel label;
    GOval circle;
    
    public void run()
    {
        label = new GLabel("Hi", 50, 50);
        label.setFont("Arial-Plain-24");
        add(label);
        circle = new GOval(200, 450, 200, 350);
        circle.setFilled(true);
        circle.setColor(Color.red);
        add(circle);
    }
    
    public void mouseMoved(MouseEvent event)
    {
        label.setText("X=" + event.getX() + ", Y=" + event.getY());
        if (getElementAt(event.getX(), event.getY()) == circle) {
            label.setColor(Color.green);
        } else if (getElementAt(event.getX(), event.getY()) == label) {
            label.setColor(Color.magenta);
        } else {
            label.setColor(Color.black);
        }
    }
    
    public void mouseClicked(MouseEvent event)
    {
        circle.setLocation(event.getX() - 50, event.getY() - 50);
    }


}
