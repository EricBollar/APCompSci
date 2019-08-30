
import acm.program.*;

public class TVConsoleProgram extends ConsoleProgram
{

    public void run()
    {
        println("Great!  I'm in a hotel.");
        String name = "Jane";
        HotelTelevision tv = new HotelTelevision(4);
        tv.channelUp();
        println("Hi, " + name + "!");
        println("You're watching " + tv.getChannelName());
        int num;
        num = readInt("Which channel? ");
        tv.setChannel(num);
        println("Now you're watching channel " + tv.getChannel() +
                 " which is " + tv.getChannelName());
    }


}
