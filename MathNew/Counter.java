
import acm.program.*;

public class Counter extends ConsoleProgram
{

    public void run()
    {
        String text = readLine("Enter a sentence: ");
        int numEs = count(text, "e");
        println("Your sentence had " + numEs + " e's.");
    }
    
    public int count(String s, String symbol) {
        int c = 0;
        while (s.indexOf(symbol) != -1) {
            s = s.substring(s.indexOf(symbol) + 1);
            c++;
        }
        return c;
    }


}
