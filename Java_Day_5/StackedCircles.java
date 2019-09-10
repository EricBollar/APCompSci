
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;

// Eric Bollar
// AP CS
// September 6, 2019

public class StackedCircles extends GraphicsProgram
{
    private static final double RADIUS = 25;
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 6;

    public void run()
    {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int q = 0; q < NUM_COLS; q++) {
                GOval c = new GOval(q * RADIUS * 2, i * RADIUS * 2, RADIUS * 2, RADIUS * 2);
                add(c);
            }
        }
    }
}
