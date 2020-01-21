import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public final class RandomWordGenerator
{
    private ArrayList<String> words;

    public RandomWordGenerator()
    {
        words = new ArrayList<String>();
        try
        {
            Scanner f = new Scanner(new File("words.txt"));

            while (f.hasNextLine())
            {
                words.add(f.nextLine().toUpperCase());  
            }
            f.close();
        }
        catch (Exception ex) {System.err.println("Error: No such file  - words.txt");}

    }

    public String getRandomWord()
    {
        return words.get((int)(Math.random()*words.size()));
    }
}
