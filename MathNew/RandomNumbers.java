
import acm.program.*;

public class RandomNumbers extends ConsoleProgram
{

    public void run()
    {
        while (true) {
            pause(50);
            double num = (int) (Math.random() * 90) + 10;
            println(num);
        }
    }


}
