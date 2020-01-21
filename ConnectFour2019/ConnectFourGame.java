import java.awt.Color;
import info.gridworld.grid.*;

// Eric Bollar
// AP CS
// December 9, 2019

public class ConnectFourGame
{
    private int[][] board;
    private int currentTurnNumber;

    public ConnectFourGame(int rows, int cols)
    {
        board = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = 0;
            }
        }

        currentTurnNumber = 1;
    }

    // returns 0, 1, or 2
    public int getWinner()
    {      
        // complete this in version 1.0

        // horizontal
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length - 3; c++) {
                if (board[r][c] != 0) {
                    if (board[r][c] == board[r][c+1] && board[r][c] == board[r][c+2] && board[r][c] == board[r][c+3]) {
                        return board[r][c];
                    }
                }
            }
        }

        // vertical
        for (int r = 0; r < board.length - 3; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] != 0) {
                    if (board[r][c] == board[r + 1][c] && board[r][c] == board[r + 2][c] && board[r][c] == board[r + 3][c]) {
                        return board[r][c];
                    }
                }
            }
        }

        // diagonal [\]
        for (int r = 0; r < board.length - 3; r++) {
            for (int c = 0; c < board[r].length - 3; c++) {
                if (board[r][c] != 0) {
                    int curr = board[r][c];
                    if (board[r+1][c+1] == curr && board[r+2][c+2] == curr && board[r+3][c+3] == curr) {
                        return curr;
                    }
                }
            }
        }

        // diagonal [/]
        for (int r = 3; r < board.length; r++) {
            for (int c = 0; c < board[r].length - 3; c++) {
                if (board[r][c] != 0) {
                    int curr = board[r][c];
                    if (board[r-1][c+1] == curr && board[r-2][c+2] == curr && board[r-3][c+3] == curr) {
                        return curr;
                    }
                }
            }
        }

        return 0; // feel free to eventually change this line.  it is just here so the rest will compile
    }

    // updates the state of the game (board and currentTurnColor)
    // returns the row in which the checker would end up
    // returns -1 if the column col is completely full and no checker can be dropped
    public int dropChecker(int col)
    {
        for (int r = board.length-1; r >= 0; r--) {
            if (board[r][col] == 0) {
                board[r][col] = currentTurnNumber;
                if (currentTurnNumber == 1) {
                    currentTurnNumber = 2;
                } else {
                    currentTurnNumber = 1;
                }
                return r;
            }
        }
        return -1;
    }
}


