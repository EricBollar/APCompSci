
import acm.program.*;

public class QuadraticFormula extends ConsoleProgram
{

    public void run()
    {
        double a = readDouble("Enter a: ");
        double b = readDouble("Enter b: ");
        double c = readDouble("Enter c: ");
        if (Math.pow(b, 2) - 4*a*c < 0)
        {
            println("Imaginary solutions.");
        }
        else
        {
            double x1 = (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c))/(2*a);
            double x2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c))/(2*a);
            println("Your solutions are " + x1 + " and " + x2);
        }
    }

}
