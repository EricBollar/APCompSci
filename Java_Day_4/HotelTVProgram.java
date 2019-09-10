
import acm.program.*;

public class HotelTVProgram extends ConsoleProgram
{

    public void run()
    {
        int startChannel;
        startChannel = readInt("What channel do you want to start on?");
        HotelTelevision tv;
        tv = new HotelTelevision(startChannel);
        
        tv.channelUp();
        
        println("You are on channel " + tv.getChannel());
        println("That channel is " + tv.getChannelName());
        
        tv.setChannel(2);
        
        for (int i=0; i<20; i++)
        {
           tv.channelUp();
           println("Channel " + tv.getChannel() + " is " + tv.getChannelName());
        }
    }


}
