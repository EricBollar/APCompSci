
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class Tree extends GraphicsProgram
{

    public void run()
    {
        GRect trunk;
        trunk = new GRect(50, 50, 20, 100);
        trunk.setFilled(true);
        trunk.setColor(Color.red);
        add(trunk);
        
        GOval leaves = new GOval(40, 15, 45, 45);
        leaves.setFilled(true);
        leaves.setColor(Color.green);
        add(leaves);
        
        GLabel text;
        text = new GLabel("This is a tree", 20, 180);
        text.setColor(Color.magenta);
        text.setFont("Times-Plain-36");
        add(text);
    }

}
