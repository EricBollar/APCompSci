
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

// Eric Bollar
// AP CS
// September 4, 2019

public class RowOfBlocks extends GraphicsProgram
{
    private static final int START_X = 25;
    private static final int START_Y = 48;
    private static final int NUM_BLOCKS = 30;
    private static final int BLOCK_SIZE = 18;
    private static final int GAP = 6;

    public void run()
    {
        for (int i = 0; i < NUM_BLOCKS; i++) {
            GRect block = new GRect(START_X + (BLOCK_SIZE + GAP) * i, START_Y,
                                    BLOCK_SIZE, BLOCK_SIZE);
            block.setFilled(true);
            block.setColor(Color.black);
            add(block);
        }
    }

}
