
import acm.program.*;

public class HappyBirthday extends ConsoleProgram
{

    public void run()
    {
        sing("Cami");
        sing("Gwyneth Paltrow");
        sing("Avril Lavigne");
        sing("Lil' Wayne");
        sing("Samuel Adams");
    }
    
    public void sing(String name)
    {
        println("Happy birthday to you");
        println("Happy birthday to you");
        println("Happy birthday dear " + name);
        println("Happy birthday to you");
        println();
    }

    

}
