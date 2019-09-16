
import acm.program.*;

public class ProductRunner extends ConsoleProgram
{

    public void run()
    {
         
        Product item = new Product("Toaster", 29.95);
        item.reducePrice(5.00);
        println(item.getName() + " costs $" + item.getPrice());
    }


}
