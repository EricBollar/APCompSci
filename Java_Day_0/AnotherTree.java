import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.Font;

public class AnotherTree extends GraphicsProgram
{
    public void run()
    {
        addTrunk();
        addLeaves();
        addDescription();
     }

    public void addTrunk()
    {
        GRect trunk = new GRect(50, 50, 20, 100);
        trunk.setColor(Color.YELLOW);
        trunk.setFilled(true);
        add(trunk);
    }
    
    public void addLeaves()
    {
        GOval leaves;
        leaves = new GOval(40, 15, 40, 40);
        leaves.setColor(Color.GREEN);
        leaves.setFilled(true);
        add(leaves);
    }
    
    public void addDescription()
    {
        GLabel text = new GLabel("This is a tree", 20, 180);
        Font treeFont = new Font("Palatino", Font.BOLD, 24);
        text.setFont(treeFont);
        add(text);
    }        
           
     
    
}
