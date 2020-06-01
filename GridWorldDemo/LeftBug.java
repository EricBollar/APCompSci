import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.awt.Color;

public class LeftBug extends Bug
{

    public LeftBug()
    {
        setColor(Color.green);
    }
    
    public void turn() {
        setDirection(getDirection() + Location.HALF_LEFT);
    }


}
