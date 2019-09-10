
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
        for (int i=NUMRINGS; i>0; i--)
        {
            GOval oval = new GOval(INITIALX - RINGWIDTH*i,
                                   INITIALY - RINGWIDTH*i,
                                   2*i*RINGWIDTH, 2*i*RINGWIDTH);
            oval.setFilled(true);
            if (i % 2 == 0) {
                oval.setColor(Color.red);
            } else {
                oval.setColor(Color.gray);
            }
            add(oval);
        }
    }

}

