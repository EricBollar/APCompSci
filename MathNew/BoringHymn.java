
import acm.program.*;

public class BoringHymn extends ConsoleProgram
{

    public void run()
    {
        title();
        verse("word");
        chorus();
        verse("peace");
        chorus();
        verse("joy");
        chorus();
        verse("hope");
        chorus();
    }
    
    public void title()
    {
        println("Closing Hymn: \"Take the Word of God With You\"");
        println("by Christopher Walker");
        println();
    }
    
    public void verse(String thing)
    {
        println("Take the " + thing + " of God with you as you go.");
        println("Take the seeds of God's " + thing + " and make them grow.");
        println();
    }
    
    public void chorus()
    {
        println("Go in peace to serve the world, in peace to serve the world,");
        println("Take the love of God, the love of God with you as you go.");
        println();
    }


}
