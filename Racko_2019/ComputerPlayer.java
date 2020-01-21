
public interface ComputerPlayer
{
    boolean shouldDrawFromDiscardPile(int topCardInDiscardPile,int[] hand);
    int getIndexForReplacementCard(int newCardValue, int[] hand);
}
