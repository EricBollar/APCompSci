
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class Tree extends GraphicsProgram
{

    public void run()
    {
        GRect trunk;
        trunk = new GRect(50, 45, 20, 100);
        trunk.setFilled(true);
        trunk.setColor(Color.yellow);
        add(trunk);
        
        GOval leaves = new GOval(40, 15, 55, 55);
        leaves.setFilled(true);
        leaves.setColor(Color.green);
        add(leaves);
        
        GLabel text;
        text = new GLabel("This is a tree.", 20, 180);
        text.setColor(Color.magenta);
        text.setFont("Arial-Bold-24");
        add(text);
    }


}
