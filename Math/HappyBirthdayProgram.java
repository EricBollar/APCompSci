
import acm.program.*;

public class HappyBirthdayProgram extends ConsoleProgram
{

    public void run()
    {
        sing("Serena Williams");
        sing("Calvin Coolidge");
    }
    
    public void sing(String name) {
        println("Happy birthday to you,");
        println("Happy birthday to you,");
        println("Happy birthday dear " + name);
        println("Happy birthday to you,");
        println();
    }


}
