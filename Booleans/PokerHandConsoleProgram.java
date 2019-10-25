
import acm.program.*;

public class PokerHandConsoleProgram extends ConsoleProgram
{

    public void run()
    {
        for (int i = 0; i < 5; i++) {
            Card card = new Card((int)(Math.random()*4), (int)(Math.random()*13));
            println(card.toString());
        }
    }


}
