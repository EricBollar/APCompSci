
import acm.program.*;

public class RoachPopulationRunner extends ConsoleProgram
{

    public void run()
    {
        RoachPopulation roaches = new RoachPopulation(1000);
        roaches.breed();
        roaches.breed();
        roaches.spray();
        roaches.spray();
        roaches.breed();
        roaches.breed();
        println("There are " + roaches.getNumRoaches() + " roaches.");
    }


}
