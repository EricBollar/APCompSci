
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class ConnectFourGraphicsProgram extends GraphicsProgram
{
    private static final int PIECE_DIAM = 100;
    private static final int NUM_COLS = 7;
    private static final int NUM_ROWS = 6;
    public static final int APPLICATION_WIDTH = NUM_COLS * PIECE_DIAM;
    public static final int APPLICATION_HEIGHT = NUM_ROWS * PIECE_DIAM;

    /** Dimensions of game board in pixels (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    private ConnectFourGame game;
    private boolean gameIsBeingPlayed;
    private Color currentDrawingColor;

    public void run()
    {
        currentDrawingColor = Color.RED;

        game = new ConnectFourGame(NUM_ROWS, NUM_COLS);
        gameIsBeingPlayed = true;
        addMouseListeners();

        while (game.getWinner()==0)
        {
            pause(5);
        }
        gameIsBeingPlayed = false;

        // add code here in version 0.4
        GLabel winner = new GLabel("Game Over", 100, 100);
        if (currentDrawingColor == Color.red) {
            winner.setColor(Color.black);
        } else {
            winner.setColor(Color.red);
        }
        add(winner);
    }

    public void mouseClicked(MouseEvent event) 
    { 
        if (!gameIsBeingPlayed) return;
        int col = event.getX()/PIECE_DIAM;
        int row = game.dropChecker(col);
        if (row == -1) {
            return;
        }
        drawChecker(row, col);
        if (currentDrawingColor == Color.red) {
            currentDrawingColor = Color.black;
        } else {
            currentDrawingColor = Color.red;
        }
    }

    public void drawChecker(int col, int row)
    {
        GOval piece = new GOval(row*PIECE_DIAM , col*PIECE_DIAM, PIECE_DIAM, PIECE_DIAM);
        piece.setFilled(true);
        piece.setColor(currentDrawingColor);
        add(piece);
    }

}
