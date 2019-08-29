
import acm.program.*;

public class Ponies extends ConsoleProgram
{

    public void run()
    {
        println("Welcome to the pony program.");
        String yourName;
        yourName = readLine("What is your name?");
        println("Hi, " + yourName + ". I hope you're having a nice day.");
        int numPonies;
        numPonies = readInt("How many ponies?");
        println("You have " + numPonies + " ponies.");
        int numLegs = numPonies * 4;
        println("There are " + numLegs + " legs!!!!");
    }


}
