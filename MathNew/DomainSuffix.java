
import acm.program.*;

public class DomainSuffix extends ConsoleProgram
{

    public void run()
    {
        String url = readLine("Enter a website: ");
        int dot = url.indexOf(".");
        String rest = url.substring(dot + 1);
        int dot2 = rest.indexOf(".");
        println("Suffix = " + rest.substring(dot2 + 1, dot2 + 4));
    }


}
