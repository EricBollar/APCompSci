
import acm.program.*;

public class ChangeMaker extends ConsoleProgram
{

    public void run()
    {
        double cost = readDouble("Enter the cost: ");
        double paid = readDouble("Enter amount paid: ");
        int change = (int)((paid - cost)*100+0.5);
        int dollars = change / 100;
        change = change % 100;
        int quarters = change / 25;
        change = change % 25;
        int dimes = change / 10;
        change = change % 10;
        int nickels = change / 5;
        int pennies = change % 5;
        
        print("Your change is ");
        print(dollars + " dollars, ");
        print(quarters + " quarters, ");
        print(dimes + " dimes, ");
        print(nickels + " nickels, and ");
        println(pennies + " pennies.");
       
       
    }


}
