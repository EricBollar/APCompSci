
import acm.program.*;

public class PigLatin extends ConsoleProgram
{

    public void run()
    {
        while (true) {
            String word = readLine("Enter a word: ");
            println(pigify(word));
        }
    }

    public String pigify(String s) {
        if (startsWithVowel(s)) {
            return s + "way";
        } else {
            int index = getIndexOfFirstVowel(s);
            return s.substring(index) + s.substring(0, index) + "ay";
        }
    }
    
    public boolean startsWithVowel(String word) {
        String vowels = "aeiou";
        String firstLetter = word.substring(0, 1);
        if (vowels.indexOf(firstLetter) == -1) {
            return false;
        }
        return true;
    }
    
    public int getIndexOfFirstVowel(String s) {
        s = s.substring(1);
        int chops = 1;
        while (startsWithVowel(s) == false) {
            s = s.substring(1);
            chops++;
        }
        return chops;
    }
}
