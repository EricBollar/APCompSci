
import acm.program.*;

public class HangmanConsoleProgram extends ConsoleProgram
{

    public void run()
    {
        println("Welcome to Hangman!");
        HangmanGame man = new HangmanGame();
        boolean gameOver = false;
        while (gameOver == false)
        {
            println(man.getPuzzle());
            println("Letters guessed = " + man.getLettersGuessed());
            println("You have " + man.getGuessesLeft() + " guesses remaining.");
            String guess = readLine("Enter a letter: ");
            guess = guess.toUpperCase().substring(0,1);
            if (man.makeGuess(guess)==true)
                println("Good guess!");
            else
                println("Bad guess!");
            if (man.getGuessesLeft() == 0)
                gameOver = true;
            if (man.getPuzzle().equals(man.getActualAnswer()))
                gameOver = true;
        }
        if (man.getGuessesLeft() == 0)
        {
            println("You lose!");
        }
        else
        {
            println("You win!");
        }
        println("The answer was " + man.getActualAnswer());

    }

}
