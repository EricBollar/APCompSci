
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class Bullseye extends GraphicsProgram
{
    private static final int NUMRINGS = 10;
    private static final int RINGWIDTH = 20;
    private static final int INITIALX = 260;
    private static final int INITIALY = 210;

    public void run()
    {
        Color circleColor = Color.red;
        for (int i=0; i<NUMRINGS; i++)
        {
            pause(400);
            int j = NUMRINGS - i;
            GOval oval = new GOval(INITIALX - RINGWIDTH*j,
                                   INITIALY - RINGWIDTH*j,
                                   2*j*RINGWIDTH, 2*j*RINGWIDTH);
            oval.setFilled(true);
            oval.setColor(circleColor);
            add(oval);
            if (circleColor == Color.red)
            {
                circleColor = Color.gray;
            }
            else
            {
                circleColor = Color.red;
            }
        }
    }

}

