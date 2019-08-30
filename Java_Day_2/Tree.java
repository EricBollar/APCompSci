
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Font;

public class Tree extends GraphicsProgram
{

    public void run()
    {
        GRect trunk;
        trunk = new GRect(45, 50, 20, 100);
        trunk.setFilled(true);
        trunk.setColor(Color.YELLOW);
        add(trunk);

        GOval leaves = new GOval(35, 15, 50, 50);
        leaves.setFilled(true);
        leaves.setColor(Color.GREEN);
        add(leaves);
        
        GLabel description;
        description = new GLabel("This is a tree.", 20, 180);
        description.setFont("Palatino-Bold-24");
        add(description);

    }

}
