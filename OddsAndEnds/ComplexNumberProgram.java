
import acm.program.*;

public class ComplexNumberProgram extends ConsoleProgram
{

    public void run()
    {
        ComplexNumber a = new ComplexNumber(3, 2);
        ComplexNumber b = new ComplexNumber(4, -6);
        ComplexNumber c = a.add(b);
        ComplexNumber d = a.multiply(b);
        ComplexNumber e = a.conjugate();
        ComplexNumber f = a.squared();
        println("a = " + a.toString());
        println("b = " + b.toString());
        println("c = " + c.toString());
        println("d = " + d.toString());
        println("e = " + e.toString());
        println("f = " + f);
    }
}
