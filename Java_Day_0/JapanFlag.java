
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

// Eric Bollar
// AP CS
// August 27, 2019

public class JapanFlag extends GraphicsProgram
{

    public void run()
    {
        drawBorder(); // border of flag
        drawCircle(); // red sun
        
        drawText();  // Writes "Japan" in red underneath flag
    }
    
    public void drawBorder() {
        GRect border = new GRect(50, 50, 100, 80);
        add(border);
    }
    
    public void drawCircle() {
        GOval redSun = new GOval (85, 75, 30, 30);
        redSun.setFilled(true);
        redSun.setColor(Color.red);
        add(redSun);
    }
    
    public void drawText() {
        GLabel text = new GLabel("Japan", 65, 155);
        text.setFont("Arial-Plain-24");
        text.setColor(Color.red);
        add(text);
    }

}
