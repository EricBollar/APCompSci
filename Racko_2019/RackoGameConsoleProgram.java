
import acm.program.*;

public class RackoGameConsoleProgram extends ConsoleProgram
{

    public void run()
    {
        println("Welcome to Rack-o!");
        RackoGame game = new RackoGame();
        ComputerPlayer computer = new RandomComputerPlayer();
        while (!game.playerWins() && !game.computerWins())
        {
            int[] yourHand = game.getPlayerHand();
            print("Your hand: " + yourHand[0]);
            for (int i=1; i<yourHand.length; i++)
                print("," + yourHand[i]);
            println();
            println("Top card of discard pile = " + game.peekAtTopCardFromDiscardPile());
            int deckOrDiscard = readInt("Do you want to (1) draw from the deck, or (2) take the top card from the discard pile? ");
            while (deckOrDiscard != 1 && deckOrDiscard != 2)
                deckOrDiscard = readInt("Do you want to (1) draw from the deck, or (2) take the top card from the discard pile? ");
            int cardValue;
            if (deckOrDiscard == 1)
                cardValue = game.removeTopCardFromDeck();
            else // deckOrDiscard == 2
                cardValue = game.removeTopCardFromDiscardPile();
            int index = readInt("Where do you want to put the number " + cardValue + "? [1 to 10] ");
            while (!(1<=index && index<=10))
                index = readInt("Where do you want to put the number " + cardValue + "? [1 to 10] ");
            game.makeMove(cardValue, index-1, yourHand);
            println();
            if (!game.playerWins())
            {
                boolean discard = computer.shouldDrawFromDiscardPile(game.peekAtTopCardFromDiscardPile(), game.getComputerHand());
                if (!discard)
                    cardValue = game.removeTopCardFromDeck();
                else 
                    cardValue = game.removeTopCardFromDiscardPile();
                index = computer.getIndexForReplacementCard(cardValue, game.getComputerHand());
                game.makeMove(cardValue, index, game.getComputerHand());
                println("The computer made a move.");
                println();
            }
        }
        if (game.playerWins())
           println("You win!");
        else
            println("You lose!");
    }

}
