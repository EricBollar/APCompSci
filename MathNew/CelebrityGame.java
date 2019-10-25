
// Eric Bollar
// AP CS, Period A
// October 1, 2019

import acm.program.*;

public class CelebrityGame extends ConsoleProgram
{

    public void run()
    {
        String celebrity = readLine("Enter the name of a celebrity: ");
        clearScreen();
        println("Your hint: " + hint(celebrity));
        
        String guess = readLine("Your guess: ");
        if (guess.equals(celebrity)) {
            println("Correct!");
        } else {
            println("Wrong! The celebrity was " + celebrity);
        }
    }
    
    public String hint(String s) {
        int spaceInd = s.indexOf(" ");
        s = s.substring(spaceInd - 2, spaceInd) + " " + s.substring(spaceInd + 1, spaceInd + 4);
        return s;
    }
    
    public void clearScreen() {
        for (int i = 0; i < 100; i++) {
            println();
        }
    }


}
