
import acm.program.*;

public class BaseballProgram extends ConsoleProgram
{

    public void run()
    {
        println("Let's play ball...");
        BaseballInning inning;
        inning = new BaseballInning(8);
        inning.batterOut();
        inning.batterBaseHit(2);
        println(inning.getBaseRunners());
        int outs = inning.getOuts();
        println("Outs: " + outs);
        inning.batterOut();
        
        println(outs);
        println(inning.getOuts());
        
        int x = 4;
        println("x=" + x + " happy Friday");
        x = x + 3;
        println("now, x = " + x);
        
        int a = 3;
        int b = 5;
        println(a + " " + b);
        a = 2 * a;
        println(a + " " + b);
        b = a;
        println(a + " " + b);
        
        inning.batterOut(); // :-)
        inning.getRuns(); // :-(
        println(inning.getRuns());  // :-)
        int aidan = inning.getRuns(); // :-)
        // int luke = inning.batterOut(); // :-(
    }
 

}
