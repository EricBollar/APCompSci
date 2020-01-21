import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
This panel displays the game of life.
 */
public class GameOfLifePanel extends JPanel
{  

    // final static variables you can use as constants
    private static final int NUM_ROWS = 75;
    private static final int NUM_COLS = 75;

    private static final int PANEL_WIDTH = 600;
    private static final int PANEL_HEIGHT = 600;

    private static final int BOX_WIDTH = PANEL_WIDTH/NUM_COLS;
    private static final int BOX_HEIGHT = PANEL_HEIGHT/NUM_ROWS;

    // private instance variable - this is the grid!
    private boolean[][] gameGrid;

    // constructor
    public GameOfLifePanel()
    {  
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        addTheMouseListener();

        // #0 - You'll need to complete the constructor method below here.
        // The gameGrid private instance variable should be initialized to
        // be random boolean values in a two dimensional array NUM_ROWS by 
        // NUM_COLS.
        
        gameGrid = new boolean[NUM_ROWS][NUM_COLS];
        for (int r = 0; r < gameGrid.length; r++) {
            for (int c = 0; c < gameGrid[r].length; c++) {
                int rand = (int)(Math.random() * 2);
                if (rand == 1) {
                    gameGrid[r][c] = true;
                } else {
                    gameGrid[r][c] = false;
                }
            }
        }

    }

    // #1 - You'll need to complete this method.  It should return the number
    // of true neighbors around location gameGrid[r][c]. 
    private int numNeighbors(int r, int c)
    {
        int counter = 0;
        
        if (r != 0 && gameGrid[r-1][c]) {
            counter++;
        }
        if (r != NUM_ROWS - 1 && gameGrid[r+1][c]) {
            counter++;
        }
        if (c != 0 && gameGrid[r][c - 1]) {
            counter++;
        }
        if (c != NUM_COLS - 1 && gameGrid[r][c + 1]) {
            counter++;
        }
        if (c != 0 && r != 0 && gameGrid[r-1][c-1]) {
            counter++;
        }
        if (c != 0 && r != NUM_ROWS - 1 && gameGrid[r+1][c-1]) {
            counter++;
        }
        if (c != NUM_COLS - 1 && r != 0 && gameGrid[r-1][c+1]) {
            counter++;
        }
        if (c != NUM_COLS - 1 && r != NUM_ROWS - 1 && gameGrid[r+1][c+1]) {
            counter++;
        }
        
        return counter;
        // just to get it to compile
    }

    // #2 - You'll need to complete this method.  This should play one "turn" or
    // "generation" of the game, and should end with a completely new gameGrid.
    // (I'd suggest creating a temporary two-dimensional array of booleans, and 
    // copy it to gameGrid when you are done.)
    // Here are the rules of the Game of Life:
    // * "alive" cells are represented by true, "dead" cells by false
    // * alive cells die due to loneliness when they have 0 or 1 alive neighbors
    // * alive cells die due to overcrowding when they have 4 or more alive neighbors
    // * dead cells come to life when they have exact three alive neighbors
    public void playOneTurn()
    {         
        boolean[][] nextGeneration = new boolean[NUM_ROWS][NUM_COLS];
        for (int r = 0; r < gameGrid.length; r++) {
            for (int c = 0; c < gameGrid[r].length; c++) {
                int n = numNeighbors(r, c);
                if (n == 3) {
                    nextGeneration[r][c] = true;
                } else if (gameGrid[r][c] && n == 2) {
                    nextGeneration[r][c] = true;
                } else {
                    nextGeneration[r][c] = false;
                }
            }
        }
        gameGrid = nextGeneration;
        // leave the line below as the last line
        repaint();
    }

    // DON'T TOUCH ANYTHING BELOW HERE!!!
    private void addTheMouseListener()
    {
        class MousePressListener implements MouseListener
        {
            public void mouseReleased(MouseEvent event) 
            {
                

            }

            public void mousePressed(MouseEvent event) {toggleLocation(event.getX(), event.getY());

                repaint();}

            public void mouseClicked(MouseEvent event) {}

            public void mouseEntered(MouseEvent event) {}

            public void mouseExited(MouseEvent event) {}
        }
        MouseListener mListener = new MousePressListener();
        addMouseListener(mListener);
    }

    public void paintComponent(Graphics g)
    {  
        super.paintComponent(g);

        for (int j=0; j < NUM_ROWS; j++)
            for (int k=0; k < NUM_COLS; k++)
            {
                if (gameGrid[j][k])
                    g.setColor(Color.RED);
                else
                    g.setColor(Color.GREEN);
                g.fillRect(j*BOX_WIDTH, k*BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
        }

    }

    public void toggleLocation(int x, int y)
    {
        int r = x/BOX_HEIGHT;
        int c = y/BOX_WIDTH;
        gameGrid[r][c] = !gameGrid[r][c];
    }

}
