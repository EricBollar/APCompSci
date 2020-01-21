
import acm.program.*;

public class FibonacciSequence extends ConsoleProgram
{

    public void run()
    {
        int num = readInt("Which Fibonacci number would you like? ");
        int[] fibNums = new int[num+1];
        fibNums[0] = 0;
        fibNums[1] = 1;
        for (int i = 2; i < fibNums.length; i++) {
            fibNums[i] = fibNums[i-1] + fibNums[i-2];
        }
        println(fibNums[fibNums.length-1]);
    }

}
