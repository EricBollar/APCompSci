
import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.util.ArrayList;

public class GridWorldMain
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Crab());
        world.show();
    }
}
