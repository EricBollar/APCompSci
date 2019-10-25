

public class Card
{
    private int suit;
    private int rank;
    
    public Card(int suit, int rank)
    {
        this.suit = suit;
        this.rank = rank;
    }
    
    public String toString() {
        return getRank() + " of " + getSuit();
    }

    public String getSuit() {
        switch(suit) {
            case 0: return "Spades";
            case 1: return "Hearts";
            case 2: return "Clubs";
            case 3: return "Diamonds";
        }
        return "Invalid Suit";
    }
    
    public String getRank() {
        switch(rank) {
            case 0: return "Ace";
            case 1: return "Two";
            case 2: return "Three";
            case 3: return "Four";
            case 4: return "Five";
            case 5: return "Six";
            case 6: return "Seven";
            case 7: return "Eight";
            case 8: return "Nine";
            case 9: return "Ten";
            case 10: return "Jack";
            case 11: return "Queen";
            case 12: return "King";
        }
        return "Invalid Rank";
    }
}
