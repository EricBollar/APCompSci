/*
import acm.program.*;
import java.util.ArrayList;

public class MagicSquare extends ConsoleProgram
{

    private int SIZE = 3;
    
    public void run()
    {
        int[][] square = getRandomSquare();
        while (!isMagic(square)) {
            square = getRandomSquare();
        }
        printSquare(square);
    }
    
    public void printSquare(int[][] square) {
        for (int r = 0; r < square.length; r++) {
            for (int c = 0; c < square[0].length; c++) {
                print(square[r][c] + " ");
            }
            println();
        }
    }
    
    public int[][] getRandomSquare() {
        
    }
    
    public boolean isMagic(int[][] square) {
        int magicSum = 0;
        for (int c = 0; c < square[0].length; c++) {
            magicSum += square[0][c];
        }
        for (int r = 1; r < square.length; r++) {
            int sum = 0;
            for (int c = 0; c < square[0].length; c++) {
                sum += square[r][c];
            }
        }
    }
    
}*/
