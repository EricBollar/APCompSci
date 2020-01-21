
import acm.program.*;
import java.util.ArrayList;

public class PokerGame extends ConsoleProgram
{

    public void run()
    {
        //Card[] cards = new Card[52];
        ArrayList<Card> deck = new ArrayList<Card>();
        for (int i = 0; i < 52; i++) {
            int rank = i % 13;
            int suit = i/13;
            deck.add(new Card(rank, suit));
        }

        for (int i = 0; i < 5; i++) {
            int index = (int)(Math.random() * deck.size());
            println(deck.get(index));
            deck.remove(index);
        }
    }

}
