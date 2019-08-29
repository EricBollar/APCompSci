
import acm.program.*;

public class TVConsoleProgram extends ConsoleProgram
{

    public void run()
    {
        println("I'm in a hotel.");
        String name = "Eric";
        HotelTelevision tv = new HotelTelevision(4);
        tv.channelUp();
        println("Hi, " + name + "!");
        println("You're watching " + tv.getChannelName() + ".");
        int num = readInt("Which channel? ");
        tv.setChannel(num);
        println("Now you're watching channel " + tv.getChannel() + 
                ", which is " + tv.getChannelName() + ".");
    }


}
