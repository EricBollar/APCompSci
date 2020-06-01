import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.awt.Color;
import java.util.ArrayList;

public class Chameleon extends Critter
{

    public Chameleon()
    {
        setColor(Color.gray);
    }
    
    public void processActors(ArrayList<Actor> actors) {
        if (actors.size() != 0) {
            int index = (int)(Math.random() * actors.size());
            Actor neighbor = actors.get(index);
            setColor(neighbor.getColor());
        }
    }
    
    public void makeMove(Location loc) {
        Location curr = getLocation();
        int dir = curr.getDirectionToward(loc);
        setDirection(dir);
        super.makeMove(loc);
    }


}
