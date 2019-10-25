
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class RandomProgram extends GraphicsProgram
{

    public void run()
    { 
        int alpha = 255;
        
        for (int i = 0; i < 255; i++) {
            int h = (int)(Math.random() * getHeight());
            int w = (int)(Math.random() * getWidth());
            
            GOval o = new GOval(0, 0, h, w);
            o.setFilled(true);
            
            int r = (int)(Math.random() * 255);
            int g = (int)(Math.random() * 255);
            int b = (int)(Math.random() * 255);
            
            Color c = new Color(r, g, b, alpha);
            alpha--;
            o.setColor(c);
            
            add(o);
            pause(10);
        }
    }


}
