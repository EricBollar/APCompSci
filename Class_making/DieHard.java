
import acm.program.*;

public class DieHard extends ConsoleProgram
{

    public void run()
    {
        WaterPuzzle puzz = new WaterPuzzle();
        
        puzz.fill("A");
        puzz.pourFromAToB();
        puzz.empty("B");
        puzz.pourFromAToB();
        puzz.fill("A");
        puzz.pourFromAToB();
        
        println(puzz.getAmount("A"));
    }


}
