import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public final class RandomWordGenerator
{

    public static String getRandomWord()
    {
        return getRandomThing("words.txt");
    }
    
    public static String getRandomPhrase()
    {
        return getRandomThing("phrases.txt");
    }

    private static String getRandomThing(String filename)
    {
        String temp;
        try
        {
            Scanner f = new Scanner(new File(filename));
            ArrayList<String> things = new ArrayList<String>();
            while (f.hasNextLine())
            {
                things.add(f.nextLine());  
            }
            f.close();
            int index = (int)(Math.random()*things.size());
            String thing = things.get(index);
            return thing.toUpperCase();
        }
        catch (Exception ex) {System.err.println("Error: No such file  - " + filename);}
        return "";   
    }
}
