
public class Card
{
    private int rank;
    private int suit;

    public Card(int startingRank, int startingSuit)
    {
        rank = startingRank;
        suit = startingSuit;
    }

    public String getSuit()
    {
        if (suit==0)
            return "spades";
        else if (suit==1)
            return "hearts";
        else if (suit==2)
            return "diamonds";
        else
            return "clubs";

    }
    
    public String getRank()
    {
        if (rank==0)
           return "ace";
        else if (rank==1)
           return "two";
        else if (rank==2)
           return "three";
        else if (rank==3)
           return "four";
        else if (rank==4)
           return "five";
        else if (rank==5)
           return "six";
        else if (rank==6)
           return "seven";
        else if (rank==7)
           return "eight";
        else if (rank==8)
           return "nine";
        else if (rank==9)
           return "ten";
        else if (rank==10)
           return "jack";
        else if (rank==11)
           return "queen";
        else 
           return "king";          
    }

    public String toString()
    {
        return getRank() + " of " + getSuit();
    }
    
}
