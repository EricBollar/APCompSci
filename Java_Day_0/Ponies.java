
import acm.program.*;

public class Ponies extends ConsoleProgram
{

    public void run()
    {
        println("Welcome to the pony calculator.");
        String name = readLine ("What is your name? ");
        println("Hi, " + name + ". I hope you're having a nice day.");
        int numPonies = readInt("How many ponies? ");
        int numLegs = numPonies * 4;
        println("There are " + numLegs + " legs!");
    }


}
