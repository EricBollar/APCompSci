
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class GRectRunner extends GraphicsProgram
{

    public void run()
    {
        GRect box = new GRect(75.3, 20.2, 45.0, 50.96);
        box.setColor(Color.green);
        box.scale(0.75, 2.25);
        double width = box.getWidth();
        double x = box.getX();
        box.sendToBack();        
    }


}
