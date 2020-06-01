
import acm.program.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Dictionary 
{

    public static ArrayList<String> getAllWords()
    {
        ArrayList<String> words = new ArrayList<String>();
        try {
            Scanner wordFile = new Scanner(new File("words.txt"));
            while (wordFile.hasNextLine())
            {
                words.add(wordFile.nextLine());
            }
            return words;
        } catch(Exception ex) {System.err.println("Something went wrong.");}
        return new ArrayList<String>();
    }


}
