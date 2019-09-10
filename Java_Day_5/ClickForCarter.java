
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class ClickForCarter extends GraphicsProgram
{

    public void run()
    {

    }
    
    public void mouseClicked(MouseEvent event)
    {
        GImage carter = new GImage("carter.jpg", event.getX(), event.getY());
        add(carter);
    }


}
