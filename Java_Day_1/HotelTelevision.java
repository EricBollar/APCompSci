

public class HotelTelevision
{
    private int currentChannel;

    public HotelTelevision(int initialChannel)
    {
        currentChannel = initialChannel;
    }
    
    public void channelUp()
    {
        currentChannel++;
        if (currentChannel >= 10)
            currentChannel = 2;
    }
    
    public void channelDown()
    {   currentChannel--;
        if (currentChannel <= 1)
            currentChannel = 9;
    }
    
    public void setChannel(int whichChannel)
    {
        currentChannel = whichChannel;
        channelUp();
        channelDown();
    }
    
    public int getChannel() {return currentChannel; }
    
    public String getChannelName()
    {
        if (currentChannel == 2) return "FOX";
        else if (currentChannel == 3) return "ABC";
        else if (currentChannel == 4) return "CNN";
        else if (currentChannel == 5) return "CBS";
        else if (currentChannel == 6) return "ESPN";    
        else if (currentChannel == 7) return "NBC"; 
        else if (currentChannel == 8) return "HBO";
        else if (currentChannel == 9) return "MTV";
        else return "n/a";
    }


}
