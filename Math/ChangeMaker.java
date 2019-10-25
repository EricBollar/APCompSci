
import acm.program.*;

public class ChangeMaker extends ConsoleProgram
{

    public void run()
    {
        double cost = readDouble("Enter the cost of the item: $");
        double paid = readDouble("Enter the amount paid: $");
        
        int change = (int)((paid - cost) * 100 + 0.5);
        println("Change due: $" + change/100.0);
        
        int dollars = change / 100;
        change = change % 100;
        print("   " + dollars + " Dollars, ");
        int quarters = change / 25;
        change = change % 25;
        print(quarters + " Quarters, ");
        int dimes = change / 10;
        change = change % 10;
        print(dimes + " Dimes, ");
        int nickels = change / 5;
        change = change % 5;
        print(nickels + " Nickels, ");
        int pennies = change;
        print(pennies + " Pennies.");
    }


}
