
import acm.program.*;

public class CarRunner extends ConsoleProgram
{

    public void run()
    {
        Car hummer = new Car(8,30);
        Car prius = new Car(45, 12);
                
        hummer.fillup(10);
        prius.fillup(5);
        
        double gallons = readDouble("How many gallons?");
        prius.fillup(gallons);

        prius.drive(10);
        hummer.drive(20);
        
        int gauge = hummer.getGasGauge();
        gauge = gauge + 5;
        
        println("The prius gas gauge is " + prius.getGasGauge());
        

    }


}
