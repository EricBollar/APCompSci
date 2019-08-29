
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.Font;

public class ItalianFlag extends GraphicsProgram
{

    public void run()
    {
        GRect greenThird = new GRect(20, 20, 40, 60);
        greenThird.setFilled(true);
        greenThird.setColor(Color.green);
        add(greenThird);
  
        GRect redThird = new GRect(100, 20, 40, 60);
        redThird.setFilled(true);
        redThird.setColor(Color.red);
        add(redThird);
         
        // this draws a nice black border around the flag
        // notice how I did NOT make it filled, and it is black by default
        GRect border = new GRect(20, 20, 120, 60);        
        add(border);
        
        GLabel italia = new GLabel("Viva Italia!", 20, 120);
        Font font = new Font("Arial", Font.PLAIN, 36);
        italia.setFont(font);
        add(italia);
        
    
    }


}
