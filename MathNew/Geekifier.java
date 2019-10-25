
import acm.program.*;

public class Geekifier extends ConsoleProgram
{

    public void run()
    {
        String name = readLine("Enter your name: ");
        String geekedName = geekify(name);
        println(geekedName);
    }

    public String geekify(String s) {
        String result = replace(s, "a", "@");
        result = replace(result, "s", "$");
        result = replace(result, "e", "3");
        result = replace(result, "i", "1");
        result = replace(result, "c", "¢");
        return result;
    }
    
    public String replace(String str, String find, String repl) {
        String n = str;
        while (n.indexOf(find) != -1) {
            int ind = n.indexOf(find);
            n = n.substring(0, ind) + repl + n.substring(ind + 1);
        }
        return n;
    }
}
