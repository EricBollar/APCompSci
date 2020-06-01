
import acm.program.*;

public class NumberGrid extends ConsoleProgram
{

    public void run()
    {
        setFont("*-*-18");
        int[][] origGrid = {{3, 4, 5, 6},      
                            {9, 8, 7, 6}, 
                            {8, 1, 8, 1}, 
                            {1, 3, 5, 7}, 
                            {8, 6, 4, 2}};
                
        printGrid(origGrid);

        waitForReturn();

        int[][] secondGrid = getVerticallySymmetricGrid(origGrid);
        printGrid(secondGrid);
        
        waitForReturn();

        int[][] thirdGrid = getEvenOddCheckerboardGrid(origGrid);
        printGrid(thirdGrid);
    }

    private void waitForReturn()
    {
        readLine();
    }

    private void printGrid(int[][] grid)
    {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                print(grid[r][c]);
            }
            println();
        }
    }

    private int[][] getVerticallySymmetricGrid(int[][] grid)
    {
        int[][] out = new int[grid.length][grid[0].length];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                out[r][c] = grid[grid.length - r - 1][c];
            }
        }
        return out;
    }

    private int[][] getEvenOddCheckerboardGrid(int[][] grid)
    {
        int[][] out = new int[grid.length][grid[0].length];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (r % 2 == 0) {
                    if (c % 2 == 0) { // even
                        if (grid[r][c] % 2 != 0) {
                            out[r][c] = grid[r][c] + 1;
                        } else {
                            out[r][c] = grid[r][c];
                        }
                    } else { // odd
                        if (grid[r][c] % 2 == 0) {
                            out[r][c] = grid[r][c] + 1;
                        } else {
                            out[r][c] = grid[r][c];
                        }
                    }
                } else {
                    if (c % 2 == 0) { // odd
                        if (grid[r][c] % 2 == 0) {
                            out[r][c] = grid[r][c] + 1;
                        } else {
                            out[r][c] = grid[r][c];
                        }
                    } else { // even
                        if (grid[r][c] % 2 != 0) {
                            out[r][c] = grid[r][c] + 1;
                        } else {
                            out[r][c] = grid[r][c];
                        }
                    }
                }
            }
        }
        return out;
    }

}
