
import acm.program.*;

public class DoublesAreInexact extends ConsoleProgram
{

    public void run()
    {
        double num = Math.sqrt(5);
        double anotherNum = Math.pow(num, 2);
        
        println(anotherNum);
    }


}
