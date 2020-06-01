import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.awt.Color;

public class BoxBug extends Bug
{
    private int steps;
    private int sideLength;
    
    public BoxBug(int side)
    {
        setColor(Color.magenta);
        steps = 0;
        sideLength = side;
    }
    
    public void act() {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        } else {
            turn();
            turn();
            steps = 0;
        }
    }

}
