
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class Tiles extends GraphicsProgram
{
    private static final int TILE_SIZE = 80;
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;
    private static final Color firstColor = Color.red;
    private static final Color secondColor = Color.black;

    private GRect[][] tiles;
    private int status;

    public void run()
    {
        setSize(TILE_SIZE*NUM_COLS, TILE_SIZE*NUM_ROWS+24);
        status = -1;
        initializeTiles();
        status = playGame(); 
        String[] endGameStrings = {"All same color", "Checkerboard", "Ring", "Stripes"};
        showEndMessage(endGameStrings[status]);
    }

    private void showEndMessage(String message)
    {
        GLabel label = new GLabel(message, 0, 0);
        label.setFont("*-*-24");
        label.setLocation(getWidth()/2-label.getWidth()/2,  getHeight()/2-label.getHeight()/2);
        label.setColor(Color.green);
        add(label);
    }

    private void initializeTiles()
    {
        tiles = new GRect[NUM_ROWS][NUM_COLS];
        for (int r=0; r<tiles.length; r++)
        {
            for (int c=0; c<tiles[0].length; c++)
            {
                tiles[r][c] = new GRect(c*TILE_SIZE, r*TILE_SIZE, TILE_SIZE, TILE_SIZE);
                tiles[r][c].setFilled(true);
                if (Math.random()<0.5)
                    tiles[r][c].setColor(firstColor);
                else
                    tiles[r][c].setColor(secondColor);
                add(tiles[r][c]);
            }
        }
    }

    private int playGame()
    {
        while (true)
        {
            if (allSameColor())  return 0;
            if (checkerBoard())  return 1;
            //if (ring())          return 2;
            //if (stripes())       return 3;
        }
    }

    private boolean allSameColor() 
    {
        Color startColor = tiles[0][0].getColor();
        for (int r=0; r<tiles.length; r++)
            for (int c=0; c<tiles[0].length; c++)
                if (tiles[r][c].getColor() == startColor)
                    return false;
        return true;
    }

    private boolean checkerBoard() 
    {
        Color startColor = tiles[0][0].getColor();
        for (int r=0; r<tiles.length; r++)
            for (int c=0; c<tiles[0].length; c++)
            {
                if ((r + c) % 2 == 0)
                {    if (!tiles[r][c].getColor().equals(startColor))
                        return false;
                }
                else
                {
                    if (tiles[r][c].getColor().equals(startColor))
                        return false;
                }
            }
        return true;
    }

    private boolean ring() 
    {
       return false;  // we'll do this
    }

    private boolean stripes()
    {
        return false; // we'll do this
    }

    private void toggleTileColor(GRect tile)
    {
        if (tile.getColor().equals(firstColor))
            tile.setColor(secondColor);
        else
            tile.setColor(firstColor);   
    }

    public void mouseClicked(MouseEvent event)
    {
        if (status != -1)
            return;
        int row = event.getY()/TILE_SIZE;
        int col = event.getX()/TILE_SIZE;
        toggleTileColor(tiles[row][col]);
        if (!isShiftKeyDown(event))
        {
            if (row!=0)
                toggleTileColor(tiles[row-1][col]);
            if (row!=tiles.length-1)
                toggleTileColor(tiles[row+1][col]);
            if (col!=0)
                toggleTileColor(tiles[row][col-1]);
            if (col!=tiles.length-1)
                toggleTileColor(tiles[row][col+1]);
        }

    }

    private boolean isShiftKeyDown(MouseEvent event)
    {
        return (event.getModifiers() & MouseEvent.SHIFT_MASK) != 0;
    }
}
