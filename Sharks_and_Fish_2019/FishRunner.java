
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;
import info.gridworld.actor.Actor;

public class FishRunner
{
    public static void main(String[] args)
    {

        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 7), new Shark());
        world.add(new Location(7, 8), new Shark());
        world.add(new Location(6, 7), new Shark());
        world.add(new Location(6, 5), new Shark());

        world.add(new Location(0, 0), new MaleFish());
        world.add(new Location(0, 1), new FemaleFish());
        world.add(new Location(1, 0), new FemaleFish());
        world.add(new Location(1, 1), new FemaleFish());
        
        world.show();
    }
}