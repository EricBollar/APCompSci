
import acm.program.*;

public class HangmanConsoleProgram extends ConsoleProgram
{
    
    public void run()
    {
        println("Welcome to Hangman!");
        HangmanGame man = new HangmanGame();
        boolean gameOver = false;
        while (!gameOver) {
            println();
            println(man.getPuzzle());
            println("Letters guessed: " + man.getLettersGuessed());
            println("You have " + man.getGuessesLeft() + " guesses remaining.");
            String guess = readLine("Enter a letter: ");
            guess = guess.toUpperCase().substring(0, 1);
            if (man.makeGuess(guess)) {
                println("Good guess!");
            } else {
                println("Bad guess!");
            }
            if (man.getPuzzle().indexOf("_") == -1) {
                gameOver = true;
                println("Congrats! You guessed the word.");
            }else if (man.getGuessesLeft() <= 0) {
                gameOver = true;
                println("The word was " + man.getActualAnswer());
            }
        }
    }

}
