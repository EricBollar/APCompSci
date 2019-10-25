
import acm.program.*;

public class LeapYear extends ConsoleProgram
{

    public void run()
    {
        while (true)
        {
            int year = readInt("Enter a year: ");
            if (isLeapYear(year))
                println("That's a leap year!!!");
            else
                println("That's not a leap year!!!!");
        }
    }
    
    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

}
