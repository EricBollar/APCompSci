import java.util.ArrayList;
import acm.program.*;
import java.util.Scanner;
import java.io.*;

// Eric Bollar
// AP CS, Period A
// 3 February 2020

public class Bogglish extends ConsoleProgram
{
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPRSTUVWXYZ";

    public void run()
    {
        setFont("Times New Roman-*-24");
        println("Welcome to Bogglish!");
        println();
        String[][] board = new String[5][5];
        createBoard(board);
        printBoard(board);
        println();
        println("Your HighScore: " + readHighScore());
        println();
        boolean running = true;
        int score = 0;
        ArrayList<String> foundWords = new ArrayList<String>();

        while (running) {
            String guess = readLine().toUpperCase();
            if (guess.equals("")) {
                running = false;
                println("Thanks for playing! Final Score: " + score);
                printAllWords(board);
                if (score > readHighScore()) {
                    writeHighScore(score);
                }
            } else if (guess.length() < 3 || guess.indexOf("Q") != -1) {
                println("Words must be at least 3 letters & cannot contain Q!");
            } else if (!isRealWord(guess)) { // Not a real word
                println(guess + " is not a real word!");
            } else if (wordAlreadyFound(foundWords, guess)) { // Word that has already been found
                println(guess + " has already been found!");
            } else if (checkWord(guess, board)) { // Real Word that is on the board
                score++;
                if (readHighScore() < score) {
                    println("+1 (NEW BEST) Total Points: " + score);
                } else {
                    println("+1 Total Points: " + score);
                }
                foundWords.add(guess);
            } else { // Real Word that is not on the board
                println(guess + " is not on the board!");
            }
            pause(10);
        }

        // version 0.1, 0.2, and 0.5 here
    }

    public void printAllWords(String[][] board) {
        ArrayList<String> allWords = Dictionary.getAllWords();
        println("All possible words:");
        for (int i = 0; i < allWords.size(); i++) {
            String curr = allWords.get(i).toUpperCase();
            if (curr.length() > 3 && curr.indexOf("Q") == -1 && checkWord(curr, board)) {
                println(curr);
            }
        }
    }

    public void printBoard(String[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                print(board[r][c]);
            }
            println();
        }
    }

    public void createBoard(String[][] board) {
        ArrayList<String> letters = new ArrayList<String>();
        for (int i = 0; i < ALPHABET.length(); i++) {
            letters.add(ALPHABET.substring(i, i+1));
        }
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                int index = (int)(Math.random()*letters.size());
                board[r][c] = letters.remove(index);
            }
        }
    }

    /** Returns true if the word is "contained" somewhere in the board,
     *  following our "Bogglish" rules, false otherwise.
     *  @param word the word to check
     *  @param board the Bogglish board
     *  @return true if the word is in the board, false otherwise.
     */
    private boolean checkWord(String word, String[][] board)
    {
        // version 0.4 here
        Position prev = null;
        for (int i = 0; i < word.length(); i++) {
            String curr = word.substring(i, i+1);
            Position p = getLetterPosition(curr, board);
            if (p == null) {
                return false;
            }
            if (prev != null) {
                if (i > 0 && Math.abs(p.getCol() - prev.getCol()) <= 1 && Math.abs(p.getRow() - prev.getRow()) <= 1) {
                    prev = p;
                } else {
                    return false;
                }
            } else {
                prev = p;
            }
        }
        return true;
    }

    private boolean wordAlreadyFound(ArrayList<String> foundWords, String word) {
        for (int i = 0; i < foundWords.size(); i++) {
            String currWord = foundWords.get(i).toUpperCase();
            if (currWord.length() == word.length()) {
                for (int c = 0; c < word.length(); c++) {
                    if (!word.substring(c, c+1).equals(currWord.substring(c, c+1))) {
                        c = word.length();
                    } else if (c == word.length() - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isRealWord(String word) {
        ArrayList<String> allWords = Dictionary.getAllWords();
        for (int i = 0; i < allWords.size(); i++) {
            String currWord = allWords.get(i).toUpperCase();
            if (currWord.length() == word.length()) {
                for (int c = 0; c < word.length(); c++) {
                    if (!word.substring(c, c+1).equals(currWord.substring(c, c+1))) {
                        c = word.length();
                    } else if (c == word.length() - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** Returns the Position of where the letter is in the board
     *  Note that this returns a Position, which is a class you wrote.
     *  @param letter a String of length 1 representing the letter
     *  @param board the Bogglish board
     *  @return the Position of the letter, or null if it is not there 
     */
    private Position getLetterPosition(String letter, String[][] board)
    {
        // version 0.3 here
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c].equals(letter)) {
                    return new Position(r, c);
                }
            }
        }
        return null;
    }

    public int readHighScore() {
        try {
            String fileName = "highscore.txt";
            String workingDirectory = System.getProperty("user.dir");
            String absoluteFilePath = workingDirectory + File.separator + fileName;

            File file = new File(absoluteFilePath);

            if (file.createNewFile()) {
                writeHighScore(0);
                return 0;
            } else {
                String text = "";
                Scanner sc = new Scanner(file);
                while (sc.hasNextInt()) {
                    text += sc.nextInt();
                }
                return Integer.parseInt(text);
            }
        } catch (IOException e) {
            println("Error: " + e.getMessage());
        }
        return -1;
    }

    public void writeHighScore(int score)
    {
        String fileContent = "" + score;
        try {
            String fileName = "highscore.txt";
            String workingDirectory = System.getProperty("user.dir");
            String absoluteFilePath = workingDirectory + File.separator + fileName;

            FileWriter fileWriter = new FileWriter(absoluteFilePath);
            fileWriter.write(fileContent);
            fileWriter.close();
        } catch (IOException e) {
            println("Error: " + e.getMessage());
        }
    }

}
