

public class RandomComputerPlayer implements ComputerPlayer
{

    public boolean shouldDrawFromDiscardPile(int topCardInDiscardPile, int[] hand)
    {
        return Math.random() > 0.5;
    }
    
    public int getIndexForReplacementCard(int newCardValue, int[] hand)
    {
        return (int)(Math.random()*hand.length);
    }

}
