
import acm.program.*;

public class Palindrome extends ConsoleProgram
{

    public void run()
    {
        while (true) {
            String word = readLine("Enter word: ");
            if (isPalindrome(word)) {
                println("Is a palindrome!");
            } else {
                println("Not a palindrome!");
            }
        }
    }
    
    public boolean isPalindrome(String word) {
        for (int i = 1; i < word.length()/2; i++) {
            String letter = word.substring(i, i+1);
            String partner = word.substring(word.length() - i - 1, word.length() - i);
            if (!letter.equals(partner)) {
                return false;
            }
        }
        return true;
    }
}
